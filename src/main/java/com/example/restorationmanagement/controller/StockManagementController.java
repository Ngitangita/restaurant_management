package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.dto.StockManagement;
import com.example.restorationmanagement.service.StockManagementService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class StockManagementController {
    private final StockManagementService stockManagementService;

    public StockManagementController(StockManagementService stockManagementService) {
        this.stockManagementService = stockManagementService;
    }

    @GetMapping("/stockManagement")
    public List<StockManagement> getStockManagement(){
        return stockManagementService.findAll();
    }
}
