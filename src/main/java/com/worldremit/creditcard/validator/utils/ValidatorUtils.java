package com.worldremit.creditcard.validator.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.math.NumberUtils;

public class ValidatorUtils {

    private  ValidatorUtils() {}

    public static void checkCreditNumberPreconditions(String number) {
        Preconditions.checkArgument(number != null, "number is null");
        Preconditions.checkArgument(NumberUtils.isDigits(number), "the number value: %s should contain only digits !", number);
    }

}
