package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ISalesProductController {
    
    PojoIntSaleProduct addSalesProduct(PojoIntSaleProduct salesProduct) throws BloSalesV2Exception;
    
    PojoIntSaleProduct getRelationship(long idSale, long idProduct) throws BloSalesV2Exception;
    
    PojoIntSaleProduct updateRelationship(PojoIntSaleProduct data) throws BloSalesV2Exception;
}
