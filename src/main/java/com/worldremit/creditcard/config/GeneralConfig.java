package com.worldremit.creditcard.config;

import com.google.common.collect.ImmutableList;
import com.worldremit.creditcard.validator.CreditCardNumberValidator;
import com.worldremit.creditcard.validator.common.CreditCardLuhnAlgorithmValidator;
import com.worldremit.creditcard.validator.vendor.CreditCardMaskVendorValidator;
import com.worldremit.creditcard.validator.vendor.CreditCardNumberLengthVendorValidator;
import com.worldremit.creditcard.validator.vendor.CreditCardNumberVendorValidator;
import com.worldremit.creditcard.validator.vendor.CreditCardVendorSpec;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class GeneralConfig {

    private List<CreditCardVendorSpec> creditCardVendorSpecs = new ArrayList<>();

    private List<CreditCardNumberVendorValidator> creditCardNumberVendorValidators = Collections.emptyList();

    private List<CreditCardNumberValidator> creditCardNumberCommonValidators = Collections.emptyList();

    public List<CreditCardVendorSpec> getCreditCardVendorSpecs() {
        return creditCardVendorSpecs;
    }

    public List<CreditCardNumberValidator> getCreditCardNumberCommonValidators() {
        return creditCardNumberCommonValidators;
    }

    public List<CreditCardNumberVendorValidator> getCreditCardNumberVendorValidators() {
        return creditCardNumberVendorValidators;
    }

    @PostConstruct
    private void initializeValidators() {
        creditCardNumberCommonValidators = initializeCommonValidators();
        creditCardNumberVendorValidators = initializeVendorSpecValidators();
    }

    private List<CreditCardNumberValidator> initializeCommonValidators() {
        return ImmutableList.of(new CreditCardLuhnAlgorithmValidator());
    }

    private List<CreditCardNumberVendorValidator> initializeVendorSpecValidators() {
        ImmutableList.Builder<CreditCardNumberVendorValidator> builder = new ImmutableList.Builder<>();
        for (CreditCardVendorSpec vendorSpec : creditCardVendorSpecs) {
            builder.add(new CreditCardNumberLengthVendorValidator(vendorSpec));
            builder.add(new CreditCardMaskVendorValidator(vendorSpec));
        }
        return builder.build();
    }

}