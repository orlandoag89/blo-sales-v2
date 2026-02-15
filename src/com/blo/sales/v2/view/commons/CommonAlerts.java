package com.blo.sales.v2.view.commons;

import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import javax.swing.JOptionPane;

public final class CommonAlerts {
    
    private CommonAlerts() { }
    
    /**
     * Muestra un panel de error
     * @param msg 
     */
    public static void openError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    /**
     * Muestra un cuadro de dialogo y valida la respuesta true
     * @param msg
     * @return 
     */
    public static boolean showConfirmDialog(String msg) {
        final var rsp = JOptionPane.showConfirmDialog(null, msg);
        return rsp == JOptionPane.YES_OPTION;
    }
    
    /**
     * Solicita una entrada de texto
     * @param msg
     * @return
     * @throws BloSalesV2Exception 
     */
    public static String showMessageDialog(String msg) throws BloSalesV2Exception {
        final var mess = JOptionPane.showInputDialog(null, msg);
        BloSalesV2Utils.validateRule(mess.trim().isBlank(), BloSalesV2Utils.COMMON_RULE_CODE, BloSalesV2Utils.INVALID_TEXT);
        return mess.trim();
    }
    
}
