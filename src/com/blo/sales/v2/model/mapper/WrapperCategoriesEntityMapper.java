package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.controller.pojos.WrapperIntPojoCategories;
import com.blo.sales.v2.model.entities.WrapperCategoriesEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperCategoriesEntityMapper implements IToOuter<WrapperCategoriesEntity, WrapperIntPojoCategories> {
    
    private static final CategoryEntityMapper categoryEntityMapper = CategoryEntityMapper.getInstance();
    
    public static WrapperCategoriesEntityMapper instance;
    
    private WrapperCategoriesEntityMapper() { }

    public static WrapperCategoriesEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperCategoriesEntityMapper();
        }
        return instance;
    }
    
    
    @Override
    public WrapperIntPojoCategories toOuter(WrapperCategoriesEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new WrapperIntPojoCategories();
        final var lst = new ArrayList<PojoIntCategory>();
        if (inner.getCategories() != null && !inner.getCategories().isEmpty()) {
            inner.getCategories().forEach(c ->{
                lst.add(categoryEntityMapper.toOuter(c));
            });
        }
        out.setCategories(lst);
        return out;
    }
    
}
