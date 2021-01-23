package com.worldremit.creditcard.validator.common;

import com.worldremit.creditcard.validator.CreditCardNumberValidator;
import com.worldremit.creditcard.validator.utils.ValidatorUtils;

public class CreditCardLuhnAlgorithmValidator implements CreditCardNumberValidator {

	@Override
	public boolean validate(String number) {
		ValidatorUtils.checkCreditNumberPreconditions(number);

		boolean result = false;
		int sum = 0;

		boolean secondValue = false;
		for(int i = number.length() - 1 ; i >= 0; i--) {
			int val = Character.getNumericValue(number.charAt(i));
			val = (secondValue) ? 2*val : val;
			sum += (val<10) ? val : (val%10 + val/10);
			secondValue = !secondValue;
		}

		if(sum % 10 == 0) {
			result = true;
		}

		return result;
	}

}
