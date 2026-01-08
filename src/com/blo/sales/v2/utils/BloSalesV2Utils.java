package com.blo.sales.v2.utils;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
* Minúsculas:
* á -> \u00e1
* é -> \u00e9
* í -> \u00ed
* ó -> \u00f3
* ú -> \u00fa
* ñ -> \u00f1
* ü -> \u00fc
* 
* Mayúsculas:
* Á -> \u00c1
* É -> \u00c9
* Í -> \u00cd
* Ó -> \u00d3
* Ú -> \u00da
* Ñ -> \u00d1
* 
* Símbolos:
* ¿ -> \u00bf
* ¡ -> \u00a1
 */
public final class BloSalesV2Utils {
    
    private static final String INVALID_TEXT = "Texto no v\u00e1lido";
    
    private BloSalesV2Utils() { }
    
    /**
     * Recupera el texto de un text field
     * @param field
     * @return 
     */
    public static String getTextFromJText(JTextField field) throws BloSalesV2Exception {
        final var text = field.getText().trim();
        validateRule(text.isBlank(), INVALID_TEXT);
        return text;
    }
    
    /**
     * Recupera el valor de una password
     * @param field
     * @return
     * @throws BloSalesV2Exception 
     */
    public static String getPasswordFromJText(JPasswordField field) throws BloSalesV2Exception {
        final var passGet = field.getPassword();
        final var pass = new String(passGet);
        validateRule(pass.isBlank(), INVALID_TEXT);
        return pass;
    }
    
    public static void validateRule(boolean sentence, String msg) throws BloSalesV2Exception {
        if (sentence) {
            throw new BloSalesV2Exception(msg);
        }
    }
}
