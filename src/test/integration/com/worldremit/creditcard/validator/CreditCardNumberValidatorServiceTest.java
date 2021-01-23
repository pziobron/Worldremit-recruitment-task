package com.worldremit.creditcard.validator;

import com.worldremit.creditcard.CreditCardValidationApplication;
import com.worldremit.creditcard.validator.service.CreditCardNumberValidatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.worldremit.creditcard.validator.vendor.CreditCardVendorType.MASTERCARD;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CreditCardValidationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCardNumberValidatorServiceTest {

    private static final String VALIDATION_ERROR_MSG = String.format("Credit Card Service Validation incorrect for %s", MASTERCARD);

    @Autowired
    private CreditCardNumberValidatorService creditCardNumberValidatorService;

    @Test
    public void testCreditCardNumberValidatorService() {
        String number = "5584239583699571";
        assertTrue(VALIDATION_ERROR_MSG, creditCardNumberValidatorService.validate(MASTERCARD, number));
    }
}
