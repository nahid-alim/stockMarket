package com.rbc.stock.service;

import com.rbc.stock.api.dto.StockDTO;
import com.rbc.stock.exception.StockNotFound;
import com.rbc.stock.model.Stock;
import com.rbc.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService implements IStockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    ConvertService convertService;

    @Override
    public List<StockDTO> getStocksBySymbol(String stockSymbol) {

        List<StockDTO> records = stockRepository.findByStockSymbol(stockSymbol)
                .stream()
                .map(stock -> convertService.stockToStockDTO(stock))
                .collect(Collectors.toList());
        if(records.size() > 0)
            return records;
        else
            throw new StockNotFound();
    }

    @Override
    public String createStock(StockDTO stockDTO) {

        Stock stockToCreate = convertService.stockDTOtoStock(stockDTO);
        return stockRepository.save(stockToCreate).getId();
    }

    @Override
    public void createStocksList(List<StockDTO> stocks) {

        stocks.forEach(stockDTO -> stockRepository.save(convertService.stockDTOtoStock(stockDTO)));
    }
}
