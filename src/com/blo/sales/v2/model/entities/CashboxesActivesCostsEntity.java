package com.blo.sales.v2.model.entities;

public class CashboxesActivesCostsEntity {
    
    private long id_cashboxes_actives_costs;
    
    private long fk_cashbox;
    
    private long fk_actives_costs;
    
    private String timestamp;

    public long getId_cashboxes_actives_costs() {
        return id_cashboxes_actives_costs;
    }

    public void setId_cashboxes_actives_costs(long id_cashboxes_actives_costs) {
        this.id_cashboxes_actives_costs = id_cashboxes_actives_costs;
    }

    public long getFk_cashbox() {
        return fk_cashbox;
    }

    public void setFk_cashbox(long fk_cashbox) {
        this.fk_cashbox = fk_cashbox;
    }

    public long getFk_actives_costs() {
        return fk_actives_costs;
    }

    public void setFk_actives_costs(long fk_actives_costs) {
        this.fk_actives_costs = fk_actives_costs;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CashboxesActivesCostsEntity{" + "id_cashboxes_actives_costs=" + id_cashboxes_actives_costs + ", fk_cashbox=" + fk_cashbox + ", fk_actives_costs=" + fk_actives_costs + ", timestamp=" + timestamp + '}';
    }
    
}
