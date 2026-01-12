package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntProducts;
import com.blo.sales.v2.controller.pojos.enums.ReasonsIntEnum;
import com.blo.sales.v2.model.IProductsModel;
import com.blo.sales.v2.model.impl.ProductsModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;

public class ProductsControllerImpl implements IProductsController {
    
    private ICategoriesController categoriesController;
    
    private IProductsModel model;
    
    private IUserController user;
    
    public ProductsControllerImpl() {
        categoriesController = new CategoriesControllerImpl();
        model = new ProductsModelImpl();
        user = new UserControllerImpl();
    }

    @Override
    public PojoIntProduct registerProduct(PojoIntProduct product) throws BloSalesV2Exception {
        product.setTimestamp(BloSalesV2Utils.getTimestamp());
        /** valida la existencia de la categoria */
        final var categoryFound = categoriesController.getCategoryById(product.getFkCategory());
        BloSalesV2Utils.validateRule(categoryFound.getIdCategory() == 0, "CATEGORIA NO ENCONTRADA --- (com.blo.sales.v2.controller.impl)");
        return model.registerProduct(product);
    }

    @Override
    public WrapperPojoIntProducts getAllProducts() throws BloSalesV2Exception {
        return model.getAllProducts();
    }

    @Override
    public PojoIntProduct updateProductInfo(PojoIntProduct product, ReasonsIntEnum reasons, int idUser) throws BloSalesV2Exception {
        // validar usuario
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
