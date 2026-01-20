package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.CashboxEntityEnum;
import java.math.BigDecimal;

public class CashboxEntity {
    
    private long id_cashbox;
    
    private long fk_user;
    
    private String timestamp;
    
    private BigDecimal amount;
    
    private CashboxEntityEnum status;
    
    private String username;

    public long getId_cashbox() {
        return id_cashbox;
    }

    public void setId_cashbox(long id_cashbox) {
        this.id_cashbox = id_cashbox;
    }

    public long getFk_user() {
        return fk_user;
    }

    public void setFk_user(long fk_user) {
        this.fk_user = fk_user;
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

    public void setAmount(BigDecimal initial_amount) {
        this.amount = initial_amount;
    }

    public CashboxEntityEnum getStatus() {
        return status;
    }

    public void setStatus(CashboxEntityEnum status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
