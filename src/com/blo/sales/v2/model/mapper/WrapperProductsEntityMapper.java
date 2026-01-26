package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntProducts;
import com.blo.sales.v2.model.entities.WrapperProductsEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperProductsEntityMapper implements IToOuter<WrapperProductsEntity, WrapperPojoIntProducts> {
    
    private static final ProductEntityMapper mapper = ProductEntityMapper.getInstance();
    
    private static WrapperProductsEntityMapper instance;
    
    private WrapperProductsEntityMapper() { }
    
    public static WrapperProductsEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperProductsEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntProducts toOuter(WrapperProductsEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new WrapperPojoIntProducts();
        final var products = new ArrayList<PojoIntProduct>();
        if (inner.getProducts() != null && !inner.getProducts().isEmpty()) {
            inner.getProducts().forEach(p -> products.add(mapper.toOuter(p)));
        }
        out.setProducts(products);
        return out;
    }

}
