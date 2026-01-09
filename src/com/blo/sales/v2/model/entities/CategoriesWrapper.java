package com.blo.sales.v2.model.entities;

import java.util.List;

public class CategoriesWrapper {
    
    private List<CategoryEntity> categories;

    public List<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }
    
}
