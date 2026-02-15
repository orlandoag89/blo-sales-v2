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
    
    public static final String VERSION = "v2.5.7-SNAPSHOT";
    
    /** cadena vacia */
    public static final String EMPTY_STRING = "";
    
    /** expresion regular para solo numeros */
    public static final String ONLY_NUMBERS = "[0-9]+";
    
    public static final String CURRENCY_REGEX = "^\\d+(\\.\\d{1,2})?$";
    
    public static final String SEPARATOR_PAYMENTS = ",";
    
    public static final String INVALID_TEXT = "Texto no v\u00e1lido";
    
    /** constante para error en guardado */
    public static final String ERROR_SAVED_ON_DATA_BASE = "Error inesperado guardando en la base de datos";
    
    public static final String ERROR_UPDATING_ON_DATA_BASE = "Error inesperado actualizando el registro";
    
    public static final String ERROR_DELETING_DATA_ON_DATA_BASE = "Error inesperado borrando registro";
    
    public static final String ERROR_CATEGORY_NOT_FOUND = "Categor\u00eda no encontrada";
    
    public static final String ERROR_PRODUCT_NOT_FOUND = "Producto no encontrado";
    
    public static final String SALES_PRODUCT_NOT_FOUND = "Relaci\u00f3n venta - producto no encontrada";
    
    public static final String ERROR_CASHBOX_NOT_DEVOLUTION = "Actualmente no hay una caja abierta.\nNo se permite hacer devoluci\u00f3n sin caja abierta.";
    
    public static final String USER_NOT_FOUND = "Usuario no encontrado";
    
    public static final String PRODUCT_NOT_FOUND = " no encontrado";
    
    public static final String PRODUCT_INSUFFICIENT = " insuficientes";
    
    public static final String DEBTOR_NOT_FOUND = "Deudor no encontrado";
    
    public static final String CATEGORY_NO_SELECTED = "Categor\u00eda no seleccionada";
    
    public static final String NOTE_TYPE_NO_SELECTED = "Selecciona un tipo de nota";
    
    public static final String CATEGORY_NOT_FOUND = "Categor\u00eda no encontrada";
    
    public static final String CATEGORY_NOT_SELECTED = "Categor\u00eda no seleccionada";
    
    public static final String PRODUCT_NOT_SELECTED = "El producto no ha sido seleccionado o no existe en el inventario";
    
    public static final String BAR_CODE_EXCEPTION = "El c\u00f3digo de barras ya est\u00e1 registrado con otro producto, por favor verifica";
    
    public static final String CATEGORY_PROTECTED = "Esta categor\u00eda est\u00e1 protegida";
    
    public static final String NOTES_INSTRUCTIONS = "¡Importante! \nRecuerda que si tu nota se cargar\u00e1 como activo / pasivo \ndebes colocar la cantidad comenzando por $";
    
    public static final String SQL_EXCEPTION_MESSAGE = "¡Ups! Algo sali\u00f3 mal. Por favor, inténtalo de nuevo más tarde.";
    
    public static final String REPORT_EXCEPTIONS = "¡Ups! Algo sali\u00f3 mal guardando archivo de reporte";
    
    public static final String NOT_PRICES_HISTORY = "No hay historial a\u00fan";
    
    public static final String ERROR_SALES_STOCK_EMPTY = "No hay relaci\u00f3n entre venta y producto";
    
    public static final long DEBTORS_PAYMENTS = 1;
    
    /** codigos de error */
    public static final String COMMON_RULE_CODE = "000";
    
    public static final String SQL_EXCEPTION_CODE = "001";
    
    public static final String SQL_ADD_EXCEPTION_CODE = "002";
    
    public static final String SQL_UPDATE_EXCEPTION_CODE = "003";
    
    public static final String SQL_DELETE_EXCEPTION_CODE = "004";
    
    public static final String CODE_CATEGORY_NOT_FOUND = "005";
    
    public static final String CODE_PRODUCT_NOT_FOUND = "006";
    
    public static final String CODE_USER_NOT_FOUND = "007";
    
    public static final String CODE_DEBTOR_NOT_FOUND = "008";
    
    public static final String CODE_CATEGORY_PROTECTED = "009";
    
    public static final String CODE_BAR_CODE_REGISTERED = "010";
    
    public static final String CODE_PRODUCT_INSUFFICIENT = "011";
    
    public static final String CODE_NOTE_TYPE_NOT_SELECTED = "012";
    
    public static final String CODE_CATEGORY_NOT_SELECTED = "013";
    
    public static final String CODE_NOTE_TYPE_NO_SELECTED = "014";
    
    public static final String CODE_PRODUCT_NOT_SELECTED = "015";
    
    public static final String CODE_SALES_PRODUCT_NOT_FOUND = "016";
    
    public static final String CODE_CASHBOX_NOT_DEVOLUTION = "017";
    
    public static final String CODE_SALES_STOCK_EMPTY = "016";
    
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    
    private BloSalesV2Utils() { }
   
    /**
     * Metodo que valida reglas de negocio y lanza una excepcion
     * @param sentence
     * @param msg
     * @throws BloSalesV2Exception 
     */
    public static void validateRule(boolean sentence, String code, String msg) throws BloSalesV2Exception {
        if (sentence) {
            throw new BloSalesV2Exception(code, msg);
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
    
    public static String parserTimeStamp(String date) {
        final var fullDateTime = LocalDateTime.parse(date);
        final var onlyDate = fullDateTime.toLocalDate();
        return onlyDate.toString();
    }
    
}




