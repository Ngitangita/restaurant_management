package com.example.restorationmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class QuantityIngredientRequest {
    private Integer ingredientId;
    private Double quantity;
}
