package com.worldremit.creditcard.validator.vendor;

import com.worldremit.creditcard.validator.CreditCardNumberValidator;

public interface CreditCardNumberVendorValidator extends CreditCardNumberValidator {

	boolean supports(CreditCardVendorType vendorType);

}
