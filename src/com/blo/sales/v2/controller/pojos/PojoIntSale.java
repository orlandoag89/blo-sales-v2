package com.blo.sales.v2.controller.pojos;

import com.blo.sales.v2.controller.pojos.enums.SalesStatusIntEnum;
import java.math.BigDecimal;

public class PojoIntSale {

    private long idSale;

    private long fkMovement;

    private BigDecimal total;

    private SalesStatusIntEnum saleStatus;

    public long getIdSale() {
        return idSale;
    }

    public void setIdSale(long idSale) {
        this.idSale = idSale;
    }

    public long getFkMovement() {
        return fkMovement;
    }

    public void setFkMovement(long fkMovement) {
        this.fkMovement = fkMovement;
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

}
