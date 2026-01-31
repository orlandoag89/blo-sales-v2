package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntDebtorSale;
import com.blo.sales.v2.model.IDebtorsSalesModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.mapper.DebtorSaleEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebtorsSalesModelImpl implements IDebtorsSalesModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static DebtorsSalesModelImpl instance;
    
    private static final DebtorSaleEntityMapper mapper = DebtorSaleEntityMapper.getInstance();
    
    private DebtorsSalesModelImpl() { }
    
    public static DebtorsSalesModelImpl getInstance() {
        if (instance == null) {
            instance = new DebtorsSalesModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntDebtorSale addRelationship(PojoIntDebtorSale debtor) throws BloSalesV2Exception {
        try {
            final var relationInner = mapper.toInner(debtor);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_DEBTOR_SALE, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, relationInner.getFk_debtor());
            ps.setLong(2, relationInner.getFk_sale());
            ps.setString(3, relationInner.getTimestamp());
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_ADD_EXCEPTION_CODE, BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                relationInner.setId_debtor_sale(rs.getLong(1));
            }
            DBConnection.doCommit();
            return mapper.toOuter(relationInner);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException ex) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
                throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            }
        }
    }

    @Override
    public void deleteRelationhip(long fkDebtor) throws BloSalesV2Exception {
         try {
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.DELETE_DEBTOR_SALE);
            ps.setLong(1, fkDebtor);
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            
            DBConnection.doCommit();
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException ex) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
                throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            }
        }
    }
    
}
