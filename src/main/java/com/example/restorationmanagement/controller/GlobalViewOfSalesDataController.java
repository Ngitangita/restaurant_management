package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.dto.GlobalViewOfSalesData;
import com.example.restorationmanagement.service.GlobalViewOfSalesDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class GlobalViewOfSalesDataController {
    private final GlobalViewOfSalesDataService globalViewOfSalesDataService;

    public GlobalViewOfSalesDataController(GlobalViewOfSalesDataService globalViewOfSalesDataService) {
        this.globalViewOfSalesDataService = globalViewOfSalesDataService;
    }

    @GetMapping("/globalViewOfSalesData")
    public List<GlobalViewOfSalesData> findAllGlobalViewOfSalesData(){
        return globalViewOfSalesDataService.findAll();
    }
}
