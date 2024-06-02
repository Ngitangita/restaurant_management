package com.example.restorationmanagement.service;

import com.example.restorationmanagement.dto.request.UpdateStockRequest;
import com.example.restorationmanagement.dto.response.UpdateStockResponse;

public interface UpdateStockService {

    UpdateStockResponse update(UpdateStockRequest toUpdate);
}
