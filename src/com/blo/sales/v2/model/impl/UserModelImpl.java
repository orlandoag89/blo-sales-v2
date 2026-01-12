package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.model.IUserModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.Queries;
import com.blo.sales.v2.model.constants.QueriesErrors;
import com.blo.sales.v2.model.entities.UserEntity;
import com.blo.sales.v2.model.entities.enums.RolesEntityEnum;
import com.blo.sales.v2.model.mapper.UserEntityMapper;
import com.blo.sales.v2.model.mapper.UserLoggedEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModelImpl implements IUserModel {
    
    private static final Connection conn = DBConnection.getConnection();
    
    private UserLoggedEntityMapper userEntityMapper;
    
    private UserEntityMapper userMapper;
    
    private static UserModelImpl instance;
    
    private UserModelImpl() {
        userEntityMapper = UserLoggedEntityMapper.getInstance();
        userMapper = UserEntityMapper.getInstance();
    }
    
    public static UserModelImpl getInstance() {
        if (instance == null) {
            instance = new UserModelImpl();
        }
        return instance;
    }
    
    @Override
    public PojoIntLoggedInUser doLogin(PojoIntUser userData) throws BloSalesV2Exception {
        try {
            userExists(userData.getUserName());
            final var userFound = selectUserEntity(userData.getUserName(), userData.getPassword());
            return userEntityMapper.toOuter(userFound);
        } catch (SQLException ex) {
            Logger.getLogger(UserModelImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BloSalesV2Exception ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
        return null;
    }
    
    private UserEntity selectUserEntity(String username, String password) throws SQLException, BloSalesV2Exception {
        final var ps = conn.prepareStatement(Queries.SELECT_USER_ROL);
        ps.setString(1, username);
        ps.setString(2, password);
        final var rs = ps.executeQuery();
        BloSalesV2Utils.validateRule(!rs.next(), QueriesErrors.USER_ERROR);
        final var userFound = new UserEntity();
        userFound.setRole(RolesEntityEnum.valueOf(rs.getString(1)));
        userFound.setUsername(rs.getString(2));
        userFound.setId_user(rs.getInt(3));
        return userFound;
    }
    
    private void userExists(String username) throws SQLException, BloSalesV2Exception {
        final var ps = conn.prepareStatement(Queries.SELECT_ONLY_ID_USERS);
        ps.setString(1, username);
        final var rs = ps.executeQuery();
        BloSalesV2Utils.validateRule(!rs.next(), QueriesErrors.USER_ERROR);
    }

    @Override
    public PojoIntUser getUserById(int id) throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(Queries.SELECT_ID_FROM_USER);
            ps.setInt(1, id);
            final var rs = ps.executeQuery();
            BloSalesV2Utils.validateRule(!rs.next(), QueriesErrors.USER_ERROR);
            final var userFound = new UserEntity();
            userFound.setId_user(rs.getInt("id_user"));
            userFound.setRole(RolesEntityEnum.valueOf(rs.getString("rol")));
            userFound.setUsername(rs.getString("username"));
            return userMapper.toOuter(userFound);
        } catch (SQLException ex) {
            throw new BloSalesV2Exception(ex.getMessage());
        }
        
    }
}
