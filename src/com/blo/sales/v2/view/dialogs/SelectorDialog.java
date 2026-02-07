package com.blo.sales.v2.view.dialogs;

import com.blo.sales.v2.view.commons.GUICommons;
import java.awt.Component;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

public class SelectorDialog<T> extends javax.swing.JDialog {
    
    private List<T> items;
    
    private DefaultListModel<String> modeloLista;

    public SelectorDialog(Component parent, String title, List<T> items, Consumer<T> callback) {
        super(SwingUtilities.getWindowAncestor(parent), title, ModalityType.APPLICATION_MODAL);
        this.items = items;
        initComponents();
        modeloLista = new DefaultListModel<>();
        loadData();
        lstMain.setModel(modeloLista);
        GUICommons.addDoubleClickOnListEvt(lstMain, item -> {
            callback.accept((T) item);
            this.dispose();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstMain = new javax.swing.JList<>();
        txtSearchTerm = new javax.swing.JTextField();
        lblFilter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(lstMain);

        txtSearchTerm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTermKeyReleased(evt);
            }
        });

        lblFilter.setText("Filtrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchTerm)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFilter)
                    .addComponent(txtSearchTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchTermKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTermKeyReleased
        if (evt.getKeyCode() == GUICommons.ESCAPE_KEY) {
            this.dispose();
        }
        final var txtSearch = txtSearchTerm.getText().trim();
        // 1. Limpiamos el modelo actual
        modeloLista.clear();
        // 2. Filtramos los datos originales y agregamos al modelo los que coincidan
        for (final T item : items) {
            final var casted = (String) item;
            if (casted.toLowerCase().contains(txtSearch.toLowerCase())) {
                modeloLista.addElement(casted);
            }
        }
    }//GEN-LAST:event_txtSearchTermKeyReleased

    private void loadData() {
        final var model = new DefaultListModel<String>();
        items.forEach(item -> model.addElement(item.toString()));
        lstMain.setModel(model);
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFilter;
    private javax.swing.JList<String> lstMain;
    private javax.swing.JTextField txtSearchTerm;
    // End of variables declaration//GEN-END:variables
}
