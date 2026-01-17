package com.blo.sales.v2.model.entities;

import java.math.BigDecimal;

public class DebtorEntity {
    
    private long id_debtor;
    
    private String name;
    
    private BigDecimal total;
    
    private String payments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId_debtor() {
        return id_debtor;
    }

    public void setId_debtor(long id_debtor) {
        this.id_debtor = id_debtor;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }
}
