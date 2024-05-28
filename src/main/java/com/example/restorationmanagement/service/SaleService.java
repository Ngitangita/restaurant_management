package com.example.restorationmanagement.service;

import com.example.restorationmanagement.entities.Sale;

import java.util.List;

public interface SaleService {
    Sale createSale(Sale sale);
    List<Sale> findAll();
    Sale findSaleById(Integer id);
    Sale updateSale(Integer id, Sale sale);
    Sale destroySaleById(Integer id);
}
