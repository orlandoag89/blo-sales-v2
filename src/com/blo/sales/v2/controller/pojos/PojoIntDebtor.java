package com.blo.sales.v2.controller.pojos;

import java.math.BigDecimal;

public class PojoIntDebtor {
    
    private long idDebtor;
    
    private String name;
    
    private BigDecimal total;
    
    private String payments;

    public long getIdDebtor() {
        return idDebtor;
    }

    public void setIdDebtor(long idDebtor) {
        this.idDebtor = idDebtor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
