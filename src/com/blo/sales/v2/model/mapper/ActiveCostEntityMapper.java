package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntActiveCost;
import com.blo.sales.v2.controller.pojos.enums.ActiveCostIntEnum;
import com.blo.sales.v2.model.entities.ActiveCostEntity;
import com.blo.sales.v2.model.entities.enums.ActivesCostsEntityEnum;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class ActiveCostEntityMapper implements IToInner<ActiveCostEntity, PojoIntActiveCost>, IToOuter<ActiveCostEntity, PojoIntActiveCost> {
    
    private static ActiveCostEntityMapper instance;
    
    private ActiveCostEntityMapper() { }
    
    public static ActiveCostEntityMapper getInstance() {
        if (instance == null) {
            instance = new ActiveCostEntityMapper();
        }
        return instance;
    }

    @Override
    public ActiveCostEntity toInner(PojoIntActiveCost outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new ActiveCostEntity();
        inner.setAmount(outer.getAmount());
        inner.setComplete(outer.isComplete());
        inner.setConcept(outer.getConcept());
        inner.setId_actives_costs(outer.getIdActiveCosts());
        inner.setType(ActivesCostsEntityEnum.valueOf(outer.getType().name()));
        return inner;
    }

    @Override
    public PojoIntActiveCost toOuter(ActiveCostEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoIntActiveCost();
        outer.setAmount(inner.getAmount());
        outer.setComplete(inner.isComplete());
        outer.setConcept(inner.getConcept());
        outer.setIdActiveCosts(inner.getId_actives_costs());
        outer.setType(ActiveCostIntEnum.valueOf(inner.getType().name()));
        return outer;
    }
    
}
