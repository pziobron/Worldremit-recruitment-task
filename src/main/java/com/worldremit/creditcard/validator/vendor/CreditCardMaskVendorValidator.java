package com.worldremit.creditcard.validator.vendor;

import com.google.common.base.Preconditions;
import com.worldremit.creditcard.validator.utils.ValidatorUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreditCardMaskVendorValidator implements CreditCardNumberVendorValidator {
	private CreditCardVendorSpec creditCardVendorSpec;
	private Map<Long, Long> maskRangeMap = new HashMap<>();

	public CreditCardMaskVendorValidator(CreditCardVendorSpec creditCardVendorSpec) {
		Preconditions.checkNotNull(creditCardVendorSpec, "creditCardVendorSpec is null");
		Preconditions.checkNotNull(creditCardVendorSpec.getStartFromRanges(), "creditCardVendorSpec.startFromRanges is null");
		this.creditCardVendorSpec = creditCardVendorSpec;
		parseMasks(creditCardVendorSpec.getStartFromRanges());
	}

	@Override public boolean validate(String number) {
		ValidatorUtils.checkCreditNumberPreconditions(number);
		for (Map.Entry<Long, Long> entry : maskRangeMap.entrySet()) {
			for (Long i = entry.getKey(); i <= entry.getValue(); i++) {
				if (number.startsWith(""+i)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override public boolean supports(CreditCardVendorType vendorType) {
		return creditCardVendorSpec.getType().equals(vendorType);
	}

	private void parseMasks(List<String> inputMasks) {
		for (String maskRange : inputMasks) {
			String[] masks = maskRange.split("-");
			switch (masks.length) {
				case 1:
					addMask(maskRange);
					break;

				case 2:
					addMask(masks);
					break;

				default:
					throw new IllegalArgumentException("Invalid masks configuration");
			}
		}
	}

	private void addMask(String maskRange) {
		Long rangeNum = Long.parseLong(maskRange);
		maskRangeMap.put(rangeNum, rangeNum);
	}

	private void addMask(String[] maskRange) {
		long min = Long.parseLong(maskRange[0]);
		long max = Long.parseLong(maskRange[1]);
		maskRangeMap.put(min, max);
	}
}
