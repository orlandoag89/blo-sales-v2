package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.IHistoryController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.PojoIntProduct;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntProducts;
import com.blo.sales.v2.controller.pojos.enums.ReasonsIntEnum;
import com.blo.sales.v2.controller.pojos.enums.TypesIntEnum;
import com.blo.sales.v2.model.IProductsModel;
import com.blo.sales.v2.model.entities.enums.ReasonsEntityEnum;
import com.blo.sales.v2.model.entities.enums.TypesEntityEnum;
import com.blo.sales.v2.model.impl.ProductsModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;

public class ProductsControllerImpl implements IProductsController {
    
    private ICategoriesController categoriesController;
    
    private IProductsModel model;
    
    private IUserController user;
    
    private IHistoryController historyController;
    
    private static ProductsControllerImpl instance;
    
    private ProductsControllerImpl() {
        categoriesController = CategoriesControllerImpl.getInstance();
        model = ProductsModelImpl.getInstance();
        user = UserControllerImpl.getInstance();
        historyController = HistoryControllerImpl.getInstance();
    }
    
    public static ProductsControllerImpl getInstance() {
        if (instance == null) {
            instance = new ProductsControllerImpl();
        }
        return instance;
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
    public PojoIntProduct updateProductInfo(PojoIntProduct product, ReasonsIntEnum reasons, long idUser, TypesIntEnum type) throws BloSalesV2Exception {
        /** validaciones */
        final var productFound = getProductById(product.getIdProduct());
        user.getUserById(idUser);
        final var timestamp = BloSalesV2Utils.getTimestamp();
        /** crea una instancia para la bitacora */
        final var movement = new PojoIntMovement();
        movement.setFk_user(idUser);
        movement.setFk_product(productFound.getIdProduct());
        movement.setQuantity(product.getQuantity());
        movement.setReason(ReasonsEntityEnum.valueOf(reasons.name()));
        movement.setTimestamp(timestamp);
        movement.setType(TypesEntityEnum.valueOf(type.name()));
        historyController.registerMovement(movement);
        
        /** actualiza algunos campos del POJO */
        productFound.setBarCode(product.getBarCode());
        productFound.setProduct(product.getProduct());
        productFound.setPrice(product.getPrice());
        productFound.setCostOfSale(product.getCostOfSale());
        productFound.setQuantity(product.getQuantity());
        /** se actualiza timestamp a ultima actualizacion */
        productFound.setTimestamp(timestamp);
        return model.updateProductInfo(productFound);
    }

    @Override
    public PojoIntProduct getProductById(long idProduct) throws BloSalesV2Exception {
        return model.getProductById(idProduct);
    }
    
}
