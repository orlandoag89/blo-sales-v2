/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.enums.CashboxStatusIntEnum;
import com.blo.sales.v2.model.entities.CashboxEntity;
import com.blo.sales.v2.model.entities.enums.CashboxEntityEnum;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

/**
 *
 * @author Orlando
 */
public class CashboxEntityMapper implements IToInner<CashboxEntity, PojoIntCashbox>, IToOuter<CashboxEntity, PojoIntCashbox> {
    
    public static CashboxEntityMapper instance;
    
    private CashboxEntityMapper() { }
    
    public static CashboxEntityMapper getInstance() {
        if (instance == null) {
            instance = new CashboxEntityMapper();
        }
        return instance;
    }

    @Override
    public CashboxEntity toInner(PojoIntCashbox outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new CashboxEntity();
        inner.setFk_user(outer.getFkUser());
        inner.setId_cashbox(outer.getIdCashbox());
        inner.setAmount(outer.getAmount());
        inner.setStatus(CashboxEntityEnum.valueOf(outer.getStatus().name()));
        inner.setTimestamp(outer.getTimestamp());
        return inner;
    }

    @Override
    public PojoIntCashbox toOuter(CashboxEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntCashbox();
        outer.setFkUser(inner.getFk_user());
        outer.setIdCashbox(inner.getId_cashbox());
        outer.setAmount(inner.getAmount());
        outer.setStatus(CashboxStatusIntEnum.valueOf(inner.getStatus().name()));
        outer.setTimestamp(inner.getTimestamp());
        return outer;
    }
    
}
