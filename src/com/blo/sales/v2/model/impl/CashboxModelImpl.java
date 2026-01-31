package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntCashbox;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxes;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntCashboxesDetails;
import com.blo.sales.v2.model.ICashboxModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.CashboxDetailEntity;
import com.blo.sales.v2.model.entities.CashboxEntity;
import com.blo.sales.v2.model.entities.WrapperCashboxesDetailsEntity;
import com.blo.sales.v2.model.entities.WrapperCashboxesEntity;
import com.blo.sales.v2.model.entities.enums.ActivesCostsEntityEnum;
import com.blo.sales.v2.model.entities.enums.CashboxEntityEnum;
import com.blo.sales.v2.model.mapper.CashboxEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperCashboxesDetailsEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperCashboxesEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CashboxModelImpl implements ICashboxModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static final GUILogger logger = GUILogger.getLogger(CashboxModelImpl.class.getName());
        
    private static final CashboxEntityMapper mapper = CashboxEntityMapper.getInstance();
    
    private static final WrapperCashboxesEntityMapper wrapperMapper = WrapperCashboxesEntityMapper.getInstance();
    
    private static final WrapperCashboxesDetailsEntityMapper cashboxesDetailsMapper =  WrapperCashboxesDetailsEntityMapper.getInstance();
    
    private static CashboxModelImpl instance;
    
    private CashboxModelImpl() { }
    
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
            logger.log("guardando cashbox " + cashbox.toString());
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_CASHBOX, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, cashboxInner.getFk_user());
            ps.setString(2, cashboxInner.getTimestamp());
            ps.setString(3, cashboxInner.getStatus().name());
            ps.setBigDecimal(4, cashboxInner.getAmount());
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_ADD_EXCEPTION_CODE, BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            
            final var rs = ps.getGeneratedKeys();
            if (rs.next()){
                cashboxInner.setId_cashbox(rs.getLong(1));
                DBConnection.doCommit();
            }
            logger.log("cashbox gardada " + cashboxInner.toString());
            return mapper.toOuter(cashboxInner);
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
    public PojoIntCashbox getOpenCashbox() throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_OPEN_CASHBOX);
            ps.setString(1, CashboxEntityEnum.OPEN.name());
            final var data = ps.executeQuery();
            CashboxEntity cashbox = null;
            logger.log("recuperando cashbox actual");
            while(data.next()) {
                cashbox = new CashboxEntity();
                cashbox.setId_cashbox(data.getLong(BloSalesV2Columns.ID_CASHBOX));
                cashbox.setFk_user(data.getLong(BloSalesV2Columns.FK_USER));
                cashbox.setAmount(data.getBigDecimal(BloSalesV2Columns.AMOUNT));
                cashbox.setStatus(CashboxEntityEnum.valueOf(data.getString(BloSalesV2Columns.STATUS)));
                cashbox.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                cashbox.setUsername(data.getString(BloSalesV2Columns.USER_NAME));
            }
            final var cashboxIsFound = cashbox!= null;
            logger.log("cashbox data " + cashboxIsFound);
            return mapper.toOuter(cashbox);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public PojoIntCashbox updateCashbox(PojoIntCashbox cashbox, long idCashbox) throws BloSalesV2Exception {
        try {
            logger.log("actaulizando " + idCashbox + " " + cashbox.toString());
            DBConnection.disableAutocommit();
            final var cashboxInner = mapper.toInner(cashbox);
            final var ps = conn.prepareStatement(BloSalesV2Queries.UPDATE_CASHBOX);
            ps.setString(1, cashboxInner.getTimestamp());
            ps.setString(2, cashboxInner.getStatus().name());
            ps.setBigDecimal(3, cashboxInner.getAmount());
            ps.setLong(4, idCashbox);
            final var rowsAffected = ps.executeUpdate();
            
            BloSalesV2Utils.validateRule(rowsAffected == 0, BloSalesV2Utils.SQL_UPDATE_EXCEPTION_CODE, BloSalesV2Utils.ERROR_UPDATING_ON_DATA_BASE);
            
            DBConnection.doCommit();
            logger.log("cashbox actualizada " + cashbox.toString());
            return mapper.toOuter(cashboxInner);
        } catch (SQLException e) {
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
            }
        }
    }
    
    @Override
    public WrapperPojoIntCashboxes getAllCashboxes() throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_ALL_CASHBOXES_AND_USERS);
            final var data = ps.executeQuery();
            final var out = new WrapperCashboxesEntity();
            final var lst = new ArrayList<CashboxEntity>();
            logger.log("recuperando todas las cashbox");
            CashboxEntity cashbox = null;
            while(data.next()) {
                cashbox = new CashboxEntity();
                cashbox.setId_cashbox(data.getLong(BloSalesV2Columns.ID_CASHBOX));
                cashbox.setAmount(data.getBigDecimal(BloSalesV2Columns.AMOUNT));
                cashbox.setFk_user(data.getLong(BloSalesV2Columns.FK_USER));
                cashbox.setStatus(CashboxEntityEnum.valueOf(data.getString(BloSalesV2Columns.STATUS)));
                cashbox.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                cashbox.setUsername(data.getString(BloSalesV2Columns.USER_NAME));
                lst.add(cashbox);
            }
            out.setCashboxes(lst);
            logger.log("cashbox encontradas " + lst.size());
            return wrapperMapper.toOuter(out);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public WrapperPojoIntCashboxesDetails getCashboxesDetail() throws BloSalesV2Exception {
         try {
             logger.log("detalles de cashboxes flow");
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_CASHBOXES_DATA);
            final var data = ps.executeQuery();
            final var out = new WrapperCashboxesDetailsEntity();
            final var lst = new ArrayList<CashboxDetailEntity>();
            CashboxDetailEntity cashbox = null;
            while(data.next()) {
                cashbox = new CashboxDetailEntity();
                cashbox.setAmount(data.getBigDecimal(BloSalesV2Columns.AMOUNT));
                cashbox.setConcept(data.getString(BloSalesV2Columns.CONCEPT));
                cashbox.setId_cashbox(data.getLong(BloSalesV2Columns.ID_CASHBOX));
                cashbox.setStatus(CashboxEntityEnum.valueOf(data.getString(BloSalesV2Columns.STATUS)));
                cashbox.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                cashbox.setType(ActivesCostsEntityEnum.valueOf(data.getString(BloSalesV2Columns.TYPE)));
                cashbox.setConcept_amount(data.getBigDecimal(BloSalesV2Columns.CONCEPT_AMOUNT));
                lst.add(cashbox);
            }
            logger.log("cashboxes " + lst.size());
            out.setCashbocesInfo(lst);
            return cashboxesDetailsMapper.toOuter(out);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(BloSalesV2Utils.SQL_EXCEPTION_CODE, BloSalesV2Utils.SQL_EXCEPTION_MESSAGE);
        }
    }
    
}
