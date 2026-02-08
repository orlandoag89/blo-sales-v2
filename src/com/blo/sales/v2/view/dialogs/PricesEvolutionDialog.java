package com.blo.sales.v2.view.dialogs;

import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.pojos.PojoStockPriceHistory;
import com.blo.sales.v2.view.pojos.WrapperPojoStockPriceHistory;
import java.awt.Component;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

public class PricesEvolutionDialog extends javax.swing.JDialog {

    private WrapperPojoStockPriceHistory pricesEvolution;

    public PricesEvolutionDialog(Component parent, boolean modal, WrapperPojoStockPriceHistory pricesEvolution) {
        super(SwingUtilities.getWindowAncestor(parent), "", ModalityType.APPLICATION_MODAL);
        this.pricesEvolution = pricesEvolution;
        initComponents();
        createGraphic();

        this.setLocationRelativeTo(null);
    }

   private void createGraphic() {
    if (pricesEvolution.getHistory() == null || pricesEvolution.getHistory().isEmpty()) {
        return;
    }

    final var dataset = new DefaultCategoryDataset();

    // 1. Llenar Dataset usando el Timestamp
    for (final var item : pricesEvolution.getHistory()) {
        // Extraemos solo la hora del timestamp para que el eje X sea legible
        // Ejemplo: "2026-02-08T10:05:51.535" -> "10:05:51"
        final var fullTimestamp = item.getTimestamp();
        final var date = fullTimestamp.split("\\.")[0];
       
        dataset.addValue(item.getPrice(), "Precio de venta", date);
        dataset.addValue(item.getCostOfSale(), "Costo de venta", date);
    }

    // 2. Crear Chart con etiquetas de tiempo
    final var chart = ChartFactory.createLineChart(
            "Evolución de: " + pricesEvolution.getHistory().get(0).getProduct(),
            "Hora del Registro", // Etiqueta del eje X
            "Pesos ($)",
            dataset
    );

    // Configuración del Plot
    final var plot = (CategoryPlot) chart.getPlot();
    
    // Rotar etiquetas para evitar que se encimen si hay muchos registros
    plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45); 

    // Configurar el eje Y (Range Axis)
    final var rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setAutoRangeIncludesZero(false); // Zoom dinámico para ver cambios pequeños
    
    // Opcional: Añadir un margen del 10% arriba y abajo para que las líneas no toquen los bordes
    rangeAxis.setUpperMargin(0.10);
    rangeAxis.setLowerMargin(0.10);

    // 3. Configurar el Panel y el JDialog
    final var chartPanel = new ChartPanel(chart);
    chartPanel.setMouseWheelEnabled(true); // Permite hacer zoom con el scroll

    this.setLayout(new java.awt.BorderLayout());
    this.getContentPane().removeAll();
    this.add(chartPanel, java.awt.BorderLayout.CENTER);

    // 4. Forzar actualización visual
    this.revalidate();
    this.repaint();
    this.pack();
    this.setSize(800, 600);
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
