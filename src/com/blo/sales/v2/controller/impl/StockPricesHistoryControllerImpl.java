package com.blo.sales.v2.controller.impl;

import com.blo.sales.v2.controller.IPricesHistoryController;
import com.blo.sales.v2.controller.IStockPricesHistoryController;
import com.blo.sales.v2.controller.pojos.PojoIntPriceHistory;
import com.blo.sales.v2.controller.pojos.PojoIntStockPricesHistory;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntStockPriceHistory;
import com.blo.sales.v2.model.IStockPricesHistoryModel;
import com.blo.sales.v2.model.impl.StockPricesHistoryModelImpl;
import com.blo.sales.v2.utils.BloSalesV2Exception;
import com.blo.sales.v2.utils.BloSalesV2Utils;
import com.blo.sales.v2.view.commons.GUILogger;

public class StockPricesHistoryControllerImpl implements IStockPricesHistoryController {

    private static final GUILogger logger = GUILogger.getLogger(StockPricesHistoryControllerImpl.class.getName());
    
    private static final IStockPricesHistoryModel model = StockPricesHistoryModelImpl.getInstance();
    
    private static final IPricesHistoryController pricesController = PricesHistoryControllerImpl.getInstance();
    
    private static StockPricesHistoryControllerImpl instance;
    
    private StockPricesHistoryControllerImpl() { }
    
    public static StockPricesHistoryControllerImpl getInstance() {
        if (instance == null) {
            instance = new StockPricesHistoryControllerImpl();
        }
        return instance;
    }    

    @Override
    public PojoIntStockPricesHistory addPriceOnHistory(PojoIntPriceHistory priceItem, long idProduct) throws BloSalesV2Exception {
        logger.log("guardando item " + priceItem.toString());
        final var saved = pricesController.addPriceHistory(priceItem);
        logger.log("precio guardado " + saved.toString());
        final var item = new PojoIntStockPricesHistory();
        item.setFkProduct(idProduct);
        item.setFkPriceHistory(saved.getIdPriceHistory());
        item.setTimesTamp(BloSalesV2Utils.getTimestamp());
        final var relationSaved = model.addPriceOnHistory(item);
        logger.log("item guardod [relacion precio stock] " + relationSaved.toString());
        return relationSaved;
    }

    @Override
    public WrapperPojoIntStockPriceHistory getPriceFromProduct(long idProduct) throws BloSalesV2Exception {
        logger.log("recuperando historial de precios " + idProduct);
        return model.getPriceFromProduct(idProduct);
    }

}
