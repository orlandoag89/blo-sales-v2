package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IDebtorsSalesController;
import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.model.IDebtorsSalesModel;
import com.blo.sales.v2.model.impl.DebtorsSalesModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class DebtorsSalesControllerImpl implements IDebtorsSalesController {
    
    private static DebtorsSalesControllerImpl instance;
    
    private IDebtorsSalesModel model;
    
    private DebtorsSalesControllerImpl() {
        model = DebtorsSalesModelImpl.getInstance();
    }
    
    public static DebtorsSalesControllerImpl getInstance() {
        if (instance == null) {
            instance = new DebtorsSalesControllerImpl();
        }
        return instance;
    }

    @Override
    public PojoIntDebtorSale addRelationship(PojoIntDebtorSale debtor) throws BloSalesV2Exception {
        return model.addRelationship(debtor);
    }
    
}
