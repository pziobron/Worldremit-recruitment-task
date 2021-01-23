package com.worldremit.creditcard;

import com.worldremit.creditcard.config.GeneralConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreditCardValidationApplication {

    @Autowired
    private GeneralConfig generalConfig;

    public static void main(String[] args) {
        SpringApplication.run(CreditCardValidationApplication.class, args);
    }

}
