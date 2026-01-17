package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.model.IDebtorsSalesModel;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public class DebtorsSalesModelImpl implements IDebtorsSalesModel {
    
    private static DebtorsSalesModelImpl instance;
    
    private DebtorsSalesModelImpl() {}
    
    public static DebtorsSalesModelImpl getInstance() {
        if (instance == null) {
            instance = new DebtorsSalesModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntDebtorSale addRelationship(PojoIntDebtorSale debtor) throws BloSalesV2Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
