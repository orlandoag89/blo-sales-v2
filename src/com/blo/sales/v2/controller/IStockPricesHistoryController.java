package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntPriceHistory;
import com.blo.sales.v2.controller.pojos.PojoIntStockPricesHistory;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IStockPricesHistoryController {
    
    PojoIntStockPricesHistory addPriceOnHistory(PojoIntPriceHistory priceItem, long idProduct) throws BloSalesV2Exception;
    
}
