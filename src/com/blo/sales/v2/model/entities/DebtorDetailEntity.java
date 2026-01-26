package com.blo.sales.v2.model.entities;

import java.math.BigDecimal;

public class DebtorDetailEntity {
    
    private long id_debtor;
    
    private String name;
    
    private BigDecimal debt;
    
    private String payments;
    
    private String product;
    
    private BigDecimal quantity_sale;
    
    private BigDecimal total_on_sale;
    
    private String timestamp;

    public long getId_debtor() {
        return id_debtor;
    }

    public void setId_debtor(long id_debtor) {
        this.id_debtor = id_debtor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public BigDecimal getQuantity_sale() {
        return quantity_sale;
    }

    public void setQuantity_sale(BigDecimal quantity_sale) {
        this.quantity_sale = quantity_sale;
    }

    public BigDecimal getTotal_on_sale() {
        return total_on_sale;
    }

    public void setTotal_on_sale(BigDecimal total_on_sale) {
        this.total_on_sale = total_on_sale;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
