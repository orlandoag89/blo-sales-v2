package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IHistoryController;
import com.blo.sales.v2.controller.IProductsController;
import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProduct;
import com.blo.sales.v2.controller.pojos.PojoIntSaleProductData;
import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import com.blo.sales.v2.model.ISalesModel;
import com.blo.sales.v2.model.entities.enums.ReasonsEntityEnum;
import com.blo.sales.v2.model.entities.enums.TypesEntityEnum;
import com.blo.sales.v2.model.impl.SalesModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SalesControllerImpl implements ISalesController {
    
    private static SalesControllerImpl instance;
    
    private ISalesModel saleModel;
    
    private IUserController userController;
    
    private IHistoryController historyController;
    
    private IProductsController productsController;
    
    private SalesControllerImpl() {
        saleModel = SalesModelImpl.getInstance();
        historyController = HistoryControllerImpl.getInstance();
        userController = UserControllerImpl.getInstance();
        productsController = ProductsControllerImpl.getInstance();
    }
    
    public static SalesControllerImpl getInstance() {
        if (instance == null) {
            instance = new SalesControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSale registerSale(BigDecimal totalSale, List<PojoIntSaleProductData> products, long idUser) throws BloSalesV2Exception {
        /** validaciones */
        userController.getUserById(idUser);
        /** valida productos */
        products.forEach(p -> {
            try {
                productsController.getProductById(p.getIdProduct());
            } catch (BloSalesV2Exception ex) {
                Logger.getLogger(SalesControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        final var timestamp = BloSalesV2Utils.getTimestamp();
        // registro de venta
        final var sale = new PojoIntSale();
        sale.setSaleStatus(SalesStatusIntEnum.CLOSE);
        sale.setTotal(totalSale);
        sale.setTimestamp(timestamp);
        final var saleSaved = saleModel.registerSale(sale);
        PojoIntSaleProduct saleProduct;
        products.forEach(p -> {
            try {
                // registro de movimiento
                final var movement = new PojoIntMovement();
                movement.setFk_product(p.getIdProduct());
                movement.setFk_user(idUser);
                movement.setQuantity(p.getQuantityOnSale());
                movement.setReason(ReasonsEntityEnum.SALE);
                movement.setTimestamp(timestamp);
                movement.setType(TypesEntityEnum.OUTPUT);
                final var movementSaved = historyController.registerMovement(movement);
                // registro de movimiento en venta
                
            } catch (BloSalesV2Exception ex) {
                Logger.getLogger(SalesControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /** se registra la venta */
        /** por cada producto en venta se hace registro en la tabla de sale_product */
        /** se actualizacion en el stock */
        /** se agrega el dinero a la caja */
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
