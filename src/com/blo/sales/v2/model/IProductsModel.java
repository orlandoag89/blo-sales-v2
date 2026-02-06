package com.blo.sales.v2.model;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntProducts;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IProductsModel {
    
    PojoIntProduct registerProduct(PojoIntProduct product) throws BloSalesV2Exception;
    
    WrapperPojoIntProducts getAllProducts() throws BloSalesV2Exception;
    
    PojoIntProduct updateProductInfo(PojoIntProduct product) throws BloSalesV2Exception;
    
    PojoIntProduct getProductById(long idProduct) throws BloSalesV2Exception;
    
    PojoIntProduct getProductByBarCode(String barCode) throws BloSalesV2Exception;
    
}
