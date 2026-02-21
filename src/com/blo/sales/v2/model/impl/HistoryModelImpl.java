package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntMovement;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntMovementsDetail;
import com.blo.sales.v2.model.IHistoryModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.MovementDetailEntity;
import com.blo.sales.v2.model.entities.WrapperMovementsDetailEntity;
import com.blo.sales.v2.model.entities.enums.ReasonsEntityEnum;
import com.blo.sales.v2.model.entities.enums.TypesEntityEnum;
import com.blo.sales.v2.model.mapper.MovementEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperMovementsDetailEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HistoryModelImpl implements IHistoryModel {
    
    private static final GUILogger logger = GUILogger.getLogger(DebtorsSalesModelImpl.class.getName());
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static HistoryModelImpl instance;
    
    private static final MovementEntityMapper mapper = MovementEntityMapper.getInstance();
    
    private static final WrapperMovementsDetailEntityMapper movementsDetailsMapper = WrapperMovementsDetailEntityMapper.getInstance();
    
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
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_ADD_EXCEPTION_CODE, BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inMovement.setId_movement(rs.getInt(1));
            }
            DBConnection.doCommit();
            return mapper.toOuter(inMovement);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            }
        }
    }

    @Override
    public WrapperPojoIntMovementsDetail getHistoryFromProduct(long productId) throws BloSalesV2Exception {
        try {
            logger.log(String.format("Buscando movimientos de %s", productId));
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_MOVEMENTS_DETAIL);
            ps.setLong(1, productId);
            final var rs = ps.executeQuery();
            
            final var wrapperMovement = new WrapperMovementsDetailEntity();
            final var movements = new ArrayList<MovementDetailEntity>();
            MovementDetailEntity item = null;
            while(rs.next()) {
                item = new MovementDetailEntity();
                item.setId_movement(rs.getLong(BloSalesV2Columns.ID_MOVEMENT));
                item.setId_product(rs.getLong(BloSalesV2Columns.ID_PRODUCT));
                item.setProduct(rs.getString(BloSalesV2Columns.PRODUCT));
                item.setReason(ReasonsEntityEnum.valueOf(rs.getString(BloSalesV2Columns.REASON)));
                item.setTimestamp(rs.getString(BloSalesV2Columns.TIMESTAMP));
                item.setType(TypesEntityEnum.valueOf(rs.getString(BloSalesV2Columns.TYPE)));
                item.setUsername(rs.getString(BloSalesV2Columns.USER_NAME));
                item.setQuantity(rs.getBigDecimal(BloSalesV2Columns.QUANTITY));
                movements.add(item);
            }
            logger.log(String.format("Movimientos recuperados %s", movements.size()));
            wrapperMovement.setHistory(movements);
            return movementsDetailsMapper.toOuter(wrapperMovement);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }
    
}
