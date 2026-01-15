package com.blo.sales.v2.controller.pojos;

import com.blo.sales.v2.controller.pojos.enums.CashboxStatusIntEnum;
import java.math.BigDecimal;

public class PojoIntCashbox {
    
    private long idCashbox;
    
    private long fkUser;
    
    private String timestamp;
    
    private BigDecimal amount;
    
    private CashboxStatusIntEnum status;

    public long getIdCashbox() {
        return idCashbox;
    }

    public void setIdCashbox(long idCashbox) {
        this.idCashbox = idCashbox;
    }

    public long getFkUser() {
        return fkUser;
    }

    public void setFkUser(long fkUser) {
        this.fkUser = fkUser;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal initialAmount) {
        this.amount = initialAmount;
    }

    public CashboxStatusIntEnum getStatus() {
        return status;
    }

    public void setStatus(CashboxStatusIntEnum status) {
        this.status = status;
    }
}
