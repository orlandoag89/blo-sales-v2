package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ISaleProductModel {
    
    PojoIntSaleProduct addSaleProduct(PojoIntSaleProduct sale) throws BloSalesV2Exception;
}
