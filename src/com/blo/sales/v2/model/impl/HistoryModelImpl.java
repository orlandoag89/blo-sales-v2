package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.model.IHistoryModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.Queries;
import com.blo.sales.v2.model.mapper.MovementEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoryModelImpl implements IHistoryModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static HistoryModelImpl instance;
    
    private MovementEntityMapper mapper;
    
    private HistoryModelImpl() {
        mapper = MovementEntityMapper.getInstance();
    }
    
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
            final var ps = conn.prepareStatement(Queries.INSERT_MOVEMENT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inMovement.getFk_product());
            ps.setInt(2, inMovement.getFk_user());
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
