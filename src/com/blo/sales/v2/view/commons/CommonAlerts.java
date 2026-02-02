package com.blo.sales.v2.view.commons;

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
    
}
