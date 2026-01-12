package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IUserController {
    
    PojoIntLoggedInUser doLogin(PojoIntUser userData) throws BloSalesV2Exception;
    
    PojoIntUser getUserById(int id) throws BloSalesV2Exception;
}
