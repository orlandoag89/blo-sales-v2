package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntStockPricesHistory;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntStockPriceHistory;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IStockPricesHistoryModel {
    
    PojoIntStockPricesHistory addPriceOnHistory(PojoIntStockPricesHistory item) throws BloSalesV2Exception;
    
    WrapperPojoIntStockPriceHistory getPriceFromProduct(long idProduct) throws BloSalesV2Exception;
    
}
