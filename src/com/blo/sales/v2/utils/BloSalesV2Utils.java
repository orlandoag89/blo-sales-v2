package com.blo.sales.v2.utils;

import java.time.LocalDateTime;

public final class BloSalesV2Utils {
    
    public static final String EMPTY_STRING = "";
    
    private BloSalesV2Utils() { }
   
    /**
     * Metodo que valida reglas de negocio y lanza una excepcion
     * @param sentence
     * @param msg
     * @throws BloSalesV2Exception 
     */
    public static void validateRule(boolean sentence, String msg) throws BloSalesV2Exception {
        if (sentence) {
            throw new BloSalesV2Exception(msg);
        }
    }
    
    public static String getTimestamp() {
        return LocalDateTime.now() + "";
    }
}

