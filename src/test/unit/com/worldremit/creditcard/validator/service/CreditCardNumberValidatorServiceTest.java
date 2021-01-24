package com.worldremit.creditcard.validator.service;

import com.google.common.collect.ImmutableList;
import com.worldremit.creditcard.config.CreditCardValidators;
import com.worldremit.creditcard.validator.CreditCardNumberTestBase;
import com.worldremit.creditcard.validator.common.CreditCardLuhnAlgorithmValidator;
import com.worldremit.creditcard.validator.vendor.CreditCardMaskVendorValidator;
import com.worldremit.creditcard.validator.vendor.CreditCardNumberLengthVendorValidator;
import com.worldremit.creditcard.validator.vendor.CreditCardNumberVendorValidator;
import com.worldremit.creditcard.validator.vendor.CreditCardVendorSpec;
import com.worldremit.creditcard.validator.vendor.CreditCardVendorType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.worldremit.creditcard.validator.vendor.CreditCardVendorType.MASTERCARD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class CreditCardNumberValidatorServiceTest extends CreditCardNumberTestBase  {

    private static final String VALIDATION_ERROR_MSG = String.format("Validation incorrect for %s", MASTERCARD);

    private CreditCardVendorType cardVendorType;
    private List<String> validNumbers;
    private List<String> invalidNumbers;

    @Mock
    private CreditCardValidators creditCardValidators;

    @InjectMocks
    private CreditCardNumberValidatorService objectUnderTest;

    public CreditCardNumberValidatorServiceTest(CreditCardVendorType cardVendorType, List<String> validNumbers, List<String> invalidNumbers) {
        this.cardVendorType = cardVendorType;
        this.validNumbers = validNumbers;
        this.invalidNumbers = invalidNumbers;
    }

    @Before
    public void setUp() {
        objectUnderTest = new CreditCardNumberValidatorService();
        MockitoAnnotations.initMocks(this);
        when(creditCardValidators.getCommonValidators()).thenReturn(ImmutableList.of(new CreditCardLuhnAlgorithmValidator()));
        ImmutableList.Builder<CreditCardNumberVendorValidator> builder = new ImmutableList.Builder<>();
        for (CreditCardVendorSpec vendorSpec : creditCardVendorSpecMap.values()) {
            builder.add(new CreditCardNumberLengthVendorValidator(vendorSpec));
            builder.add(new CreditCardMaskVendorValidator(vendorSpec));
        }
        when(creditCardValidators.getVendorSpecificValidators()).thenReturn(builder.build());
    }

    @Test
    public void testServiceValidation() {
        assertTrue(VALIDATION_ERROR_MSG, validNumbers.stream()
                .allMatch(validNumber -> objectUnderTest.validate(cardVendorType, validNumber)));
        assertFalse(VALIDATION_ERROR_MSG, invalidNumbers.stream()
                .allMatch(validNumber -> objectUnderTest.validate(cardVendorType, validNumber)));
    }

    @Parameterized.Parameters(name = "Test Credit Card Vendor {0} with valid numbers = {1} and invalid numbers = {2}")
    public static List<Object[]> testData() {
        return ImmutableList.of(new Object[]{
                MASTERCARD, ImmutableList.of("5584239583699571"), ImmutableList.of("2584239583699571")});
    }

}
