package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.model.entities.DebtorEntity;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class DebtorEntityMapper implements IToInner<DebtorEntity, PojoIntDebtor>, IToOuter<DebtorEntity, PojoIntDebtor> {
    
    private static DebtorEntityMapper instance;
    
    private DebtorEntityMapper() { }
    
    public static DebtorEntityMapper getInstance() {
        if (instance == null) {
            instance = new DebtorEntityMapper();
        }
        return instance;
    }

    @Override
    public DebtorEntity toInner(PojoIntDebtor outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new DebtorEntity();
        inner.setId_debtor(outer.getIdDebtor());
        inner.setPayments(outer.getPayments());
        inner.setDebt(outer.getDebt());
        inner.setName(outer.getName());
        return inner;
    }

    @Override
    public PojoIntDebtor toOuter(DebtorEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntDebtor();
        outer.setIdDebtor(inner.getId_debtor());
        outer.setName(inner.getName());
        outer.setPayments(inner.getPayments());
        outer.setDebt(inner.getDebt());
        return outer;
    }
    
}
