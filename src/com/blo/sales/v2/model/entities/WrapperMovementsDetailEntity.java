package com.blo.sales.v2.model.entities;

import java.util.List;

public class WrapperMovementsDetailEntity {
    
    private List<MovementDetailEntity> history;

    public List<MovementDetailEntity> getHistory() {
        return history;
    }

    public void setHistory(List<MovementDetailEntity> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "WrapperMovementDetailEntity{" + "history=" + history + '}';
    }
    
}
