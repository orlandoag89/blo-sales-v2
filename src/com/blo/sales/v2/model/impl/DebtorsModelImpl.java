package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntDebtor;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtors;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntDebtorsDetails;
import com.blo.sales.v2.model.IDebtorsModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.DebtorDetailEntity;
import com.blo.sales.v2.model.entities.DebtorEntity;
import com.blo.sales.v2.model.entities.WrapperDebtorsDetailsEntity;
import com.blo.sales.v2.model.entities.WrapperDebtorsEntity;
import com.blo.sales.v2.model.mapper.DebtorEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperDebtorsDetailsEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperDebtorsEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebtorsModelImpl implements IDebtorsModel {
    
    private static final GUILogger logger = GUILogger.getLogger(DebtorsModelImpl.class.getName());
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static DebtorsModelImpl instance;
    
    private static final DebtorEntityMapper mapper = DebtorEntityMapper.getInstance();
    
    private static final WrapperDebtorsEntityMapper wrapperMapper = WrapperDebtorsEntityMapper.getInstance();
    
    private static final WrapperDebtorsDetailsEntityMapper debtorsDetailsMapper = WrapperDebtorsDetailsEntityMapper.getInstance();
    
    private DebtorsModelImpl() { }
    
    public static DebtorsModelImpl getInstance() {
        if (instance == null) {
            instance = new DebtorsModelImpl();
        }
        return instance;
    }

    @Override
    public PojoIntDebtor saveDebtor(PojoIntDebtor debtor) throws BloSalesV2Exception {
        try {
            final var data = mapper.toInner(debtor);
            // 1. Desactivar el AutoCommit para iniciar la transacción
            DBConnection.disableAutocommit();
            // 2. Usar prepareStatement con RETURN_GENERATED_KEYS (Más estándar que prepareCall para INSERT)
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_DEBTOR, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, data.getName());
            ps.setBigDecimal(2, data.getDebt());
            ps.setString(3, data.getPayments());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                final var rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    data.setId_debtor(rs.getLong(1));
                }
            }
            // 3. Si todo salió bien, confirmamos los cambios en la DB
            DBConnection.doCommit();
            return mapper.toOuter(data);
        } catch (SQLException e) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BloSalesV2Exception(e.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
                throw new BloSalesV2Exception(e.getMessage());
            }
        }
    }

    @Override
    public PojoIntDebtor getDebtorById(long idDebtor) throws BloSalesV2Exception {
        try {
            logger.log("id buscado " + idDebtor);
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_DEBTOR_BY_ID);
            ps.setLong(1, idDebtor);
            final var rs = ps.executeQuery();
            BloSalesV2Utils.validateRule(!rs.next(), BloSalesV2Utils.ERROR_PRODUCT_NOT_FOUND);
            final var d = new DebtorEntity();
            d.setId_debtor(rs.getLong(BloSalesV2Columns.ID_DEBTOR));
            d.setName(rs.getString(BloSalesV2Columns.NAME));
            d.setPayments(rs.getString(BloSalesV2Columns.PAYMENTS));
            d.setDebt(rs.getBigDecimal(BloSalesV2Columns.DEBT));
            return mapper.toOuter(d);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public PojoIntDebtor updateDebtor(PojoIntDebtor debtor, long idDebtor) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            final var debtorMapped = mapper.toInner(debtor);
            logger.log("deudor actualizado " + debtor.toString());
            debtorMapped.setName(debtor.getName());
            debtorMapped.setPayments(debtor.getPayments());
            debtorMapped.setDebt(debtor.getDebt());
            final var ps = conn.prepareStatement(BloSalesV2Queries.UPDATE_DEBTOR);
            ps.setString(1, debtorMapped.getName());
            ps.setBigDecimal(2, debtorMapped.getDebt());
            ps.setString(3, debtorMapped.getPayments());
            ps.setLong(4, idDebtor);
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_UPDATING_ON_DATA_BASE);
            }
            DBConnection.doCommit();
            return mapper.toOuter(debtorMapped);
        } catch (SQLException e) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BloSalesV2Exception(e.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
                throw new BloSalesV2Exception(e.getMessage());
            }
        }
    }

    @Override
    public WrapperPojoIntDebtors getAllDebtors() throws BloSalesV2Exception {
         try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_DEBTORS);
            final var rs = ps.executeQuery();
            final var debtorsWrapper = new WrapperDebtorsEntity();
            final var debtors = new ArrayList<DebtorEntity>();
            DebtorEntity entity;
            while (rs.next()) {
                entity = new DebtorEntity();
                entity.setId_debtor(rs.getLong(BloSalesV2Columns.ID_DEBTOR));
                entity.setName(rs.getString(BloSalesV2Columns.NAME));
                entity.setDebt(rs.getBigDecimal(BloSalesV2Columns.DEBT));
                entity.setPayments(rs.getString(BloSalesV2Columns.PAYMENTS));
                debtors.add(entity);
            }
            debtorsWrapper.setDebtors(debtors);
            return wrapperMapper.toOuter(debtorsWrapper);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public WrapperPojoIntDebtorsDetails getDebtorsDetails() throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.DEBTORS_DETAILS);
            final var rs = ps.executeQuery();
            final var debtorsDetails = new WrapperDebtorsDetailsEntity();
            final var details = new ArrayList<DebtorDetailEntity>();
            DebtorDetailEntity detail;
            while(rs.next()) {
                detail = new DebtorDetailEntity();
                detail.setDebt(rs.getBigDecimal(BloSalesV2Columns.DEBT));
                detail.setId_debtor(rs.getLong(BloSalesV2Columns.ID_DEBTOR));
                detail.setName(rs.getString(BloSalesV2Columns.NAME));
                detail.setPayments(rs.getString(BloSalesV2Columns.PAYMENTS));
                detail.setProduct(rs.getString(BloSalesV2Columns.PRODUCT));
                detail.setQuantity_sale(rs.getBigDecimal(BloSalesV2Columns.QUANTITY_ON_SALE));
                detail.setTimestamp(rs.getString(BloSalesV2Columns.TIMESTAMP));
                detail.setTotal_on_sale(rs.getBigDecimal(BloSalesV2Columns.TOTAL_ON_SALE));
                details.add(detail);
            }
            debtorsDetails.setDebtors(details);
            return debtorsDetailsMapper.toOuter(debtorsDetails);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public void deleteDebtor(long idDebtor) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.DEBTOR_DELETE);
            ps.setLong(1, idDebtor);
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_DELETING_DATA_ON_DATA_BASE);
            }
            DBConnection.doCommit();
        } catch (SQLException e) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new BloSalesV2Exception(e.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, e);
                throw new BloSalesV2Exception(e.getMessage());
            }
        }
    }
    
}
