package com.blo.sales.v2.model.entities;

public class SaleDeletedDetailEntity {
    
    private long id_sale_deleted;
    
    private long fk_sale_product;
    
    private long fk_user;
    
    private String reason;
    
    private String timestamp;

    public long getId_sale_deleted() {
        return id_sale_deleted;
    }

    public void setId_sale_deleted(long id_sale_deleted) {
        this.id_sale_deleted = id_sale_deleted;
    }

    public long getFk_sale_product() {
        return fk_sale_product;
    }

    public void setFk_sale_product(long fk_sale_product) {
        this.fk_sale_product = fk_sale_product;
    }

    public long getFk_user() {
        return fk_user;
    }

    public void setFk_user(long fk_user) {
        this.fk_user = fk_user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SaleDeletedDetailEntity{" + "id_sale_deleted=" + id_sale_deleted + ", fk_sale_product=" + fk_sale_product + ", fk_user=" + fk_user + ", reason=" + reason + ", timestamp=" + timestamp + '}';
    }
    
}
