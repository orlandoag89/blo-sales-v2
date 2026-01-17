package com.blo.sales.v2.view.pojos;

public class PojoDebtorSale {
    
    private long idDebtorSale;
    
    private long fkSale;
    
    private long fkDebtor;
    
    private String timestamp;

    public long getIdDebtorSale() {
        return idDebtorSale;
    }

    public void setIdDebtorSale(long idDebtorSale) {
        this.idDebtorSale = idDebtorSale;
    }

    public long getFkSale() {
        return fkSale;
    }

    public void setFkSale(long fkSale) {
        this.fkSale = fkSale;
    }

    public long getFkDebtor() {
        return fkDebtor;
    }

    public void setFkDebtor(long fkDebtor) {
        this.fkDebtor = fkDebtor;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
}
