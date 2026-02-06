package com.blo.sales.v2.view.pojos;

import java.math.BigDecimal;

public class PojoDebtorDetail {
    
    private long idDebtor;
    
    private String name;
    
    private BigDecimal debt;
    
    private String payments;
    
    private String product;
    
    private BigDecimal quantitySale;
    
    private BigDecimal totalOnSale;
    
    private String timestamp;

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

    public BigDecimal getQuantitySale() {
        return quantitySale;
    }

    public void setQuantitySale(BigDecimal quantitySale) {
        this.quantitySale = quantitySale;
    }

    public BigDecimal getTotalOnSale() {
        return totalOnSale;
    }

    public void setTotalOnSale(BigDecimal totalOnSale) {
        this.totalOnSale = totalOnSale;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "PojoDebtorDetail{" + "idDebtor=" + idDebtor + ", name=" + name + ", debt=" + debt + ", payments=" + payments + ", product=" + product + ", quantitySale=" + quantitySale + ", totalOnSale=" + totalOnSale + ", timestamp=" + timestamp + '}';
    }
}
