package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtors;
import com.blo.sales.v2.model.entities.WrapperDebtorsEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperDebtorsEntityMapper implements IToOuter<WrapperDebtorsEntity, WrapperPojoIntDebtors> {
    
    private static WrapperDebtorsEntityMapper instance;
    
    private DebtorEntityMapper mapper;
    
    private WrapperDebtorsEntityMapper() {
        mapper = DebtorEntityMapper.getInstance();
    }
    
    public static WrapperDebtorsEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperDebtorsEntityMapper();
        }
        return instance;
    }
    
    @Override
    public WrapperPojoIntDebtors toOuter(WrapperDebtorsEntity inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoIntDebtors();
        final var items = new ArrayList<PojoIntDebtor>();
        
        if (inner.getDebtors() != null && !inner.getDebtors().isEmpty()) {
            inner.getDebtors().forEach(i -> items.add(mapper.toOuter(i)));
        }
        
        outer.setDebtors(items);
        return outer;
    }
    
}
