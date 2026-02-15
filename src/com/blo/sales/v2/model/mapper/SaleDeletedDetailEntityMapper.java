package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntSaleDeletedDetail;
import com.blo.sales.v2.model.entities.SaleDeletedDetailEntity;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class SaleDeletedDetailEntityMapper implements 
        IToInner<SaleDeletedDetailEntity, PojoIntSaleDeletedDetail>, IToOuter<SaleDeletedDetailEntity, PojoIntSaleDeletedDetail> {
    
    private static SaleDeletedDetailEntityMapper instance;
    
    private SaleDeletedDetailEntityMapper() {}
    
    public static SaleDeletedDetailEntityMapper getInstance() {
        if (instance == null) {
            instance = new SaleDeletedDetailEntityMapper();
        }
        return instance;
    }

    @Override
    public SaleDeletedDetailEntity toInner(PojoIntSaleDeletedDetail outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new SaleDeletedDetailEntity();
        inner.setFk_sale_product(outer.getFkSaleProduct());
        inner.setFk_user(outer.getFkUser());
        inner.setId_sale_deleted(outer.getIdSaleDeleted());
        inner.setReason(outer.getReason());
        inner.setTimestamp(outer.getTimestamp());
        return inner;
    }

    @Override
    public PojoIntSaleDeletedDetail toOuter(SaleDeletedDetailEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntSaleDeletedDetail();
        outer.setFkSaleProduct(inner.getFk_sale_product());
        outer.setFkUser(inner.getFk_user());
        outer.setIdSaleDeleted(inner.getId_sale_deleted());
        outer.setReason(inner.getReason());
        outer.setTimestamp(inner.getTimestamp());
        return outer;
    }
    
}
