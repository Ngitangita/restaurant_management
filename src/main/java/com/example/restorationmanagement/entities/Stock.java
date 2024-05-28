package com.example.restorationmanagement.entities;

import com.example.restorationmanagement.enumes.MovementType;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Stock {
    private Integer id;
    private Restaurant restaurant;
    private Ingredient ingredient;
    private MovementType movementType;
    private Double quantity;
    private Instant movementDate;
    
}
