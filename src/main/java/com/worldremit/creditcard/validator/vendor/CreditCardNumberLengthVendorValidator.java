package com.worldremit.creditcard.validator.vendor;

import com.google.common.base.Preconditions;
import com.worldremit.creditcard.validator.utils.ValidatorUtils;

public class CreditCardNumberLengthVendorValidator implements CreditCardNumberVendorValidator {
	private CreditCardVendorSpec creditCardVendorSpec;

	public CreditCardNumberLengthVendorValidator(CreditCardVendorSpec creditCardVendorSpec) {
		Preconditions.checkNotNull(creditCardVendorSpec, "creditCardVendorSpec is null");
		this.creditCardVendorSpec = creditCardVendorSpec;
	}

	@Override public boolean validate(String number) {
		ValidatorUtils.checkCreditNumberPreconditions(number);
		return (number.length() >= creditCardVendorSpec.getMinNumberOfDigits() &&
				number.length() <= creditCardVendorSpec.getMaxNumberOfDigits());
	}

	@Override public boolean supports(CreditCardVendorType vendorType) {
		return creditCardVendorSpec.getType().equals(vendorType);
	}

}
