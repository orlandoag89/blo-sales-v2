package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntActiveCost;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntActivesCosts;
import com.blo.sales.v2.model.entities.ActiveCostEntity;
import com.blo.sales.v2.model.entities.WrapperActivesCostsEntity;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperActivesCostsEntityMapper implements IToInner<WrapperActivesCostsEntity, WrapperPojoIntActivesCosts>, IToOuter<WrapperActivesCostsEntity, WrapperPojoIntActivesCosts> {
    
    private static final ActiveCostEntityMapper mapper = ActiveCostEntityMapper.getInstance();
    
    private static WrapperActivesCostsEntityMapper instance;
    
    private WrapperActivesCostsEntityMapper() { }
    
    public static WrapperActivesCostsEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperActivesCostsEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperActivesCostsEntity toInner(WrapperPojoIntActivesCosts outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new WrapperActivesCostsEntity();
        final var lst = new ArrayList<ActiveCostEntity>();
        if (outer.getActivesCosts() != null && !outer.getActivesCosts().isEmpty()) {
            outer.getActivesCosts().forEach(i -> lst.add(mapper.toInner(i)));
        }
        inner.setActivesCosts(lst);
        return inner;
    }

    @Override
    public WrapperPojoIntActivesCosts toOuter(WrapperActivesCostsEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoIntActivesCosts();
        final var lst = new ArrayList<PojoIntActiveCost>();
        if (inner.getActivesCosts() != null && !inner.getActivesCosts().isEmpty()) {
            inner.getActivesCosts().forEach(i -> lst.add(mapper.toOuter(i)));
        }
        outer.setActivesCosts(lst);
        return outer;
    }
    
}
