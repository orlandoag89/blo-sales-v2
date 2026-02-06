package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IDebtorsSalesModel {
    
    PojoIntDebtorSale addRelationship(PojoIntDebtorSale debtor) throws BloSalesV2Exception;
    
    void deleteRelationhip(long fkDebtor) throws BloSalesV2Exception;
    
}
