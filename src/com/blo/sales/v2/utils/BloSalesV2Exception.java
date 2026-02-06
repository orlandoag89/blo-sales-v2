package com.blo.sales.v2.utils;

public class BloSalesV2Exception extends Exception {
    
    private String message;
    
    private String code;
    
    public BloSalesV2Exception(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }
    
    @Override
    public String getMessage() {
        return "[" + code + "] " + message;
    }
    
}
