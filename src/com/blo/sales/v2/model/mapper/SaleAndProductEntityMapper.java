package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntSaleAndProduct;
import com.blo.sales.v2.model.entities.SaleAndProductEntity;
import com.blo.sales.v2.utils.IToOuter;

public class SaleAndProductEntityMapper implements IToOuter<SaleAndProductEntity, PojoIntSaleAndProduct> {
    
    private static SaleAndProductEntityMapper instance;
    
    private SaleAndProductEntityMapper() { }
    
    public static SaleAndProductEntityMapper getInstance() {
        if (instance == null) {
            instance = new SaleAndProductEntityMapper();
        }
        return instance;
    }

    @Override
    public PojoIntSaleAndProduct toOuter(SaleAndProductEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntSaleAndProduct();
        outer.setIdProduct(inner.getId_product());
        outer.setIdSale(inner.getId_sale());
        outer.setProduct(inner.getProduct());
        outer.setQuantityOnSale(inner.getQuantity_on_sale());
        outer.setTimestamp(inner.getTimestamp());
        outer.setTotalOnSale(inner.getTotal_on_sale());
        outer.setPrice(inner.getPrice());
        outer.setCostOfSale(inner.getCost_of_sale());
        outer.setKg(inner.isKg());
        outer.setProductTotalOnSale(inner.getProduct_total_on_sale());
        return outer;
    }
    
}
