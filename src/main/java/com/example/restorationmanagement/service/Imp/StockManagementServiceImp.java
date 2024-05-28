package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.dto.StockManagement;
import com.example.restorationmanagement.repositories.StockManagementRepository;
import com.example.restorationmanagement.service.StockManagementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockManagementServiceImp implements StockManagementService {
    private final StockManagementRepository stockManagementRepository;

    public StockManagementServiceImp(StockManagementRepository stockManagementRepository) {
        this.stockManagementRepository = stockManagementRepository;
    }

    @Override
    public List<StockManagement> findAll() {
        try {
            return stockManagementRepository.findStockManagement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
