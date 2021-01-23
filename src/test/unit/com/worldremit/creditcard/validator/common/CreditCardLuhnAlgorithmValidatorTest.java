package com.worldremit.creditcard.validator.common;

import com.worldremit.creditcard.validator.CreditCardNumberTestBase;
import org.junit.Before;
import org.junit.Test;

import static com.worldremit.creditcard.validator.vendor.CreditCardVendorType.MASTERCARD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreditCardLuhnAlgorithmValidatorTest extends CreditCardNumberTestBase {

    private static final String VALIDATION_ERROR_MSG = String.format("Luhn Algorithm Validation incorrect for %s", MASTERCARD);

    private CreditCardLuhnAlgorithmValidator objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest =  new CreditCardLuhnAlgorithmValidator();
    }

    @Test
    public void testLuhnAlgorithmWhenCorrect() {
        String number = "5584239583699571";
        assertTrue(VALIDATION_ERROR_MSG, objectUnderTest.validate(number));
    }

    @Test
    public void testLuhnAlgorithmWhenIncorrect() {
        String number = "5584239583611111";
        assertFalse(VALIDATION_ERROR_MSG, objectUnderTest.validate(number));
    }

}
