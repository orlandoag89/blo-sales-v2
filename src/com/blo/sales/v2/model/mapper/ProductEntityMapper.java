package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.model.entities.ProductEntity;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class ProductEntityMapper implements IToInner<ProductEntity, PojoIntProduct>, IToOuter<ProductEntity, PojoIntProduct> {

    public static ProductEntityMapper instance;
    
    private ProductEntityMapper() { }
    
    public static ProductEntityMapper getInstance() {
        if (instance == null) {
            instance = new ProductEntityMapper();
        }
        return instance;
    }
    
    @Override
    public ProductEntity toInner(PojoIntProduct outer) {
        if (outer == null) {
            return null;
        }
        final var in = new ProductEntity();
        in.setBar_code(outer.getBarCode());
        in.setCost_of_sale(outer.getCostOfSale());
        in.setFk_category(outer.getFkCategory());
        in.setKg(outer.isKg());
        in.setPrice(outer.getPrice());
        in.setProduct(outer.getProduct());
        in.setQuantity(outer.getQuantity());
        in.setTimestamp(outer.getTimestamp());
        return in;
    }

    @Override
    public PojoIntProduct toOuter(ProductEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new PojoIntProduct();
        out.setBarCode(inner.getBar_code());
        out.setCostOfSale(inner.getCost_of_sale());
        out.setFkCategory(inner.getFk_category());
        out.setIdProduct(inner.getId_product());
        out.setKg(inner.isKg());
        out.setPrice(inner.getPrice());
        out.setProduct(inner.getProduct());
        out.setQuantity(inner.getQuantity());
        out.setTimestamp(inner.getTimestamp());
        return out;
    }
    
}
