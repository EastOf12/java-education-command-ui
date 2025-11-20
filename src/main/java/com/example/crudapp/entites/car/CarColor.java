package com.example.crudapp.entites.car;

import java.util.Objects;

public class CarColor {
    private String name;

    public CarColor() {
    }

    public CarColor(String name) {
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
        return "CarColor{" +
                "name='" + name + '\'' +
                '}';
    }
}
