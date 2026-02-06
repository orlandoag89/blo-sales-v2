package com.blo.sales.v2.model.entities;

import java.util.List;

public class WrapperDebtorsEntity {
    
    private List<DebtorEntity> debtors;

    public List<DebtorEntity> getDebtors() {
        return debtors;
    }

    public void setDebtors(List<DebtorEntity> debtors) {
        this.debtors = debtors;
    }
    
}
