package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.model.entities.SaleProductEntity;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class SaleProductEntityMapper implements IToInner<SaleProductEntity, PojoIntSaleProduct>, IToOuter<SaleProductEntity, PojoIntSaleProduct> {
    
    private static SaleProductEntityMapper instance;
    
    private SaleProductEntityMapper() { }
    
    public static SaleProductEntityMapper getInstance() {
        if (instance == null) {
            instance = new SaleProductEntityMapper();
        }
        return instance;
    }

    @Override
    public SaleProductEntity toInner(PojoIntSaleProduct outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new SaleProductEntity();
        inner.setFk_product(outer.getFkProduct());
        inner.setFk_sale(outer.getFkSale());
        inner.setQunatity_sale(outer.getQuantityOnSale());
        inner.setTotal_on_sale(outer.getTotalOnSale());
        inner.setQunatity_sale(outer.getQuantityOnSale());
        inner.setTimestamp(outer.getTimestap());
        return inner;
    }

    @Override
    public PojoIntSaleProduct toOuter(SaleProductEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntSaleProduct();
        outer.setFkProduct(inner.getFk_product());
        outer.setFkSale(inner.getFk_sale());
        outer.setIdSaleProduct(inner.getId_sale_product());
        outer.setQuantityOnSale(inner.getQunatity_sale());
        outer.setTotalOnSale(inner.getTotal_on_sale());
        outer.setTimestap(inner.getTimestamp());
        return outer;
    }
    
}
