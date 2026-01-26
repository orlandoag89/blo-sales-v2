package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtors;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtorsDetails;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IDebtorsModel {
    
    PojoIntDebtor saveDebtor(PojoIntDebtor debtor) throws BloSalesV2Exception;
    
    PojoIntDebtor getDebtorById(long idDebtor) throws BloSalesV2Exception;
    
    PojoIntDebtor updateDebtor(PojoIntDebtor debtor, long idDebtor) throws BloSalesV2Exception;
    
    WrapperPojoIntDebtors getAllDebtors() throws BloSalesV2Exception;
    
    WrapperPojoIntDebtorsDetails getDebtorsDetails() throws BloSalesV2Exception;
    
}
