package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.math.BigDecimal;
import java.util.List;

public interface ISalesController {
    
    PojoIntSale registerSale(BigDecimal totalSale, List<PojoIntSaleProductData> productsInfo,long idUser) throws BloSalesV2Exception;
    
    PojoIntDebtor registerSaleWithNewDebtor(BigDecimal totalSale, List<PojoIntSaleProductData> productsInfo,long idUser, PojoIntDebtor debtorData) throws BloSalesV2Exception;
    
    PojoIntDebtor registerSaleWithDebtor(BigDecimal totalSale, List<PojoIntSaleProductData> productsInfo, String partialPay, long idUser, long idDebtor) throws BloSalesV2Exception;
    
}
