package com.blo.sales.v2.model.entities;

import java.util.List;

public class WrapperDebtorsDetailsEntity {
    
    private List<DebtorDetailEntity> debtors;

    public List<DebtorDetailEntity> getDebtors() {
        return debtors;
    }

    public void setDebtors(List<DebtorDetailEntity> debtors) {
        this.debtors = debtors;
    }
    
}
