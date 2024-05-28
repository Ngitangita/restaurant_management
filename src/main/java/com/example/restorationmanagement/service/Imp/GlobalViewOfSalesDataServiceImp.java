package com.example.restorationmanagement.service.Imp;

import com.example.restorationmanagement.dto.GlobalViewOfSalesData;
import com.example.restorationmanagement.repositories.GlobalViewOfSalesDataRepository;
import com.example.restorationmanagement.service.GlobalViewOfSalesDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalViewOfSalesDataServiceImp implements GlobalViewOfSalesDataService {
    private final GlobalViewOfSalesDataRepository globalViewOfSalesDataRepository;

    public GlobalViewOfSalesDataServiceImp(GlobalViewOfSalesDataRepository globalViewOfSalesDataRepository) {
        this.globalViewOfSalesDataRepository = globalViewOfSalesDataRepository;
    }

    @Override
    public List<GlobalViewOfSalesData> findAll() {
        try {
            return globalViewOfSalesDataRepository.findListGlobalViewOfSalesData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
