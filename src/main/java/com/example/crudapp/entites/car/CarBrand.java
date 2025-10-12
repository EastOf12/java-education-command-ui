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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarBrand carBrand = (CarBrand) o;
        return Objects.equals(name, carBrand.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "name='" + name + '\'' +
                '}';
    }
}
