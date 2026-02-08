package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntPriceHistory;
import com.blo.sales.v2.model.IPricesHistoryModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.mapper.PriceHistoryEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PricesHistoryModelImpl implements IPricesHistoryModel {
    
    private static final GUILogger logger = GUILogger.getLogger(PricesHistoryModelImpl.class.getName());
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static final PriceHistoryEntityMapper mapper = PriceHistoryEntityMapper.getInstance();
    
    private static PricesHistoryModelImpl instance;
    
    private PricesHistoryModelImpl() { }
    
    public static PricesHistoryModelImpl getInstance() {
        if (instance == null) {
            instance = new PricesHistoryModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntPriceHistory addPriceHistory(PojoIntPriceHistory priceHistory) throws BloSalesV2Exception {
        try {
            final var entity = mapper.toInner(priceHistory);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_PRICE_HISTORY_ITEM, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, entity.getPrice());
            ps.setBigDecimal(2, entity.getCost_of_sale());
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_ADD_EXCEPTION_CODE, BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                entity.setId_price_history(rs.getLong(1));
            }
            DBConnection.doCommit();
            return mapper.toOuter(entity);
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
    
}
