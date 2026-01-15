package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.model.ISalesModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.mapper.SaleEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesModelImpl implements ISalesModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static SalesModelImpl instance;
    
    private SaleEntityMapper saleMapper;
    
    private SalesModelImpl() {
        saleMapper = SaleEntityMapper.getInstance();
    }
    
    public static SalesModelImpl getInstance() {
        if (instance == null) {
            instance = new SalesModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntSale registerSale(PojoIntSale sale) throws BloSalesV2Exception {
        try {
            final var innerSale = saleMapper.toInner(sale);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_SALE, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, innerSale.getTotal());
            ps.setString(2, innerSale.getSale_status().name());
            ps.setString(3, innerSale.getTimestamp());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            }
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                innerSale.setId_sale(rs.getLong(1));
            }
            DBConnection.doCommit();
            return saleMapper.toOuter(innerSale);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException ex) {
                throw new BloSalesV2Exception(ex.getMessage());
            }
        }
        
    }
    
}
