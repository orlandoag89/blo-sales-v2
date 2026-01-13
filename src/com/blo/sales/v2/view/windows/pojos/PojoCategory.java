package com.blo.sales.v2.view.windows.pojos;

public class PojoCategory {
    
    private long idCategory;
    
    private String description;
    
    private String category;

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return idCategory + " " + category;
    }
    
}
