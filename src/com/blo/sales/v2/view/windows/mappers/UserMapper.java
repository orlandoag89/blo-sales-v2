package com.blo.sales.v2.view.windows.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.view.windows.pojos.PojoUser;

public class UserMapper implements IToInner<PojoIntUser, PojoUser> {

    @Override
    public PojoIntUser toInner(PojoUser outer) {
        if (outer == null) {
            return null;
        }
        return new PojoIntUser(outer.getUsername(), outer.getPassword());
    }
    
}
