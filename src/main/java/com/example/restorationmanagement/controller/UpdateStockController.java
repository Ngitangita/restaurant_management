package com.example.restorationmanagement.controller;

import com.example.restorationmanagement.dto.request.UpdateStockRequest;
import com.example.restorationmanagement.dto.response.UpdateStockResponse;
import com.example.restorationmanagement.service.UpdateStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/updateStocks")
public class UpdateStockController {
    private final UpdateStockService updateStockService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UpdateStockResponse updateStockResponse(@RequestBody UpdateStockRequest updateStockRequest){
        return updateStockService.update(updateStockRequest);
    }

}
