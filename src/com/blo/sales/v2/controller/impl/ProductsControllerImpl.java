package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.ICategoriesController;
import com.blo.sales.v2.controller.IHistoryController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.IStockPricesHistoryController;
import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.PojoIntPriceHistory;
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
import com.blo.sales.v2.view.commons.GUILogger;

public class ProductsControllerImpl implements IProductsController {
    
    private static final GUILogger logger = GUILogger.getLogger(ProductsControllerImpl.class.getName());
    
    private static final ICategoriesController categoriesController = CategoriesControllerImpl.getInstance();
    
    private static final IProductsModel model = ProductsModelImpl.getInstance();
    
    private static final IUserController user = UserControllerImpl.getInstance();
    
    private static final IHistoryController historyController = HistoryControllerImpl.getInstance();
    
    private static final IStockPricesHistoryController historyPrices = StockPricesHistoryControllerImpl.getInstance();
    
    private static ProductsControllerImpl instance;
    
    private ProductsControllerImpl() {
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
        BloSalesV2Utils.validateRule(
                product.getFkCategory() == BloSalesV2Utils.DEBTORS_PAYMENTS,
                BloSalesV2Utils.CODE_CATEGORY_PROTECTED,
                BloSalesV2Utils.CATEGORY_PROTECTED
        );
        final var productBarCode = model.getProductByBarCode(product.getBarCode());
        BloSalesV2Utils.validateRule(
                productBarCode != null,
                BloSalesV2Utils.CODE_BAR_CODE_REGISTERED,
                BloSalesV2Utils.BAR_CODE_EXCEPTION
        );
        /** valida la existencia de la categoria */
        final var categoryFound = categoriesController.getCategoryById(product.getFkCategory());
        BloSalesV2Utils.validateRule(
                categoryFound.getIdCategory() == 0,
                BloSalesV2Utils.CODE_CATEGORY_NOT_FOUND,
                BloSalesV2Utils.CATEGORY_NOT_FOUND
        );
        final var productSaved = model.registerProduct(product);
        logger.log("producto guardado: " + productSaved.toString());
        final var itemPrice = new PojoIntPriceHistory();
        itemPrice.setCostOfSale(product.getCostOfSale());
        itemPrice.setPrice(product.getPrice());
        historyPrices.addPriceOnHistory(itemPrice, productSaved.getIdProduct());
        return productSaved;
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
