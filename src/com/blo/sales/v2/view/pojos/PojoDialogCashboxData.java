package com.blo.sales.v2.view.pojos;

import java.math.BigDecimal;
import java.util.List;

public class PojoDialogCashboxData {
    
    private BigDecimal totalActives;
    
    private BigDecimal totalPasives;
    
    private BigDecimal totalAmountInCashbox;
    
    private List<PojoActiveCost> items;

    public BigDecimal getTotalActives() {
        return totalActives;
    }

    public void setTotalActives(BigDecimal totalActives) {
        this.totalActives = totalActives;
    }

    public BigDecimal getTotalPasives() {
        return totalPasives;
    }

    public void setTotalPasives(BigDecimal totalPasives) {
        this.totalPasives = totalPasives;
    }

    public BigDecimal getTotalAmountInCashbox() {
        return totalAmountInCashbox;
    }

    public void setTotalAmountInCashbox(BigDecimal totalAmountInCashbox) {
        this.totalAmountInCashbox = totalAmountInCashbox;
    }

    public List<PojoActiveCost> getItems() {
        return items;
    }

    public void setItems(List<PojoActiveCost> items) {
        this.items = items;
    }
    
}
