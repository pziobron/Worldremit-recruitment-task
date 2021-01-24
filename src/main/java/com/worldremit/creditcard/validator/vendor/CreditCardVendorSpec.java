package com.worldremit.creditcard.validator.vendor;

import java.util.ArrayList;
import java.util.List;

public class CreditCardVendorSpec {
	private CreditCardVendorType type;
	private int minNumberOfDigits;
	private int maxNumberOfDigits;
	private List<String> startFromRanges = new ArrayList<>();

	public CreditCardVendorType getType() {
		return type;
	}

	public void setType(CreditCardVendorType type) {
		this.type = type;
	}

	public int getMinNumberOfDigits() {
		return minNumberOfDigits;
	}

	public void setMinNumberOfDigits(int minNumberOfDigits) {
		this.minNumberOfDigits = minNumberOfDigits;
	}

	public int getMaxNumberOfDigits() {
		return maxNumberOfDigits;
	}

	public void setMaxNumberOfDigits(int maxNumberOfDigits) {
		this.maxNumberOfDigits = maxNumberOfDigits;
	}

	public List<String> getStartFromRanges() {
		return startFromRanges;
	}

	public void setStartFromRanges(List<String> startFromRanges) {
		this.startFromRanges = startFromRanges;
	}

	@Override
	public String toString(){
		return type.toString();
	}
}
