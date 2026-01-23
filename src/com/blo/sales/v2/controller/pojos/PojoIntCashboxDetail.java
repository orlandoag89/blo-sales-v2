package com.blo.sales.v2.controller.pojos;

import com.blo.sales.v2.controller.pojos.enums.ActiveCostIntEnum;
import com.blo.sales.v2.controller.pojos.enums.CashboxStatusIntEnum;
import java.math.BigDecimal;

public class PojoIntCashboxDetail {
    
    private long idCashbox;
    
    private CashboxStatusIntEnum status;
    
    private BigDecimal amount;
    
    private String concept;
    
    private ActiveCostIntEnum type;
    
    private String timestamp;

    public long getIdCashbox() {
        return idCashbox;
    }

    public void setIdCashbox(long idCashbox) {
        this.idCashbox = idCashbox;
    }

    public CashboxStatusIntEnum getStatus() {
        return status;
    }

    public void setStatus(CashboxStatusIntEnum status) {
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

    public ActiveCostIntEnum getType() {
        return type;
    }

    public void setType(ActiveCostIntEnum type) {
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
        return "PojoIntCashboxDetail{" + "idCashbox=" + idCashbox + ", status=" + status + ", amount=" + amount + ", concept=" + concept + ", type=" + type + ", timestamp=" + timestamp + '}';
    }
    
}
