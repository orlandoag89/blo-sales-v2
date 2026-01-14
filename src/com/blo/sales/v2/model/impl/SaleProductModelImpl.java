package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.model.ISaleProductModel;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class SaleProductModelImpl implements ISaleProductModel {
    
    private static SaleProductModelImpl instance;
    
    private SaleProductModelImpl() { }
    
    public static SaleProductModelImpl getInstance() {
        if (instance == null) {
            instance = new SaleProductModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSaleProduct addSaleProduct(PojoIntSaleProduct sale) throws BloSalesV2Exception {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
