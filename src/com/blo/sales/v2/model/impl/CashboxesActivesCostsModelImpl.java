package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntCashboxesActivesCosts;
import com.blo.sales.v2.model.ICashboxesActivesCostsModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.mapper.CashboxesActivesCostsEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CashboxesActivesCostsModelImpl implements ICashboxesActivesCostsModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static final GUILogger logger = GUILogger.getLogger(CashboxesActivesCostsModelImpl.class.getName());
    
    private static final CashboxesActivesCostsEntityMapper mapper = CashboxesActivesCostsEntityMapper.getInstance();
    
    private static CashboxesActivesCostsModelImpl instance;
    
    public static CashboxesActivesCostsModelImpl getInstance() {
        if (instance == null) {
            instance = new CashboxesActivesCostsModelImpl();
        }
        return instance;
    }
    
    private CashboxesActivesCostsModelImpl() { }

    @Override
    public PojoIntCashboxesActivesCosts addRelationship(PojoIntCashboxesActivesCosts data) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            logger.log("guardando " + data.toString());
            final var dataInner = mapper.toInner(data);
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_CASHBOXES_ACTIVE_COSTS, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, dataInner.getFk_cashbox());
            ps.setLong(2, dataInner.getFk_actives_costs());
            ps.setString(3, dataInner.getTimestamp());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            }
            final var rs = ps.getGeneratedKeys();
            if (rs.next()){
                dataInner.setId_cashboxes_actives_costs(rs.getLong(BloSalesV2Columns.ID_CASHBOXES_ACTIVES_COSTS));
                DBConnection.doCommit();
            }
            logger.log("registro guardado " + dataInner.toString());
            return mapper.toOuter(dataInner);
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
