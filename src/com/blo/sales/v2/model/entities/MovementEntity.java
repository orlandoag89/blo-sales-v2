package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.ReasonsEntityEnum;
import com.blo.sales.v2.model.entities.enums.TypesEntityEnum;
import java.math.BigDecimal;

public class MovementEntity {
    
    private long id_movement;
    
    private int fk_product;
    
    private int fk_user;
    
    private TypesEntityEnum type;
    
    private BigDecimal quantity;
    
    private ReasonsEntityEnum reason;
    
    private String timestamp;

    public long getId_movement() {
        return id_movement;
    }

    public void setId_movement(long id_movement) {
        this.id_movement = id_movement;
    }

    public int getFk_product() {
        return fk_product;
    }

    public void setFk_product(int fk_product) {
        this.fk_product = fk_product;
    }

    public int getFk_user() {
        return fk_user;
    }

    public void setFk_user(int fk_user) {
        this.fk_user = fk_user;
    }

    public TypesEntityEnum getType() {
        return type;
    }

    public void setType(TypesEntityEnum type) {
        this.type = type;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public ReasonsEntityEnum getReason() {
        return reason;
    }

    public void setReason(ReasonsEntityEnum reason) {
        this.reason = reason;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
