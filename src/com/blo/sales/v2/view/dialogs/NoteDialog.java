package com.blo.sales.v2.view.dialogs;

import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.pojos.PojoNote;
import com.blo.sales.v2.view.pojos.enums.TypeNoteEnum;
import java.awt.Component;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;

public class NoteDialog<T> extends javax.swing.JDialog {
    
    private static final String[] types_note = {"ACTIVO", "PASIVO", "OTRO"};
    
    private Consumer<T> callback;
    
    private PojoNote pojoNote;

    public NoteDialog(Component parent, String title, PojoNote pojoNote, Consumer<T> callback) {
        super(SwingUtilities.getWindowAncestor(parent), title, ModalityType.APPLICATION_MODAL);
        this.callback = callback;
        this.pojoNote = pojoNote;
        initComponents();
        loadData();
    }
    
     private void loadData() {
        GUICommons.setTextToField(areaNote, pojoNote.getNote());
        final var typesModel = new DefaultComboBoxModel<String>();
        int typeNote = -1;
        for (var i = 0; i < types_note.length; i++) {
            final var type = types_note[i];
            if (pojoNote.getTypeNote().name().equals(type)) {
                typeNote = i;
            }
            typesModel.addElement(type);
        }
        cmbxTypeNote.setModel(typesModel);
        cmbxTypeNote.setSelectedIndex(typeNote);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        areaNote = new javax.swing.JTextArea();
        btnSaveChanges = new javax.swing.JButton();
        cmbxTypeNote = new javax.swing.JComboBox<>();
        btnDeleteNote = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        areaNote.setColumns(20);
        areaNote.setRows(5);
        areaNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                areaNoteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(areaNote);

        btnSaveChanges.setText("Guardar cambios");
        btnSaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveChangesActionPerformed(evt);
            }
        });

        btnDeleteNote.setText("Eliminar nota");
        btnDeleteNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteNoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnDeleteNote)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbxTypeNote, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveChanges)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveChanges)
                    .addComponent(cmbxTypeNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteNote))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveChangesActionPerformed
        try {
            final var pojoCopy = pojoNote;
            final var txt = GUICommons.getTextFromField(areaNote, true);
            pojoCopy.setNote(txt);
            final var itemSelected = GUICommons.getValueFromComboBox(cmbxTypeNote).trim();
            BloSalesV2Utils.validateRule(
                    itemSelected.isBlank(),
                    BloSalesV2Utils.CODE_NOTE_TYPE_NOT_SELECTED,
                    BloSalesV2Utils.NOTE_TYPE_NO_SELECTED
            );
            final var noteType = TypeNoteEnum.valueOf(itemSelected);
            pojoCopy.setTypeNote(noteType);
            pojoCopy.setTimesamp(BloSalesV2Utils.getTimestamp());
            callback.accept((T) pojoCopy);
            dispose();
        } catch (BloSalesV2Exception ex) {
            Logger.getLogger(NoteDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveChangesActionPerformed

    private void areaNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_areaNoteKeyReleased
        try {
            final var txt = GUICommons.getTextFromField(areaNote, false);
            if (GUICommons.isEmptyFieldByKeyEvt(evt, txt.isBlank())) {
                GUICommons.disabledButton(btnSaveChanges);
                return;
            }
            GUICommons.enabledButton(btnSaveChanges);
        } catch(BloSalesV2Exception e){}
    }//GEN-LAST:event_areaNoteKeyReleased

    private void btnDeleteNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNoteActionPerformed
        callback.accept(null);
        dispose();
    }//GEN-LAST:event_btnDeleteNoteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaNote;
    private javax.swing.JButton btnDeleteNote;
    private javax.swing.JButton btnSaveChanges;
    private javax.swing.JComboBox<String> cmbxTypeNote;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
