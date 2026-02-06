package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.view.pojos.PojoSaleProductData;

public class PojoSaleProductDataMapper implements IToInner<PojoIntSaleProductData, PojoSaleProductData> {
    
    private static PojoSaleProductDataMapper instance;
    
    private PojoSaleProductDataMapper () { }
    
    public static PojoSaleProductDataMapper getInstance() {
        if (instance == null) {
            instance = new PojoSaleProductDataMapper();
        }
        return instance;
    }

    @Override
    public PojoIntSaleProductData toInner(PojoSaleProductData outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntSaleProductData();
        inner.setIdProduct(outer.getIdProduct());
        inner.setPrice(outer.getPrice());
        inner.setQuantityOnSale(outer.getQuantityOnSale());
        inner.setProductBuyTotal(outer.getProductBuyTotal());
        return inner;
    }
    
}
