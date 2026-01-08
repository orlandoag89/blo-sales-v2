package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.beans.BeanIntLoggedInUser;
import com.blo.sales.v2.controller.beans.BeanIntUser;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class UserControllerImpl implements IUserController {

    @Override
    public BeanIntLoggedInUser doLogin(BeanIntUser userData) throws BloSalesV2Exception {
        System.err.println(userData.getPassword() + "  " + userData.getUserName());
        return null;
    }
    
}
