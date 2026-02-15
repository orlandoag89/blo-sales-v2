package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.controller.ICashboxController;
import com.blo.sales.v2.controller.IUserController;
import com.blo.sales.v2.controller.impl.CashboxControllerImpl;
import com.blo.sales.v2.controller.impl.UserControllerImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.CommonAlerts;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.commons.GUILogger;
import com.blo.sales.v2.view.dialogs.CashboxDialog;
import com.blo.sales.v2.view.mappers.PojoCashboxMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoActivesCostsMapper;
import com.blo.sales.v2.view.mappers.WrapperPojoNotesMapper;
import com.blo.sales.v2.view.pojos.PojoCashbox;
import com.blo.sales.v2.view.pojos.PojoDialogCashboxData;
import com.blo.sales.v2.view.pojos.PojoLoggedInUser;
import com.blo.sales.v2.view.pojos.WrapperPojoActivesCosts;
import com.blo.sales.v2.view.pojos.WrapperPojoNotes;
import com.blo.sales.v2.view.pojos.enums.CashboxStatusEnum;
import com.blo.sales.v2.view.pojos.enums.TypeNoteEnum;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;

public class CashboxOpen extends javax.swing.JPanel {
    
    private static final GUILogger logger = GUILogger.getLogger(CashboxOpen.class.getName());
    
    private static final ICashboxController cashboxController = CashboxControllerImpl.getInstance();
    
    private static final IUserController userController = UserControllerImpl.getInstance();
    
    private static final WrapperPojoActivesCostsMapper activesCostMapper = WrapperPojoActivesCostsMapper.getInstance();
    
    private static final PojoCashboxMapper mapper = PojoCashboxMapper.getInstance();
    
    private static final WrapperPojoNotesMapper mapperNotes = WrapperPojoNotesMapper.getInstance();
    
    private PojoLoggedInUser userData;
    
    private PojoCashbox openCashbox;
    
    private WrapperPojoNotes notes;
    
    public CashboxOpen(PojoLoggedInUser userData) {
        this.userData = userData;
        initComponents();
        try {
            loadDataAndCashbox();
            this.notes = mapperNotes.toOuter(userController.getNotesByUserId(userData.getIdUser()));
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
            CommonAlerts.openError(ex.getMessage());
        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCashboxes = new javax.swing.JTable();
        btnCloseNow = new javax.swing.JButton();

        tblCashboxes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblCashboxes);

        btnCloseNow.setText("Cerrar ahora");
        btnCloseNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseNowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCloseNow)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCloseNow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private WrapperPojoNotes filterLst(TypeNoteEnum type) {
        final var out = new WrapperPojoNotes();
        final var lst = notes.getNotes().stream().filter(n -> n.getTypeNote() == type).collect(Collectors.toList());
        out.setNotes(lst);
        return out;
    }
    
    private void btnCloseNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseNowActionPerformed
        if (openCashbox == null) {
            return;
        }
        final var resp = CommonAlerts.showConfirmDialog("¿Importar desde notas rápidas?");
        WrapperPojoNotes pasives = null;
        WrapperPojoNotes actives = null;
        if (resp) {
            pasives = filterLst(TypeNoteEnum.PASIVO);
            actives = filterLst(TypeNoteEnum.ACTIVO);
        }
        
        final var cashboxDialog = new CashboxDialog<>(
            this,
            "Cerrando caja de dinero",
            openCashbox,
            actives,
            pasives,
            (PojoDialogCashboxData data) -> {
                try {
                    final var wrapper = new WrapperPojoActivesCosts();
                    wrapper.setActivesCosts(data.getItems());
                    openCashbox.setAmount(data.getTotalAmountInCashbox());
                    final var cashboxClosed = cashboxController.closeCashbox(mapper.toInner(openCashbox), activesCostMapper.toInner(wrapper));
                    if (mapper.toOuter(cashboxClosed).getStatus().compareTo(CashboxStatusEnum.CLOSE) == 0) {
                         loadDataAndCashbox();
                    }
                } catch (BloSalesV2Exception ex) {
                    logger.error(ex.getMessage());
                    CommonAlerts.openError(ex.getMessage());
                }
            }
        );
        cashboxDialog.setVisible(true);
    }//GEN-LAST:event_btnCloseNowActionPerformed
    private void loadDataAndCashbox() throws BloSalesV2Exception {
        openCashbox = mapper.toOuter(cashboxController.getOpenCashbox());
        loadDataAndTitles(openCashbox); 
    }
    
    private void loadDataAndTitles(PojoCashbox cashbox) throws BloSalesV2Exception {
        final String[] titles = {"ID", "Monto", "Gestionada por", "Fecha"};
        GUICommons.loadTitleOnTable(tblCashboxes, titles, false);
        final var model = (DefaultTableModel) tblCashboxes.getModel();
        if (cashbox != null) {
            final Object[] row = {
                cashbox.getIdCashbox(),
                cashbox.getAmount(),
                cashbox.getUserFrom(),
                cashbox.getTimestamp()
            };
            model.addRow(row);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseNow;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCashboxes;
    // End of variables declaration//GEN-END:variables
}
