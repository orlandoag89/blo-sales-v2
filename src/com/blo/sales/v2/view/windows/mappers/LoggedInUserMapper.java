package com.blo.sales.v2.view.windows.mappers;

import com.blo.sales.v2.controller.beans.BeanIntLoggedInUser;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.windows.beans.BeanLoggedInUser;
import com.blo.sales.v2.view.windows.beans.enums.RolesEnum;

public class LoggedInUserMapper implements IToOuter<BeanIntLoggedInUser, BeanLoggedInUser> {

    @Override
    public BeanLoggedInUser toOuter(BeanIntLoggedInUser inner) {
        if (inner == null) {
            return null;
        }
        final var out = new BeanLoggedInUser();
        out.setRole(RolesEnum.valueOf(inner.getRole().name()));
        out.setUsername(inner.getUsername());
        return out;
    }
    
}
