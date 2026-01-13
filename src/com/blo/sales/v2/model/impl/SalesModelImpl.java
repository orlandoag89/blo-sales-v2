package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntSale;
import com.blo.sales.v2.model.ISalesModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.Queries;
import com.blo.sales.v2.model.mapper.SaleEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            final var ps = conn.prepareStatement(Queries.INSERT_SALE, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, innerSale.getFk_movement());
            ps.setBigDecimal(2, innerSale.getTotal());
            ps.setString(3, sale.getSaleStatus().name());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception("ERROR EN BASE DE DATOS");
            }
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                innerSale.setId_sale(rs.getLong(1));
            }
            DBConnection.doCommit();
            return saleMapper.toOuter(innerSale);
        } catch (SQLException ex) {
            Logger.getLogger(SalesModelImpl.class.getName()).log(Level.SEVERE, null, ex);
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
