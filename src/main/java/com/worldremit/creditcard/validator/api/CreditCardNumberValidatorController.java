package com.worldremit.creditcard.validator.api;

import com.worldremit.creditcard.validator.service.CreditCardNumberValidatorService;
import com.worldremit.creditcard.validator.vendor.CreditCardVendorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController()
@RequestMapping("/api")
public class CreditCardNumberValidatorController {

	@Autowired
	private CreditCardNumberValidatorService creditCardNumberValidatorService;

	@PostMapping("/creditcards/validate")
	public ResponseEntity<ValidationResponse> validateCreditCard(@RequestBody ValidationRequest request) {
		//Preconditions
		Optional<CreditCardVendorType> vendorTypeOpt = parseVendorType(request.getVendorType());
		if (vendorTypeOpt.isEmpty()) {
			return generateInvalidVendorResponse();
		}

		//Validations
		boolean result = creditCardNumberValidatorService.validate(vendorTypeOpt.get(), request.getNumber());
		ValidationResponse response = new ValidationResponse(result);
		response.setValid(result);
		if (result) {
			return ResponseEntity.ok().body(response);
		} else {
			response.setErrorMessage("The credit card number is invalid !!!");
			return ResponseEntity.badRequest().body(response);
		}
	}

	private Optional<CreditCardVendorType> parseVendorType(String vendorType) {
		if (vendorType == null) {
			return Optional.empty();
		}
		try {
			return Optional.of(CreditCardVendorType.valueOf(vendorType));
		} catch (IllegalArgumentException e) {
			return Optional.empty();
		}
	}

	private ResponseEntity<ValidationResponse> generateInvalidVendorResponse() {
		ValidationResponse response = new ValidationResponse(false);
		response.setErrorMessage(String.format("Invalid vendor type, supported vendors: %s",
											   Arrays.asList(CreditCardVendorType.values())));
		return ResponseEntity.badRequest().body(response);
	}

}
