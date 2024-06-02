package com.example.restorationmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class QuantityIngredientResponse {
    private Integer ingredientId;
    private Double quantity;
}
