package com.example.restorationmanagement.service;

import com.example.restorationmanagement.entities.Stock;

import java.util.List;

public interface StockService {
    Stock createStock(Stock stock);
    List<Stock> findAll();
    Stock findStockById(Integer id);
    Stock updateStock(Integer id, Stock stock);
    Stock destroyStockById(Integer id);
}
