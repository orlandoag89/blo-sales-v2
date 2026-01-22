package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntActiveCost;
import com.blo.sales.v2.controller.pojos.enums.ActiveCostIntEnum;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.view.pojos.PojoActiveCost;

public class PojoActiveCostMapper implements IToInner<PojoIntActiveCost, PojoActiveCost> {
    
    private static PojoActiveCostMapper instance;
    
    private PojoActiveCostMapper() { }
    
    public static PojoActiveCostMapper getInstance() {
        if (instance == null) {
            instance = new PojoActiveCostMapper();
        }
        return instance;
    }

    @Override
    public PojoIntActiveCost toInner(PojoActiveCost outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntActiveCost();
        inner.setAmount(outer.getAmount());
        inner.setComplete(outer.isComplete());
        inner.setConcept(outer.getConcept());
        inner.setIdActiveCosts(outer.getIdActiveCosts());
        inner.setType(ActiveCostIntEnum.valueOf(outer.getType().name()));
        return inner;
    }
    
}
