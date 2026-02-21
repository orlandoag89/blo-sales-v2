package com.blo.sales.v2.view.pojos;

import com.blo.sales.v2.view.pojos.enums.ReasonsEnum;
import com.blo.sales.v2.view.pojos.enums.TypesEnum;
import java.math.BigDecimal;

public class PojoMovementDetail {
    
    private long idMovement;
    
    private TypesEnum type;
    
    private ReasonsEnum reason;
    
    private long idProduct;
    
    private String product;
    
    private String timestamp;
    
    private String username;
    
    private BigDecimal quantity;

    public long getIdMovement() {
        return idMovement;
    }

    public void setIdMovement(long idMovement) {
        this.idMovement = idMovement;
    }

    public TypesEnum getType() {
        return type;
    }

    public void setType(TypesEnum type) {
        this.type = type;
    }

    public ReasonsEnum getReason() {
        return reason;
    }

    public void setReason(ReasonsEnum reason) {
        this.reason = reason;
    }

    public long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(long idProduct) {
        this.idProduct = idProduct;
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
        return "PojoIntMovementDetail{" + "idMovement=" + idMovement + ", type=" + type + ", reason=" + reason + ", idProduct=" + idProduct + ", product=" + product + ", timestamp=" + timestamp + ", username=" + username + ", quantity=" + quantity + '}';
    }
}
