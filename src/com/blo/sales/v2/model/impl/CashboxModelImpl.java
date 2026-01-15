package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.model.ICashboxModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.CashboxEntity;
import com.blo.sales.v2.model.entities.enums.CashboxEntityEnum;
import com.blo.sales.v2.model.mapper.CashboxEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashboxModelImpl implements ICashboxModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static CashboxModelImpl instance;
    
    private CashboxEntityMapper mapper;
    
    private CashboxModelImpl() {
        mapper = CashboxEntityMapper.getInstance();
    }
    
    public static CashboxModelImpl getInstance() {
        if (instance == null) {
            instance = new CashboxModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntCashbox addCashbox(PojoIntCashbox cashbox) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            final var cashboxInner = mapper.toInner(cashbox);
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_CASHBOX, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, cashboxInner.getFk_user());
            ps.setString(2, cashboxInner.getTimestamp());
            ps.setString(3, cashboxInner.getStatus().name());
            ps.setBigDecimal(4, cashboxInner.getAmount());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            }
            final var rs = ps.getGeneratedKeys();
            if (rs.next()){
                cashboxInner.setId_cashbox(rs.getLong(1));
                DBConnection.doCommit();
            }
            return mapper.toOuter(cashboxInner);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException ex) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
                throw new BloSalesV2Exception(ex.getMessage());
            }
        }
    }

    @Override
    public PojoIntCashbox getOpenCashbox() throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_OPEN_CASHBOX);
            ps.setString(1, CashboxEntityEnum.OPEN.name());
            final var data = ps.executeQuery();
            CashboxEntity cashbox = null;
            while(data.next()) {
                cashbox = new CashboxEntity();
                cashbox.setId_cashbox(data.getLong(BloSalesV2Columns.ID_CASHBOX));
                cashbox.setFk_user(data.getLong(BloSalesV2Columns.FK_USER));
                cashbox.setAmount(data.getBigDecimal(BloSalesV2Columns.AMOUNT));
                cashbox.setStatus(CashboxEntityEnum.valueOf(data.getString(BloSalesV2Columns.STATUS)));
                cashbox.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
            }
            return mapper.toOuter(cashbox);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public PojoIntCashbox updateCashbox(PojoIntCashbox cashbox, long idCashbox) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            final var cashboxInner = mapper.toInner(cashbox);
            final var ps = conn.prepareStatement(BloSalesV2Queries.UPDATE_CASHBOX);
            ps.setString(1, cashboxInner.getTimestamp());
            ps.setString(2, cashboxInner.getStatus().name());
            ps.setBigDecimal(3, cashboxInner.getAmount());
            ps.setLong(4, idCashbox);
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_UPDATING_ON_DATA_BASE);
            }
            DBConnection.doCommit();
            return mapper.toOuter(cashboxInner);
        } catch (SQLException e) {
            throw new BloSalesV2Exception(e.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                throw new BloSalesV2Exception(e.getMessage());
            }
        }
    }
    
}
