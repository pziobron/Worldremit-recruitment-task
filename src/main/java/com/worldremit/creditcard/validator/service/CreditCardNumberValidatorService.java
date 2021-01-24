package com.worldremit.creditcard.validator.service;

import com.google.common.base.Preconditions;
import com.worldremit.creditcard.config.CreditCardValidators;
import com.worldremit.creditcard.validator.service.exception.InvalidNumberException;
import com.worldremit.creditcard.validator.utils.ValidatorUtils;
import com.worldremit.creditcard.validator.vendor.CreditCardVendorType;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardNumberValidatorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardNumberValidatorService.class);

	@Autowired
	private CreditCardValidators creditCardValidators;

	@Timed(value = "ccNumberValidatorServiceCallTimes")
	public boolean validate(CreditCardVendorType vendorType, String number) {
		//Preconditions
		Preconditions.checkNotNull(vendorType, "vendorType is null");
		try {
			ValidatorUtils.checkCreditNumberPreconditions(number);
		} catch(IllegalArgumentException e) {
			throw new InvalidNumberException(e.getMessage());
		}

		//Validation
		boolean validationResult = creditCardValidators
				.getCommonValidators().stream().allMatch(validator -> validator.validate(number));

		if (validationResult) {
			validationResult = creditCardValidators.getVendorSpecificValidators().stream()
					.filter(validator -> validator.supports(vendorType))
					.allMatch(validator -> validator.validate(number));
			if (!validationResult) {
				LOGGER.info("Validation failed in the vendor specific validation stage");
			}
		} else {
			LOGGER.info("Validation failed in the common validation stage");
		}

		return validationResult;
	}

}
