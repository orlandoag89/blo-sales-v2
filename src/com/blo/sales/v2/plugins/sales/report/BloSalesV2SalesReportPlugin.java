package com.blo.sales.v2.plugins.sales.report;

import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.pojos.PojoSaleAndProduct;
import com.blo.sales.v2.view.pojos.WrapperPojoSalesAndStock;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public final class BloSalesV2SalesReportPlugin {
    
    private BloSalesV2SalesReportPlugin() { }
    
    public static void createReport(WrapperPojoSalesAndStock lst, BigDecimal total) throws BloSalesV2Exception {
        final var rows = new ArrayList<RowSaleReportData>();
        RowSaleReportData r = null;
        for (final var item: lst.getSalesDetail()) {
            r = new RowSaleReportData();
            r.setIdProduct(item.getIdProduct());
            r.setCostOfSale(item.getCostOfSale());
            r.setPrice(item.getPrice());
            r.setProduct(item.getProduct());
            r.setQuantityOnSale(item.getQuantityOnSale());
            r.setTimestamp(item.getTimestamp());
            var profit = item.getPrice().subtract(item.getCostOfSale());
            if (item.isKg()) {
             final var costOfPortion = item.getQuantityOnSale().multiply(item.getPrice());
            }
            r.setProfit(profit);
            rows.add(r);
        }
        
        
        /*System.out.println(lst);
        //crear una lista con los ids no duplicados
        final var idsUniques = lst.getSalesDetail().stream().collect(Collectors.toMap(
                PojoSaleAndProduct::getIdSale, // Clave para identificar duplicados
                obj -> obj,// El objeto en sí
                (existente, reemplazo) -> existente // Si hay duplicado, se queda con el primero
            ))
            .values()
            .stream()
            .map(PojoSaleAndProduct::getIdSale)
            .collect(Collectors.toList());
        // mapa para guardar todas las ventas con el mismo id
        final Map<Long, List<PojoSaleAndProduct>> salesDetail = new HashMap<>();
        for (final var id: idsUniques) {
            // recuperar todas las ventas con el mismo id
            final var salesIdEq = lst.getSalesDetail().stream().filter(s -> s.getIdSale() == id).collect(Collectors.toList());
            // guardarlas en el mapa
            salesDetail.put(id, salesIdEq);
        }
        final var rows = new ArrayList<RowSaleReportData>();
        RowSaleReportData r = null;
        for (List<PojoSaleAndProduct> entry: salesDetail.values()) {
            // validar si hay ventas que tienen productos por kg
            final var totalOnSale = entry.get(0).getTotalOnSale();
            final var saleWithProductKg = entry.stream().filter(s -> s.isKg()).collect(Collectors.toList());
            // caso A: no hay productos por kg en la venta
            if (saleWithProductKg.isEmpty()) {
                for (final var data: entry){
                    r = mapPojoSaleAndProductToRowSaleReportData(data, false);
                    rows.add(r);
                }
            }
            System.out.println("");
        }
        /*final var rows = new ArrayList<RowSaleReportData>();
        var totalCostOfSale = BigDecimal.ZERO;
        RowSaleReportData r = null;
        for (final var item: lst.getSalesDetail()) {
            r = new RowSaleReportData();
            r.setProduct(item.getProduct());
            r.setIdProduct(item.getIdProduct());
            r.setCostOfSale(item.getCostOfSale());
            r.setPrice(item.getPrice());
            r.setQuantityOnSale(item.getQuantityOnSale());
            if (item.isKg()) {
                System.out.println(item.toString());
                final var amoutMultCost = item.getTotalOnSale().multiply(item.getCostOfSale());
                final var ganancy = amoutMultCost.divide(item.getPrice(),2, RoundingMode.HALF_UP);
                System.out.println(ganancy);
             //   totalCostOfSale = item.getTotalOnSale().multiply(item.getPrice()).divide(item.getCostOfSale());
            }
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
        }*/
    }
    
    private static RowSaleReportData mapPojoSaleAndProductToRowSaleReportData(PojoSaleAndProduct data, boolean byKg) {
        final var out = new RowSaleReportData();
        out.setCostOfSale(data.getCostOfSale());
        out.setIdProduct(data.getIdProduct());
        out.setPrice(data.getPrice());
        out.setProduct(data.getProduct());
        out.setQuantityOnSale(data.getQuantityOnSale());
        out.setTimestamp(data.getTimestamp());
        final var profit = data.getPrice().subtract(data.getCostOfSale());
        out.setProfit(profit);
        return out;
    }
    
    private static BigDecimal getProfit(BigDecimal saleMoney, BigDecimal cost, BigDecimal costOfSale) {
        final var total = saleMoney.multiply(cost).divide(costOfSale,2, RoundingMode.HALF_UP);
        return saleMoney.subtract(total);
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
