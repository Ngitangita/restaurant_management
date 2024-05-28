package com.example.restorationmanagement.repositories;

import com.example.restorationmanagement.dto.GlobalViewOfSalesData;

import java.util.List;

public interface GlobalViewOfSalesDataRepository {
    List<GlobalViewOfSalesData> findListGlobalViewOfSalesData();
}
