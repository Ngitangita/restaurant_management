package com.example.restorationmanagement.entities;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Sale {
    private Integer id;
    private Restaurant restaurant;
    private Menu menu;
    private Instant saleDate;
    private Integer quantity;
    private Double price;
}
