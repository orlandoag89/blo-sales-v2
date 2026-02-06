package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntDebtorDetail;
import com.blo.sales.v2.model.entities.DebtorDetailEntity;
import com.blo.sales.v2.utils.IToOuter;

public class DebtorDetailEntityMapper implements IToOuter<DebtorDetailEntity, PojoIntDebtorDetail> {
    
    private static DebtorDetailEntityMapper instance;
    
    private DebtorDetailEntityMapper() { }
    
    public static DebtorDetailEntityMapper getInstance() {
        if (instance == null) {
            instance = new DebtorDetailEntityMapper();
        }
        return instance;
    }

    @Override
    public PojoIntDebtorDetail toOuter(DebtorDetailEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new PojoIntDebtorDetail();
        out.setDebt(inner.getDebt());
        out.setIdDebtor(inner.getId_debtor());
        out.setName(inner.getName());
        out.setPayments(inner.getPayments());
        out.setProduct(inner.getProduct());
        out.setQuantitySale(inner.getQuantity_sale());
        out.setTimestamp(inner.getTimestamp());
        out.setTotalOnSale(inner.getTotal_on_sale());
        return out;
    }
    
}
