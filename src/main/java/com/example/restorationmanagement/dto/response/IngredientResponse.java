package com.example.restorationmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class IngredientResponse {
    private Integer id;
    private String name;
    private Double quantity;
    private Double costPrice;
}
