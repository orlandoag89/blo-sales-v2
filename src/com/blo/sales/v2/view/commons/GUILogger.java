package com.blo.sales.v2.view.commons;

import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class GUILogger {

    private static StringBuilder row;
    
    private static String className;
    
    private static GUILogger instance;
    
    private GUILogger() {
        if (row == null) {
            row = new StringBuilder();
        }
    }
    
    public static GUILogger getLogger(String className) {
        if (instance == null) {
            instance = new GUILogger();
        }
        GUILogger.className = className;
        return instance;
    }
    
    public void error(String str) {
        final var _str = String.format("%s [%s] ERROR - %s \n", BloSalesV2Utils.getTimestamp(), className, str);
        row.append(_str);
        Logger.getLogger(className).log(Level.SEVERE, _str);
    }
    
    public void log(String str) {
        final var _str = String.format("%s [%s] INFO - %s \n", BloSalesV2Utils.getTimestamp(), className, str);
        row.append(_str);
        Logger.getLogger(className).log(Level.INFO, _str);
    }
    
    public String getLogs() {
        if (row == null) {
            return "[" + BloSalesV2Utils.getTimestamp() + "]";
        }
        return row.toString();
    }
}
