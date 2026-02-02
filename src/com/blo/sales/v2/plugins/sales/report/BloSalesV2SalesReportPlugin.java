package com.blo.sales.v2.plugins.sales.report;

import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.pojos.WrapperPojoSalesAndStock;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class BloSalesV2SalesReportPlugin {
    
    private BloSalesV2SalesReportPlugin() { }
    
    public static void createReport(WrapperPojoSalesAndStock lst, BigDecimal total) throws BloSalesV2Exception {
        final var rows = new ArrayList<RowSaleReportData>();
        var totalCostOfSale = BigDecimal.ZERO;
        RowSaleReportData r = null;
        for (final var item: lst.getSalesDetail()) {
            r = new RowSaleReportData();
            r.setProduct(item.getProduct());
            r.setIdProduct(item.getIdProduct());
            r.setCostOfSale(item.getCostOfSale());
            r.setPrice(item.getPrice());
            r.setQuantityOnSale(item.getQuantityOnSale());
//            r.setTotalOnSale(item.getTotalOnSale());
            r.setTimestamp(item.getTimestamp());
            if (item.getTotalOnSale().compareTo(BigDecimal.ZERO) != 0) {
                totalCostOfSale = totalCostOfSale.add(item.getCostOfSale());
            }
            rows.add(r);
        }
        final var ganancia = total.subtract(totalCostOfSale);
        final var str = new StringBuilder();
        
        for (final var row: rows) {
            str.append(row.toString());
            str.append("\n");          
        }
        str.append("Total: $" + total + "\n");
        str.append("Costo de venta total: $" + totalCostOfSale + "\n");
        str.append("Total - costo de venta: $" + ganancia + "\n");
        
        try {
            saveFile(str.toString());
        } catch (IOException ex) {
            throw new BloSalesV2Exception(BloSalesV2Utils.COMMON_RULE_CODE, BloSalesV2Utils.REPORT_EXCEPTIONS);
        }
    }
    
    private static void saveFile(String str) throws IOException {
        final var fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            var archivo = fileChooser.getSelectedFile();
            // Asegurar la extensión .txt
            final var ruta = archivo.getAbsolutePath();
            if (!ruta.endsWith(".txt")) {
                archivo = new File(ruta + ".txt");
            }

            final var bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(str);
            bw.flush();
            JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente.");
        }
    }

}
