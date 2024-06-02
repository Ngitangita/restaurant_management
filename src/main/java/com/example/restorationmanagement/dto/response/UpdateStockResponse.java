package com.example.restorationmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateStockResponse {
    private Integer IngredientId;
    private Integer RestaurantId;
    private Double quantity;
}
