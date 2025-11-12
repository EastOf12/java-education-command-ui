package com.example.crudapp.requests.car;

import com.example.crudapp.entites.car.CarBrand;
import com.example.crudapp.entites.car.CarColor;

import java.util.Objects;

public class CreateCarRequest {
    private String number;
    private int maxSeat;
    private CarColor color;
    private CarBrand brand;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Long userId;

    public CreateCarRequest() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMaxSeat() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateCarRequest that = (CreateCarRequest) o;
        return maxSeat == that.maxSeat && Objects.equals(number, that.number) && Objects.equals(color, that.color) && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(number);
        result = 31 * result + maxSeat;
        result = 31 * result + Objects.hashCode(color);
        result = 31 * result + Objects.hashCode(brand);
        return result;
    }

    @Override
    public String toString() {
        return "CreateCarRequest{" +
                "number='" + number + '\'' +
                ", maxSeat=" + maxSeat +
                ", color=" + color +
                ", brand=" + brand +
                '}';
    }
}
