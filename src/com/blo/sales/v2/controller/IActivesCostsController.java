package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.WrapperPojoIntActivesCosts;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IActivesCostsController {
    
    WrapperPojoIntActivesCosts addActiveCost(WrapperPojoIntActivesCosts activeCost) throws BloSalesV2Exception;
    
}
