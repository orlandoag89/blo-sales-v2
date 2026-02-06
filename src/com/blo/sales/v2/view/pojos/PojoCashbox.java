package com.blo.sales.v2.view.pojos;

import com.blo.sales.v2.view.pojos.enums.CashboxStatusEnum;
import java.math.BigDecimal;

public class PojoCashbox {
    
    private long idCashbox;
    
    private long fkUser;
    
    private String timestamp;
    
    private BigDecimal amount;
    
    private CashboxStatusEnum status;
    
    private String userFrom;

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

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

    public CashboxStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CashboxStatusEnum status) {
        this.status = status;
    }

}
