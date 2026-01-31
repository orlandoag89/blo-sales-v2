package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.WrapperPojoIntActivesCosts;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.blo.sales.v2.model.IActivesCostsModel;
import com.blo.sales.v2.model.mapper.WrapperActivesCostsEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivesCostsModelImpl implements IActivesCostsModel {

    private static final Connection conn = DBConnection.getConnection();
    
    private static final GUILogger logger = GUILogger.getLogger(ActivesCostsModelImpl.class.getName());
    
    private static final WrapperActivesCostsEntityMapper mapper = WrapperActivesCostsEntityMapper.getInstance();
    
    private static ActivesCostsModelImpl instance;

    private ActivesCostsModelImpl() { }

    public static ActivesCostsModelImpl getInstance() {
        if (instance == null) {
            instance = new ActivesCostsModelImpl();
        }
        return instance;
    }
    
    @Override
    public WrapperPojoIntActivesCosts addActiveCost(WrapperPojoIntActivesCosts activesCosts) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            logger.log("guardando " + activesCosts.getActivesCosts().size());
            final var activesCostsInner = mapper.toInner(activesCosts);
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_ACTIVE_COSTS, Statement.RETURN_GENERATED_KEYS);
            for (final var activeCost: activesCostsInner.getActivesCosts()) {
                ps.setString(1, activeCost.getConcept());
                ps.setBigDecimal(2, activeCost.getAmount());
                ps.setString(3, activeCost.getType().name());
                ps.setBoolean(4, activeCost.isComplete());
                ps.addBatch();
            }
            // ejecuta la pila
            ps.executeBatch();
            DBConnection.doCommit();
            // se guardan keys
            var i = 0;
            final var rsKeys = ps.getGeneratedKeys();
            while(rsKeys.next()) {
                activesCostsInner.getActivesCosts().get(i).setId_actives_costs(rsKeys.getLong(1));
                i++;
                logger.log("registro guardado " + i);
            }
            return mapper.toOuter(activesCostsInner);
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
