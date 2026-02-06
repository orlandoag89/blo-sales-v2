package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import com.blo.sales.v2.model.entities.SaleEntity;
import com.blo.sales.v2.model.entities.enums.SaleStatusEntityEnum;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class SaleEntityMapper implements IToInner<SaleEntity, PojoIntSale>, IToOuter<SaleEntity, PojoIntSale> {

    private static SaleEntityMapper instance;
    
    private SaleEntityMapper() { }
    
    public static SaleEntityMapper getInstance() {
        if (instance == null) {
            instance = new SaleEntityMapper();
        }
        return instance;
    }
    
    @Override
    public SaleEntity toInner(PojoIntSale outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new SaleEntity();
        inner.setId_sale(outer.getIdSale());
        inner.setSales_status(SaleStatusEntityEnum.valueOf(outer.getSaleStatus().name()));
        inner.setTotal(outer.getTotal());
        inner.setTimestamp(outer.getTimestamp());
        return inner;
    }

    @Override
    public PojoIntSale toOuter(SaleEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntSale();
        outer.setIdSale(inner.getId_sale());
        outer.setSaleStatus(SalesStatusIntEnum.valueOf(inner.getSale_status().name()));
        outer.setTotal(inner.getTotal());
        outer.setTimestamp(inner.getTimestamp());
        return outer;
    }
    
}
