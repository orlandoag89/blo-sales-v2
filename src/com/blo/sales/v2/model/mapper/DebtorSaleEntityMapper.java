package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.model.entities.DebtorSaleEntity;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class DebtorSaleEntityMapper implements IToInner<DebtorSaleEntity, PojoIntDebtorSale>, IToOuter<DebtorSaleEntity, PojoIntDebtorSale> {

    private static DebtorSaleEntityMapper instance;
    
    private DebtorSaleEntityMapper() { }
    
    public static DebtorSaleEntityMapper getInstance() {
        if (instance == null) {
            instance = new DebtorSaleEntityMapper();
        }
        return instance;
    }
    
    @Override
    public DebtorSaleEntity toInner(PojoIntDebtorSale outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new DebtorSaleEntity();
        inner.setFk_debtor(outer.getFkDebtor());
        inner.setFk_sale(outer.getFkSale());
        inner.setId_debtor_sale(outer.getIdDebtorSale());
        inner.setTimestamp(outer.getTimestamp());
        return inner;
    }

    @Override
    public PojoIntDebtorSale toOuter(DebtorSaleEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntDebtorSale();
        outer.setFkDebtor(inner.getFk_debtor());
        outer.setFkSale(inner.getFk_sale());
        outer.setIdDebtorSale(inner.getId_debtor_sale());
        outer.setTimestamp(inner.getTimestamp());
        return outer;
    }
    
}
