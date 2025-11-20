package com.example.crudapp.entites.car;

import java.util.Objects;

public class CarBrand {
    private String name;

    public CarBrand() {
    }

    public CarBrand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "name='" + name + '\'' +
                '}';
    }
}
