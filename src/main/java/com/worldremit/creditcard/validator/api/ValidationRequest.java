package com.worldremit.creditcard.validator.api;

public class ValidationRequest {
    private String vendorType;
    private String number;

    public String getVendorType() {
        return vendorType;
    }

    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
