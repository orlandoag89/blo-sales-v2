package com.blo.sales.v2.view.windows.pojos;

import com.blo.sales.v2.view.windows.pojos.enums.SalesStatusEnum;
import java.math.BigDecimal;

public class PojoSale {
    
    private long idSale;

    private BigDecimal total;

    private SalesStatusEnum saleStatus;

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

    public SalesStatusEnum getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SalesStatusEnum saleStatus) {
        this.saleStatus = saleStatus;
    }
}
