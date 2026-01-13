package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.model.ICategoriesModel;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.pojos.WrapperIntPojoCategories;
import com.blo.sales.v2.model.impl.CategoriesModelImpl;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriesControllerImpl implements ICategoriesController {
    
    private ICategoriesModel categoriesModel;
    
    private static CategoriesControllerImpl instance;
    
    private CategoriesControllerImpl() {
        categoriesModel = CategoriesModelImpl.getInstance();
    }
    
    public static CategoriesControllerImpl getInstance() {
        if (instance == null) {
            instance = new CategoriesControllerImpl();
        }
        return instance;
    }
    
    @Override
    public PojoIntCategory registerCategory(PojoIntCategory category) throws BloSalesV2Exception {
        try {
            return categoriesModel.registerCategory(category);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriesControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public WrapperIntPojoCategories getAllCategories() throws BloSalesV2Exception {
        return categoriesModel.getAllCategories();
    }

    @Override
    public PojoIntCategory updateCategory(long id, PojoIntCategory newData) throws BloSalesV2Exception {
        return categoriesModel.updateCategory(id, newData);
    }

    @Override
    public PojoIntCategory getCategoryById(long id) throws BloSalesV2Exception {
        return categoriesModel.getCategoryById(id);
    }
    
}
