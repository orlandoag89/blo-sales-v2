package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntSaleAndProduct;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoSaleAndProduct;

public class PojoSaleAndProductMapper implements IToOuter<PojoIntSaleAndProduct, PojoSaleAndProduct>{
    
    private static PojoSaleAndProductMapper instance;
    
    private PojoSaleAndProductMapper() { }
    
    public static PojoSaleAndProductMapper getInstance() {
        if (instance == null) {
            instance = new PojoSaleAndProductMapper();
        }
        return instance;
    }

    @Override
    public PojoSaleAndProduct toOuter(PojoIntSaleAndProduct inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoSaleAndProduct();
        outer.setIdProduct(inner.getIdProduct());
        outer.setIdSale(inner.getIdSale());
        outer.setProduct(inner.getProduct());
        outer.setQuantityOnSale(inner.getQuantityOnSale());
        outer.setTotalOnSale(inner.getTotalOnSale());
        outer.setTimestamp(inner.getTimestamp());
        outer.setCostOfSale(inner.getCostOfSale());
        outer.setPrice(inner.getPrice());
        outer.setKg(inner.isKg());
        return outer;
    }
}
