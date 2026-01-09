package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.pojos.PojoIntCategory;
import com.blo.sales.v2.model.ICategoriesModel;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.model.impl.CategoriesModelImpl;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriesControllerImpl implements ICategoriesController {
    
    private ICategoriesModel categoriesModel;
    
    public CategoriesControllerImpl() {
        categoriesModel = new CategoriesModelImpl();
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
    
}
