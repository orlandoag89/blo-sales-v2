package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntActiveCost;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IActivesCostsModel {
    
    PojoIntActiveCost addActiveCost(PojoIntActiveCost activeCost) throws BloSalesV2Exception;
    
}
