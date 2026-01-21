package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntActiveCost;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.mapper.ActiveCostEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.blo.sales.v2.model.IActivesCostsModel;

public class ActivesCostsModelImpl implements IActivesCostsModel {

    private static final Connection conn = DBConnection.getConnection();
    
    private static final GUILogger logger = GUILogger.getLogger(ActivesCostsModelImpl.class.getName());
    
    private static final ActiveCostEntityMapper mapper = ActiveCostEntityMapper.getInstance();
    
    private static ActivesCostsModelImpl instance;

    private ActivesCostsModelImpl() { }

    public static ActivesCostsModelImpl getInstance() {
        if (instance == null) {
            instance = new ActivesCostsModelImpl();
        }
        return instance;
    }
    
    @Override
    public PojoIntActiveCost addActiveCost(PojoIntActiveCost activeCost) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            logger.log("guardando " + activeCost.toString());
            final var activeCostInner = mapper.toInner(activeCost);
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_ACTIVE_COSTS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, activeCostInner.getConcept());
            ps.setBigDecimal(2, activeCostInner.getAmount());
            ps.setString(3, activeCostInner.getType().name());
            ps.setBoolean(4, activeCostInner.isComplete());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            }
            final var rs = ps.getGeneratedKeys();
            if (rs.next()){
                activeCostInner.setId_actives_costs(rs.getLong(1));
                DBConnection.doCommit();
            }
            logger.log("registro guardado " + activeCostInner.toString());
            return mapper.toOuter(activeCostInner);
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
