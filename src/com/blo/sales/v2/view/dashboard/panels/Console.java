package com.blo.sales.v2.view.dashboard.panels;

import com.blo.sales.v2.view.commons.GUILogger;
import java.awt.Color;
import java.awt.Font;

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

        txtArea.setColumns(20);
        txtArea.setRows(5);
        scrllConsole.setViewportView(txtArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrllConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrllConsole, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrllConsole;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}
