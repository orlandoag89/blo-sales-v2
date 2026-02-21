package com.blo.sales.v2.controller.pojos;

import java.util.List;

public class WrapperPojoIntMovementsDetail {
    
    private List<PojoIntMovementDetail> history;

    public List<PojoIntMovementDetail> getHistory() {
        return history;
    }

    public void setHistory(List<PojoIntMovementDetail> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "WrapperPojoIntMovementDetail{" + "history=" + history + '}';
    }
    
}
