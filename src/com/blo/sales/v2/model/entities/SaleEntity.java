package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.SaleStatusEntityEnum;
import java.math.BigDecimal;

public class SaleEntity {
    
    private long id_sale;
    
    private long fk_movement;
    
    private BigDecimal total;
    
    private SaleStatusEntityEnum sale_status;

    public long getId_sale() {
        return id_sale;
    }

    public void setId_sale(long id_sale) {
        this.id_sale = id_sale;
    }

    public long getFk_movement() {
        return fk_movement;
    }

    public void setFk_movement(long fk_movement) {
        this.fk_movement = fk_movement;
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
    
}
