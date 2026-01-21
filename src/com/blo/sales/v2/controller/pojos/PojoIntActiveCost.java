package com.blo.sales.v2.controller.pojos;

import com.blo.sales.v2.controller.pojos.enums.ActiveCostIntEnum;
import java.math.BigDecimal;

public class PojoIntActiveCost {
    
    private long idActiveCosts;
    
    private String concept;
    
    private BigDecimal amount;
    
    private ActiveCostIntEnum type;
    
    private boolean complete;

    public long getIdActiveCosts() {
        return idActiveCosts;
    }

    public void setIdActiveCosts(long idActiveCosts) {
        this.idActiveCosts = idActiveCosts;
    }


    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ActiveCostIntEnum getType() {
        return type;
    }

    public void setType(ActiveCostIntEnum type) {
        this.type = type;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "PojoIntActiveCost{" + "idActiveCosts=" + idActiveCosts + ", concept=" + concept + ", amount=" + amount + ", type=" + type + ", complete=" + complete + '}';
    }
    
}
