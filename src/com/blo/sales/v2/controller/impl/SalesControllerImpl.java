package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IHistoryController;
import com.blo.sales.v2.controller.ISalesController;
import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.model.ISalesModel;
import com.blo.sales.v2.model.impl.SalesModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class SalesControllerImpl implements ISalesController {
    
    private static SalesControllerImpl instance;
    
    private ISalesModel saleModel;
    
    private IUserController userController;
    
    private IHistoryController historyController;
    
    private SalesControllerImpl() {
        saleModel = SalesModelImpl.getInstance();
        historyController = HistoryControllerImpl.getInstance();
        userController = UserControllerImpl.getInstance();
    }
    
    public static SalesControllerImpl getInstance() {
        if (instance == null) {
            instance = new SalesControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSale registerSale(PojoIntSale sale, int idUser) throws BloSalesV2Exception {
        /** validaciones */
        userController.getUserById(idUser);
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
