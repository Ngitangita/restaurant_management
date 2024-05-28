package com.example.restorationmanagement.entities;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Unit {
    private Integer id;
    private String name;
}
