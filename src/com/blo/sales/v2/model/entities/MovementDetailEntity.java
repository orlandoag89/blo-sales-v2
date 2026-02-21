package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.ReasonsEntityEnum;
import com.blo.sales.v2.model.entities.enums.TypesEntityEnum;
import java.math.BigDecimal;

public class MovementDetailEntity {
    
    private long id_movement;
    
    private TypesEntityEnum type;
    
    private ReasonsEntityEnum reason;
    
    private long id_product;
    
    private String product;
    
    private String timestamp;
    
    private String username;
    
    private BigDecimal quantity;

    public long getId_movement() {
        return id_movement;
    }

    public void setId_movement(long id_movement) {
        this.id_movement = id_movement;
    }

    public TypesEntityEnum getType() {
        return type;
    }

    public void setType(TypesEntityEnum type) {
        this.type = type;
    }

    public ReasonsEntityEnum getReason() {
        return reason;
    }

    public void setReason(ReasonsEntityEnum reason) {
        this.reason = reason;
    }

    public long getId_product() {
        return id_product;
    }

    public void setId_product(long id_product) {
        this.id_product = id_product;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MovementDetailEntity{" + "id_movement=" + id_movement + ", type=" + type + ", reason=" + reason + ", id_product=" + id_product + ", product=" + product + ", timestamp=" + timestamp + ", username=" + username + ", quantity=" + quantity + '}';
    }
}
