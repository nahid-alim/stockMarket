package com.rbc.stock.repository;

import com.rbc.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByStockSymbol(String stockSymbol);
}