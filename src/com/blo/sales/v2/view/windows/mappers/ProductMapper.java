package com.blo.sales.v2.view.windows.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.windows.pojos.PojoProduct;

public class ProductMapper implements IToInner<PojoIntProduct, PojoProduct>, IToOuter<PojoIntProduct, PojoProduct> {
    
    private static ProductMapper instance;
    
    private ProductMapper() { }
    
    public static ProductMapper getInstance() {
        if (instance == null) {
            instance = new ProductMapper();
        }
        return instance;
    }

    @Override
    public PojoIntProduct toInner(PojoProduct outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntProduct();
        inner.setIdProduct(outer.getIdProduct());
        inner.setBarCode(outer.getBarCode());
        inner.setCostOfSale(outer.getCostOfSale());
        inner.setFkCategory(outer.getFkCategory());
        inner.setKg(outer.isKg());
        inner.setPrice(outer.getPrice());
        inner.setProduct(outer.getProduct());
        inner.setQuantity(outer.getQuantity());
        return inner;
    }

    @Override
    public PojoProduct toOuter(PojoIntProduct inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoProduct();
        outer.setBarCode(inner.getBarCode());
        outer.setCostOfSale(inner.getCostOfSale());
        outer.setFkCategory(inner.getFkCategory());
        outer.setIdProduct(inner.getIdProduct());
        outer.setKg(inner.isKg());
        outer.setPrice(inner.getPrice());
        outer.setProduct(inner.getProduct());
        outer.setQuantity(inner.getQuantity());
        return outer;
    }
    
}
