package com.worldremit.creditcard.validator.vendor;

import com.worldremit.creditcard.validator.CreditCardNumberTestBase;
import org.junit.Before;
import org.junit.Test;

import static com.worldremit.creditcard.validator.vendor.CreditCardVendorType.MASTERCARD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreditCardNumberLengthValidatorTest extends CreditCardNumberTestBase {

    private static final String VALIDATION_ERROR_MSG = String.format("Number Length Validation incorrect for %s", MASTERCARD);

    private CreditCardNumberLengthVendorValidator objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new CreditCardNumberLengthVendorValidator(creditCardVendorSpecMap.get(
                MASTERCARD));
    }

    @Test
    public void testLengthOfNumberWhenCorrect() {
        String number = "5584239583699571";
        assertTrue(VALIDATION_ERROR_MSG, objectUnderTest.validate(number));
    }

    @Test
    public void testLengthOfNumberWhenIncorrect() {
        String number = "55842395";
        assertFalse(VALIDATION_ERROR_MSG, objectUnderTest.validate(number));
    }

}
