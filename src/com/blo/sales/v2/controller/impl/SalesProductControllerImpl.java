package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ISalesProductController;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.model.ISaleProductModel;
import com.blo.sales.v2.model.impl.SaleProductModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class SalesProductControllerImpl implements ISalesProductController {
    
    private static SalesProductControllerImpl instance;
    
    private ISaleProductModel model;
    
    private SalesProductControllerImpl() {
        model = SaleProductModelImpl.getInstance();
    }

    public static SalesProductControllerImpl getInstance() {
        if (instance == null) {
            instance = new SalesProductControllerImpl();
        }
        return instance;
    }
    
    @Override
    public PojoIntSaleProduct addSalesProduct(PojoIntSaleProduct salesProduct) throws BloSalesV2Exception {
        return model.addSaleProduct(salesProduct);
    }

    
}
