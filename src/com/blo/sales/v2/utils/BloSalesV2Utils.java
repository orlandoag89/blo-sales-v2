package com.blo.sales.v2.utils;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public final class BloSalesV2Utils {
    
    public static final String EMPTY_STRING = "";
    
    public static final String ONLY_NUMBERS = "[0-9]+";
    
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
    
    public static boolean validateTextWithPattern(String pattern, String txt) {
        final var patternCompile = Pattern.compile(pattern);
        final var matcher = patternCompile.matcher(txt);
        return matcher.find();
    }
}

