package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntCashboxesActivesCosts;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ICashboxesActivesCostsModel {
    
    PojoIntCashboxesActivesCosts addRelationship(PojoIntCashboxesActivesCosts data) throws BloSalesV2Exception;
    
}
