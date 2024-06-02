package com.example.restorationmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateStockRequest {
    private Integer ingredientId;
    private Integer restaurantId;
    private Double quantity;
}
