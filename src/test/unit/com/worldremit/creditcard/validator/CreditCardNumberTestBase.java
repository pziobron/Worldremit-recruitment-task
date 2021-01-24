package com.worldremit.creditcard.validator;

import com.worldremit.creditcard.validator.vendor.CreditCardVendorSpec;
import com.worldremit.creditcard.validator.vendor.CreditCardVendorType;
import org.junit.Before;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import static com.worldremit.creditcard.validator.vendor.CreditCardVendorType.MASTERCARD;
import static com.worldremit.creditcard.validator.vendor.CreditCardVendorType.VISA;

public abstract class CreditCardNumberTestBase {

    protected Map<CreditCardVendorType, CreditCardVendorSpec> creditCardVendorSpecMap = new EnumMap<>(CreditCardVendorType.class);

    @Before
    public void setUpCreditCardVendorSpecs() {
        creditCardVendorSpecMap.put(
                MASTERCARD,
                generateVendorSpec(MASTERCARD, 16, 16, "2221-2720", "51-55"));
        creditCardVendorSpecMap.put(
                VISA,
                generateVendorSpec(VISA, 13, 16, "4"));
    }

    private CreditCardVendorSpec generateVendorSpec(CreditCardVendorType type,
                                                    int minNumberOfDigits, int maxNumberOfDigits, String... startFromRanges) {
        CreditCardVendorSpec vendorSpec = new CreditCardVendorSpec();
        vendorSpec.setType(type);
        vendorSpec.setMinNumberOfDigits(minNumberOfDigits);
        vendorSpec.setMaxNumberOfDigits(maxNumberOfDigits);
        vendorSpec.setStartFromRanges(Arrays.asList(startFromRanges));
        return vendorSpec;
    }

}
