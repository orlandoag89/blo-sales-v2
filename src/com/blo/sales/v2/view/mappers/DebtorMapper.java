package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoDebtor;

public class DebtorMapper implements IToInner<PojoIntDebtor, PojoDebtor>, IToOuter<PojoIntDebtor, PojoDebtor> {
    
    private static DebtorMapper instance;
    
    private DebtorMapper() { }
    
    public static DebtorMapper getInstance() {
        if (instance == null) {
            instance = new DebtorMapper();
        }
        return instance;
    }

    @Override
    public PojoIntDebtor toInner(PojoDebtor outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntDebtor();
        inner.setIdDebtor(outer.getIdDebtor());
        inner.setName(outer.getName());
        inner.setPayments(outer.getPayments());
        inner.setTotal(outer.getTotal());
        return inner;
    }

    @Override
    public PojoDebtor toOuter(PojoIntDebtor inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoDebtor();
        outer.setIdDebtor(inner.getIdDebtor());
        outer.setName(inner.getName());
        outer.setPayments(inner.getPayments());
        outer.setTotal(inner.getTotal());
        return outer;
    }
    
}
