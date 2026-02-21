package com.blo.sales.v2.plugins.writter.enums;

public enum ExtensionEnum {
    
    TXT(".txt"), LOG(".log");
    
    private final String extension;
    
    private ExtensionEnum(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

}
