package com.blo.sales.v2.view.windows.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.windows.pojos.PojoProduct;

public class ProductMapper implements IToInner<PojoIntProduct, PojoProduct>, IToOuter<PojoIntProduct, PojoProduct> {

    @Override
    public PojoIntProduct toInner(PojoProduct outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntProduct();
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
