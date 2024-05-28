package com.example.restorationmanagement.entities;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Restaurant {
    private Integer id;
    private String location;
}
