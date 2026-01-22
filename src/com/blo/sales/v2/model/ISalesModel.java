package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSalesAndStock;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ISalesModel {
    
    PojoIntSale registerSale(PojoIntSale sale) throws BloSalesV2Exception;
    
    WrapperPojoIntSalesAndStock retrieveAllSalesDetail() throws BloSalesV2Exception;
}
