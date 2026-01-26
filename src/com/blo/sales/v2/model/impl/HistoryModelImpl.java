package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.model.IHistoryModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.mapper.MovementEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoryModelImpl implements IHistoryModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static HistoryModelImpl instance;
    
    private static final MovementEntityMapper mapper = MovementEntityMapper.getInstance();
    
    private HistoryModelImpl() { }
    
    public static HistoryModelImpl getInstance() {
        if (instance == null) {
            instance = new HistoryModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntMovement registerMovement(PojoIntMovement movement) throws BloSalesV2Exception {
        try {
            final var inMovement = mapper.toInner(movement);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_MOVEMENT, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, inMovement.getFk_product());
            ps.setLong(2, inMovement.getFk_user());
            ps.setString(3, inMovement.getType().name());
            ps.setBigDecimal(4, inMovement.getQuantity());
            ps.setString(5, inMovement.getReason().name());
            ps.setString(6, inMovement.getTimestamp());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                final var rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    inMovement.setId_movement(rs.getInt(1));
                }
            }
            DBConnection.doCommit();
            return mapper.toOuter(inMovement);
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
    
}
