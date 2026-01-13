package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.model.IUserModel;
import com.blo.sales.v2.model.impl.UserModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class UserControllerImpl implements IUserController {
    
    private IUserModel userModel;
    
    private static UserControllerImpl instance;
    
    private UserControllerImpl() {
        userModel = UserModelImpl.getInstance();
    }

    public static UserControllerImpl getInstance() {
        if (instance == null) {
            instance = new UserControllerImpl();
        }
        return instance;
    }
    
    @Override
    public PojoIntLoggedInUser doLogin(PojoIntUser userData) throws BloSalesV2Exception {
        return userModel.doLogin(userData);
    }

    @Override
    public PojoIntUser getUserById(long id) throws BloSalesV2Exception {
        return userModel.getUserById(id);
    }
    
}
