package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.SaleStatusEntityEnum;
import java.math.BigDecimal;

public class SaleEntity {
    
    private long id_sale;
    
    private BigDecimal total;
    
    private SaleStatusEntityEnum sale_status;
    
    private String timestamp;

    public long getId_sale() {
        return id_sale;
    }

    public void setId_sale(long id_sale) {
        this.id_sale = id_sale;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public SaleStatusEntityEnum getSale_status() {
        return sale_status;
    }

    public void setSales_status(SaleStatusEntityEnum sale_status) {
        this.sale_status = sale_status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SaleEntity{" + "id_sale=" + id_sale + ", total=" + total + ", sale_status=" + sale_status + ", timestamp=" + timestamp + '}';
    }
    
}
