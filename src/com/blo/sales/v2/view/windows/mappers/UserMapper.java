package com.blo.sales.v2.view.windows.mappers;

import com.blo.sales.v2.controller.beans.BeanIntUser;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.view.windows.beans.BeanUser;

public class UserMapper implements IToInner<BeanIntUser, BeanUser> {

    @Override
    public BeanIntUser toInner(BeanUser outer) {
        if (outer == null) {
            return null;
        }
        return new BeanIntUser(outer.getUsername(), outer.getPassword());
    }
    
}
