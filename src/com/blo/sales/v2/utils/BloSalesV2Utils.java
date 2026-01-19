package com.blo.sales.v2.utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Minúsculas: á -> \u00e1 é -> \u00e9 í -> \u00ed ó -> \u00f3 ú -> \u00fa ñ ->
 * \u00f1 ü -> \u00fc
 *
 * Mayúsculas: Á -> \u00c1 É -> \u00c9 Í -> \u00cd Ó -> \u00d3 Ú -> \u00da Ñ ->
 * \u00d1
 *
 * Símbolos: ¿ -> \u00bf ¡ -> \u00a1
 */
public final class BloSalesV2Utils {
    
    /** cadena vacia */
    public static final String EMPTY_STRING = "";
    
    /** expresion regular para solo numeros */
    public static final String ONLY_NUMBERS = "[0-9]+";
    
    public static final String CURRENCY_REGEX = "^\\d+(\\.\\d{1,2})?$";
    
    public static final String SEPARATOR_PAYMENTS = "\\|";
    
    public static final String INVALID_TEXT = "Texto no v\u00e1lido";
    
    /** constante para error en guardado */
    public static final String ERROR_SAVED_ON_DATA_BASE = "Error inesperado guardando en la base de datos";
    
    public static final String ERROR_UPDATING_ON_DATA_BASE = "Error inesperado actualizando el registro";
    
    public static final String ERROR_CATEGORY_NOT_FOUND = "Categor\u00e9a no encontrada";
    
    public static final String ERROR_PRODUCT_NOT_FOUND = "Producto no encontrado";
    
    public static final String USER_NOT_FOUND = "Usuario no encontrado";
    
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
    
    /** genera un item para los pagos parciales */
    public static String getPartialPayment(BigDecimal partialPayment) {
        final var str = new StringBuilder("");
        str.append(partialPayment);
        str.append("TIMESTAMP");
        str.append(getTimestamp());
        str.append(SEPARATOR_PAYMENTS);
        return str.toString();
    }
    
    /**
     * 
     * @param payments
     * @return 
     */
    public static BigDecimal getFirstLastPayment(String payments, BloSalesV2UtilsEnum index) throws BloSalesV2Exception {
        if (payments.trim().isBlank()) {
            return BigDecimal.ZERO;
        }
        final var partialPayments = payments.split(SEPARATOR_PAYMENTS);
        var indexSearch = 0;
        if (index.compareTo(BloSalesV2UtilsEnum.LAST) == 0) {
            indexSearch = partialPayments.length - 1;
        }
        final var payFound = partialPayments[indexSearch].split("TIMESTAMP")[0].trim();
        return new BigDecimal(payFound);
    }
}

