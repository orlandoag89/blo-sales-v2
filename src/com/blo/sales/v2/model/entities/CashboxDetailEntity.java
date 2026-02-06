package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.ActivesCostsEntityEnum;
import com.blo.sales.v2.model.entities.enums.CashboxEntityEnum;
import java.math.BigDecimal;

public class CashboxDetailEntity {
    
    private long id_cashbox;
    
    private CashboxEntityEnum status;
    
    private BigDecimal amount;
    
    private String concept;
    
    private ActivesCostsEntityEnum type;
    
    private String timestamp;
    
    private BigDecimal concept_amount;

    public BigDecimal getConcept_amount() {
        return concept_amount;
    }

    public void setConcept_amount(BigDecimal concept_amount) {
        this.concept_amount = concept_amount;
    }

    public long getId_cashbox() {
        return id_cashbox;
    }

    public void setId_cashbox(long id_cashbox) {
        this.id_cashbox = id_cashbox;
    }

    public CashboxEntityEnum getStatus() {
        return status;
    }

    public void setStatus(CashboxEntityEnum status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public ActivesCostsEntityEnum getType() {
        return type;
    }

    public void setType(ActivesCostsEntityEnum type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CashboxDetailEntity{" + "id_cashbox=" + id_cashbox + ", status=" + status + ", amount=" + amount + ", concept=" + concept + ", type=" + type + ", timestamp=" + timestamp + '}';
    }
    
}
