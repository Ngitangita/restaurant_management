package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.dto.StockManagement;

import java.util.List;

public interface StockManagementRepository {
    List<StockManagement> findStockManagement();
}
