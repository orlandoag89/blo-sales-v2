package com.blo.sales.v2.controller.pojos;

import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import java.math.BigDecimal;

public class PojoIntSale {

    private long idSale;

    private BigDecimal total;

    private SalesStatusIntEnum saleStatus;
    
    private String timestamp;

    public long getIdSale() {
        return idSale;
    }

    public void setIdSale(long idSale) {
        this.idSale = idSale;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public SalesStatusIntEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SalesStatusIntEnum saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
