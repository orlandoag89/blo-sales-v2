package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntDebtorDetail;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtorsDetails;
import com.blo.sales.v2.model.entities.WrapperDebtorsDetailsEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperDebtorsDetailsEntityMapper implements IToOuter<WrapperDebtorsDetailsEntity, WrapperPojoIntDebtorsDetails> {
    
    private static WrapperDebtorsDetailsEntityMapper instance;
    
    private static final DebtorDetailEntityMapper mapper = DebtorDetailEntityMapper.getInstance();
    
    private WrapperDebtorsDetailsEntityMapper() { }
    
    public static WrapperDebtorsDetailsEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperDebtorsDetailsEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntDebtorsDetails toOuter(WrapperDebtorsDetailsEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new WrapperPojoIntDebtorsDetails();
        final var items = new ArrayList<PojoIntDebtorDetail>();
        
        if (inner.getDebtors() != null && !inner.getDebtors().isEmpty()) {
            inner.getDebtors().forEach(d -> items.add(mapper.toOuter(d)));
        }
        out.setDebtors(items);
        return out;
        
    }
    
}
