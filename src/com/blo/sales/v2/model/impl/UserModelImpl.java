package com.blo.sales.v2.model.impl;

import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.PojoIntNote;
import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntNotes;
import com.blo.sales.v2.model.IUserModel;
import com.blo.sales.v2.model.config.DBConnection;
import com.blo.sales.v2.model.constants.BloSalesV2Columns;
import com.blo.sales.v2.model.constants.BloSalesV2Queries;
import com.blo.sales.v2.model.entities.NoteEntity;
import com.blo.sales.v2.model.entities.SaleAndProductEntity;
import com.blo.sales.v2.model.entities.UserEntity;
import com.blo.sales.v2.model.entities.WrapperNotesEntity;
import com.blo.sales.v2.model.entities.WrapperSalesAndStockEntity;
import com.blo.sales.v2.model.entities.enums.RolesEntityEnum;
import com.blo.sales.v2.model.entities.enums.TypeNoteEntityEnum;
import com.blo.sales.v2.model.mapper.NoteEntityMapper;
import com.blo.sales.v2.model.mapper.UserEntityMapper;
import com.blo.sales.v2.model.mapper.UserLoggedEntityMapper;
import com.blo.sales.v2.model.mapper.WrapperNotesEntityMapper;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModelImpl implements IUserModel {
    
    private static final GUILogger logger = GUILogger.getLogger(UserModelImpl.class.getName());
    
    private static final Connection conn = DBConnection.getConnection();
    
    private static final UserLoggedEntityMapper userEntityMapper = UserLoggedEntityMapper.getInstance();
    
    private static final UserEntityMapper userMapper = UserEntityMapper.getInstance();
    
    private static final WrapperNotesEntityMapper mapperNotes = WrapperNotesEntityMapper.getInstance();
    
    private static final NoteEntityMapper noteMapper = NoteEntityMapper.getInstance();
    
    private static UserModelImpl instance;
    
    private UserModelImpl() { }
    
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
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }
    
    private UserEntity selectUserEntity(String username, String password) throws SQLException, BloSalesV2Exception {
        final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_USER_ROL);
        ps.setString(1, username);
        ps.setString(2, password);
        final var rs = ps.executeQuery();
        BloSalesV2Utils.validateRule(!rs.next(), BloSalesV2Utils.USER_NOT_FOUND);
        final var userFound = new UserEntity();
        userFound.setRole(RolesEntityEnum.valueOf(rs.getString(1)));
        userFound.setUsername(rs.getString(2));
        userFound.setId_user(rs.getInt(3));
        return userFound;
    }
    
    private void userExists(String username) throws SQLException, BloSalesV2Exception {
        final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_ONLY_ID_USERS);
        ps.setString(1, username);
        final var rs = ps.executeQuery();
        BloSalesV2Utils.validateRule(!rs.next(), BloSalesV2Utils.USER_NOT_FOUND);
    }

    @Override
    public PojoIntUser getUserById(long id) throws BloSalesV2Exception {
        try {
            final var ps = conn.prepareStatement(BloSalesV2Queries.SELECT_ID_FROM_USER);
            ps.setLong(1, id);
            final var rs = ps.executeQuery();
            BloSalesV2Utils.validateRule(!rs.next(), BloSalesV2Utils.USER_NOT_FOUND);
            final var userFound = new UserEntity();
            userFound.setId_user(rs.getInt(BloSalesV2Columns.ID_USER));
            userFound.setRole(RolesEntityEnum.valueOf(rs.getString(BloSalesV2Columns.ROL)));
            userFound.setUsername(rs.getString(BloSalesV2Columns.USER_NAME));
            return userMapper.toOuter(userFound);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        }
        
    }

    @Override
    public PojoIntNote addNote(PojoIntNote note) throws BloSalesV2Exception {
         try {
            logger.log("se comienza a registrar venta");
            final var innerNote = noteMapper.toInner(note);
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.INSERT_NOTES, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, innerNote.getNote());
            ps.setString(2, innerNote.getTimestamp());
            ps.setString(3, innerNote.getType_note().name());
            ps.setLong(4, innerNote.getFk_user());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_SAVED_ON_DATA_BASE);
            }
            final var rs = ps.getGeneratedKeys();
            if (rs.next()) {
                innerNote.setFk_user(rs.getInt(1));
            }
            DBConnection.doCommit();
            logger.log("nota registrada " + innerNote.toString());
            return noteMapper.toOuter(innerNote);
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

    @Override
    public WrapperPojoIntNotes getNotesByUserId(long idUser) throws BloSalesV2Exception {
        try {
            logger.log("recuperando notas");
            final var ps = conn.prepareStatement(BloSalesV2Queries.GET_NOTES_BY_ID_USER);
            ps.setLong(1, idUser);
            final var data = ps.executeQuery();
            final var wrapper = new WrapperNotesEntity();
            NoteEntity note = null;
            final var notes = new ArrayList<NoteEntity>();
            while(data.next()) {
                note = new NoteEntity();
                note.setId_note(data.getLong(BloSalesV2Columns.ID_NOTE));
                note.setNote(data.getString(BloSalesV2Columns.NOTE));
                note.setFk_user(data.getLong(BloSalesV2Columns.FK_USER));
                note.setTimestamp(data.getString(BloSalesV2Columns.TIMESTAMP));
                note.setType_note(TypeNoteEntityEnum.valueOf(data.getString(BloSalesV2Columns.TYPE_NOTE)));
                notes.add(note);
            }
            wrapper.setNotes(notes);
            logger.log("registros encontrados " + notes.size());
            return mapperNotes.toOuter(wrapper);
        } catch (SQLException ex) {
            Logger.getLogger(ProductsModelImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(ex.getMessage());
        }
    }

    @Override
    public PojoIntNote updateNote(PojoIntNote note) throws BloSalesV2Exception {
        try {
            logger.log("actaulizando " + note.getIdNote() + " " + note.toString());
            DBConnection.disableAutocommit();
            final var noteInner = noteMapper.toInner(note);
            final var ps = conn.prepareStatement(BloSalesV2Queries.UPDATE_NOTE);
            ps.setString(1, noteInner.getNote());
            ps.setLong(2, noteInner.getFk_user());
            final var rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BloSalesV2Exception(BloSalesV2Utils.ERROR_UPDATING_ON_DATA_BASE);
            }
            DBConnection.doCommit();
            logger.log("nota actualizada " + noteInner.toString());
            return noteMapper.toOuter(noteInner);
        } catch (SQLException e) {
            throw new BloSalesV2Exception(e.getMessage());
        } finally {
            try {
                DBConnection.enableAutocommit();
            } catch (SQLException e) {
                throw new BloSalesV2Exception(e.getMessage());
            }
        }        
    }

    @Override
    public void deleteNote(long idNote) throws BloSalesV2Exception {
        try {
            DBConnection.disableAutocommit();
            final var ps = conn.prepareStatement(BloSalesV2Queries.DELETE_NOTE);
            ps.setLong(1, idNote);
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
