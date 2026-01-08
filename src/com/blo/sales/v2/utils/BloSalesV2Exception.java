package com.blo.sales.v2.utils;

public class BloSalesV2Exception extends Exception {
    
    private String message;
    
    public BloSalesV2Exception(String message) {
        super(message);
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
}
