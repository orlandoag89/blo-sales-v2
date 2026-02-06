package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.ActivesCostsEntityEnum;
import java.math.BigDecimal;

public class ActiveCostEntity {
    
    private long id_actives_costs;
    
    private String concept;
    
    private BigDecimal amount;
    
    private ActivesCostsEntityEnum type;
    
    private boolean complete;

    public long getId_actives_costs() {
        return id_actives_costs;
    }

    public void setId_actives_costs(long id_actives_costs) {
        this.id_actives_costs = id_actives_costs;
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

    public ActivesCostsEntityEnum getType() {
        return type;
    }

    public void setType(ActivesCostsEntityEnum type) {
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
        return "ActiveCostEntity{" + "id_actives_costs=" + id_actives_costs + ", concept=" + concept + ", amount=" + amount + ", type=" + type + ", complete=" + complete + '}';
    }
}
