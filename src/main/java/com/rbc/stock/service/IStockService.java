package com.rbc.stock.service;

import com.rbc.stock.api.dto.StockDTO;
import java.util.List;

public interface IStockService {
    List<StockDTO> getStocksBySymbol(String stockSymbol);

    String createStock(StockDTO stockDTO);

    void createStocksList(List<StockDTO> stocks);
}
