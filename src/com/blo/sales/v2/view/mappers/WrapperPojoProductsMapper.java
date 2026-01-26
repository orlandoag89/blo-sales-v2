package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.WrapperPojoIntProducts;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoProduct;
import com.blo.sales.v2.view.pojos.WrapperPojoProducts;
import java.util.ArrayList;

public class WrapperPojoProductsMapper implements IToOuter<WrapperPojoIntProducts, WrapperPojoProducts> {
    
    private static final ProductMapper mapper = ProductMapper.getInstance();
    
    private static WrapperPojoProductsMapper instance;
    
    private WrapperPojoProductsMapper() {  }
    
    public static WrapperPojoProductsMapper getInstance() {
        if (instance == null) {
            instance = new WrapperPojoProductsMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoProducts toOuter(WrapperPojoIntProducts inner) {
        if (inner == null) {
            return null;
        }
        final var out = new WrapperPojoProducts();
        final var products = new ArrayList<PojoProduct>();
        if (inner.getProducts() != null && !inner.getProducts().isEmpty()) {
            inner.getProducts().forEach(p -> products.add(mapper.toOuter(p)));
        }
        out.setProducts(products);
        return out;
    }
    
}
