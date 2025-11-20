package com.example.crudapp.requests.car;

import com.example.crudapp.entites.car.CarBrand;
import com.example.crudapp.entites.car.CarColor;

import java.util.Objects;

public class UpdateCarRequest {
    private String number;
    private Integer maxSeat;
    private CarColor color;
    private CarBrand brand;

    public UpdateCarRequest() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getMaxSeat() {
        return maxSeat;
    }

    public void setMaxSeat(int maxSeat) {
        this.maxSeat = maxSeat;
    }

    public CarColor getColor() {
        return color;
    }

    public void setColor(CarColor color) {
        this.color = color;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "UpdateCarRequest{" +
                "number='" + number + '\'' +
                ", maxSeat=" + maxSeat +
                ", color=" + color +
                ", brand=" + brand +
                '}';
    }
}
