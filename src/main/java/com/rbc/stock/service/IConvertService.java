package com.rbc.stock.service;

import com.rbc.stock.api.dto.StockDTO;
import com.rbc.stock.model.Stock;

public interface IConvertService {
    Stock stockDTOtoStock(StockDTO stockDTO);

    StockDTO stockToStockDTO(Stock stock);
}
