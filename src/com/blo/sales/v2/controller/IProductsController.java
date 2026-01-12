package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntProducts;
import com.blo.sales.v2.controller.pojos.enums.ReasonsIntEnum;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IProductsController {
    
    PojoIntProduct registerProduct(PojoIntProduct product) throws BloSalesV2Exception;
    
    PojoIntProduct updateProductInfo(PojoIntProduct product, ReasonsIntEnum reasons, int idUser) throws BloSalesV2Exception;
    
    WrapperPojoIntProducts getAllProducts() throws BloSalesV2Exception;
    
}
