package com.example.restorationmanagement.entities;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Ingredient {
    private Integer id;
    private Unit unit;
    private String name;
    private Double costPrice;
    private Double quantity;

}
