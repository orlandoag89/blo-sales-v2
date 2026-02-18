package com.blo.sales.v2.view.utils.handler;

import java.util.function.BiConsumer;

public enum ManagementProductInfoStoreHandler {
    
    ID_PRODUCT((p, v) -> p[0] = v);
    
    private final BiConsumer<Object[], Object> setter;
    
    ManagementProductInfoStoreHandler(BiConsumer<Object[], Object> setter) {
        this.setter = setter;
    }
    
    public void apply(Object[] args, Object value) {
        setter.accept(args, value);
    }
}
