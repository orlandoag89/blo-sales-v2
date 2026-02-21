package com.blo.sales.v2.plugins.sales.report;

import com.blo.sales.v2.plugins.writter.BloSalesV2WritterFile;
import com.blo.sales.v2.plugins.writter.enums.ExtensionEnum;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.view.pojos.PojoSaleAndProduct;
import com.blo.sales.v2.view.pojos.WrapperPojoSalesAndStock;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class BloSalesV2SalesReportPlugin {
    
    private static final String REPORT_EXCEPTION_CODE = "P-R001";
    
    private static final int PAYMENTS = 1;
    
    private BloSalesV2SalesReportPlugin() { }
    
    public static void createReport(WrapperPojoSalesAndStock lst, BigDecimal total) throws BloSalesV2Exception {
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
        final var rowsData = new ArrayList<RowSaleData>();
        List<RowSaleReportData> rows = null;
        RowSaleReportData r = null;
        BigDecimal priceSum = BigDecimal.ZERO;
        BigDecimal ganan = BigDecimal.ZERO;
        RowSaleData rowData = null;
        for (List<PojoSaleAndProduct> entry: salesDetail.values()) {
            rows = new ArrayList<>();
            rowData = new RowSaleData();
            priceSum = BigDecimal.ZERO;
            ganan = BigDecimal.ZERO;
            for (final var item: entry) {
                rowData.setIdSale(item.getIdSale());
                r = new RowSaleReportData();
                r.setIdProduct(item.getIdProduct());
                r.setProduct(item.getProduct());
                r.setQuantityOnSale(item.getQuantityOnSale());
                r.setTimestamp(item.getTimestamp());
                r.setTotalOnSale(item.getTotalOnSale());
                r.setPrice(item.getPrice());
                r.setCostOfSale(item.getCostOfSale());
                // precio total del producto en la venta
                //final var productTotalPrice = item.getProductTotalOnSale();
                //priceSum = productTotalPrice.add(priceSum);
                // recuperar ganancia
                // multiplicar cantidad vendida X costo de venta
                final var costOfSaleQuantitySold = item.getQuantityOnSale().multiply(item.getCostOfSale());
                // multiplicar cantidad vendida X precio
                final var quantitySoldPrice = item.getQuantityOnSale().multiply(item.getPrice());
                // se recupera ganancia
                final var ganancia = quantitySoldPrice.subtract(costOfSaleQuantitySold);
                r.setProfit(ganancia.setScale(2, RoundingMode.HALF_UP));
                ganan = ganan.add(ganancia).setScale(2, RoundingMode.HALF_UP);
                if (r.getIdProduct() == PAYMENTS) {
                    ganan = item.getTotalOnSale();
                }
                rows.add(r);
            }
            rowData.setProfitOnSale(ganan);
            rowData.setRows(rows);
            rowsData.add(rowData);
        }
        
        try {
            BloSalesV2WritterFile.saveFile(createStringFromArray(rowsData), ExtensionEnum.TXT);
        } catch (IOException ex) {
            Logger.getLogger(BloSalesV2SalesReportPlugin.class.getName()).log(Level.SEVERE, null, ex);
            throw new BloSalesV2Exception(REPORT_EXCEPTION_CODE, ex.getMessage());
        }
    }
    
    private static String createStringFromArray(List<RowSaleData> lst) {
        final var str = new StringBuilder();
        var totalProfit = BigDecimal.ZERO;
        for (final var item: lst) {
            str.append("Venta: " );
            str.append(item.getIdSale());
            str.append("\n");
            str.append("--------------------------------------------------------------------------------------------------------------------------------------------");
            str.append("\n");
            for (final var prd: item.getRows()) {
                str.append(prd.toString());
                str.append("\n");
            }
            str.append("--------------------------------------------------------------------------------------------------------------------------------------------");
            str.append("\n");
            str.append("Total: ");
            str.append(item.getProfitOnSale().setScale(2, RoundingMode.HALF_UP));
            str.append("\n");
            str.append("\n");
            totalProfit = totalProfit.add(item.getProfitOnSale()).setScale(2, RoundingMode.HALF_UP);
        }
        str.append("Ganancia: $");
        str.append(totalProfit.setScale(2, RoundingMode.HALF_UP));
        return str.toString();
    }
    
}
