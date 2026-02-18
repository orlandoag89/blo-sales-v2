package com.blo.sales.v2.view.utils;

import com.blo.sales.v2.view.pojos.PojoProduct;
import com.blo.sales.v2.view.utils.handler.ManagementProductInfoStoreHandler;
import com.blo.sales.v2.view.utils.handler.ManagementProductStoreHandler;

public class GUIStore {
    
    private static PojoProduct product = new PojoProduct();
    
    private static GUIStore instance;
    
    private GUIStore() { }
    
    public static GUIStore getInstance() {
        if (instance == null) {
            instance = new GUIStore();
        }
        return instance;
    }
    
    // product datos
    public void addPropOnPojoProduct(ManagementProductStoreHandler prop, String value) {
        prop.apply(product, value);
    }
    
    public static void resetProductData() {
        product = null;
        product = new PojoProduct();
    }
    
    public static PojoProduct getProductData() {
        return product;
    }
}
