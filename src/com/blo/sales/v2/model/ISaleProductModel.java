package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntSaleStock;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface ISaleProductModel {
    
    PojoIntSaleProduct addSaleProduct(PojoIntSaleProduct sale) throws BloSalesV2Exception;
    
    PojoIntSaleProduct getRelationship(long fkSale, long fkProduct) throws BloSalesV2Exception;
    
    PojoIntSaleProduct updateRelationship(PojoIntSaleProduct data) throws BloSalesV2Exception;
    
    WrapperPojoIntSaleStock getSalesStockLiveByIdSale(long idSale) throws BloSalesV2Exception;
    
}
