package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.plugins.writter.BloSalesV2WritterFile;
import com.blo.sales.v2.plugins.writter.enums.ExtensionEnum;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.commons.GUICommons;
import com.blo.sales.v2.view.commons.GUILogger;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

public class Console extends javax.swing.JPanel {
    
    private static final GUILogger logger = GUILogger.getLogger(Console.class.getName());

    public Console() {
        initComponents();
        txtArea.setBackground(Color.BLACK);
        txtArea.setForeground(Color.WHITE);
        txtArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtArea.append(logger.getLogs());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrllConsole = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        btnDownloadLogs = new javax.swing.JButton();

        txtArea.setColumns(20);
        txtArea.setRows(5);
        scrllConsole.setViewportView(txtArea);

        btnDownloadLogs.setText("Descargar logs");
        btnDownloadLogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownloadLogsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrllConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDownloadLogs)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrllConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDownloadLogs)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDownloadLogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownloadLogsActionPerformed
        try {
            final var logs = GUICommons.getTextFromField(txtArea, false);
            BloSalesV2WritterFile.saveFile(logs, ExtensionEnum.LOG);
        } catch (BloSalesV2Exception ex) {
            logger.error(ex.getMessage());
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }//GEN-LAST:event_btnDownloadLogsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDownloadLogs;
    private javax.swing.JScrollPane scrllConsole;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
