package com.blo.sales.v2.plugins.sales.report;

import java.math.BigDecimal;
import java.util.List;

public class RowSaleData {
    
    private long idSale;
    
    private List<RowSaleReportData> rows;
    
    private BigDecimal profitOnSale;

    public long getIdSale() {
        return idSale;
    }

    public void setIdSale(long idSale) {
        this.idSale = idSale;
    }

    public List<RowSaleReportData> getRows() {
        return rows;
    }

    public void setRows(List<RowSaleReportData> rows) {
        this.rows = rows;
    }

    public BigDecimal getProfitOnSale() {
        return profitOnSale;
    }

    public void setProfitOnSale(BigDecimal profitOnSale) {
        this.profitOnSale = profitOnSale;
    }

    @Override
    public String toString() {
        return "RowSaleData{" + "idSale=" + idSale + ", rows=" + rows + ", profitOnSale=" + profitOnSale + '}';
    }
}
