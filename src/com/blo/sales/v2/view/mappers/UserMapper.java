package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.view.pojos.PojoUser;

public class UserMapper implements IToInner<PojoIntUser, PojoUser> {
    
    private static UserMapper instance;
    
    private UserMapper() { }
    
    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    @Override
    public PojoIntUser toInner(PojoUser outer) {
        if (outer == null) {
            return null;
        }
        return new PojoIntUser(outer.getUsername(), outer.getPassword());
    }
    
}
