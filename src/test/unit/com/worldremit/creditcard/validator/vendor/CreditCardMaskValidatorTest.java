package com.worldremit.creditcard.validator.vendor;

import com.worldremit.creditcard.validator.CreditCardNumberTestBase;
import org.junit.Before;
import org.junit.Test;

import static com.worldremit.creditcard.validator.vendor.CreditCardVendorType.MASTERCARD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CreditCardMaskValidatorTest extends CreditCardNumberTestBase {

    private static final String VALIDATION_ERROR_MSG = String.format("Mask Validation incorrect for %s", MASTERCARD);

    private CreditCardMaskVendorValidator objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new CreditCardMaskVendorValidator(creditCardVendorSpecMap.get(MASTERCARD));
    }

    @Test
    public void testIINWhenCorrect() {
        String number = "5584239583699571";
        assertTrue(VALIDATION_ERROR_MSG, objectUnderTest.validate(number));
    }

    @Test
    public void testIINWhenIncorrect() {
        String number = "7784239583699571";
        assertFalse(VALIDATION_ERROR_MSG, objectUnderTest.validate(number));
    }

}
