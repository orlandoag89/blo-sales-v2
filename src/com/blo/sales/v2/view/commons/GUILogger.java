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
        row.append(BloSalesV2Utils.getTimestamp());
        row.append("[");
        row.append(className);
        row.append("]");
        row.append(" ERROR ");
        row.append("-");
        row.append(" ");
        row.append(str);
        row.append("\n");
        Logger.getLogger(className).log(Level.SEVERE, null, str);
    }
    
    public void log(String str) {
        row.append(BloSalesV2Utils.getTimestamp());
        row.append("[");
        row.append(className);
        row.append("]");
        row.append(" INFO ");
        row.append("-");
        row.append(" ");
        row.append(str);
        row.append("\n");
        Logger.getLogger(className).log(Level.INFO, null, str);
    }
    
    public String getLogs() {
        if (row == null) {
            return "[" + BloSalesV2Utils.getTimestamp() + "]";
        }
        return row.toString();
    }
}
