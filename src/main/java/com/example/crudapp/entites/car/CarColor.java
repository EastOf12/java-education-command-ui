package com.example.crudapp.entites.car;

import com.example.crudapp.api.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CarColor extends Entity {
    private String name;
}
