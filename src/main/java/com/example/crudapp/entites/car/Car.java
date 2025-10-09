package com.example.crudapp.entites.car;

import java.util.Objects;

public class Car {
    private String number;
    private int maxSeat;
    private CarColor color;
    private CarBrand brand;

    public Car() {
    }

    public Car(String number, int maxSeat, CarColor color, CarBrand brand) {
        this.number = number;
        this.maxSeat = maxSeat;
        this.color = color;
        this.brand = brand;
    }

    //Геттеры
    public int getMaxSeat() {
        return maxSeat;
    }

    public void setMaxSeat(int maxSeat) {
        this.maxSeat = maxSeat;
    }

    public String getNumber() {
        return number;
    }

    //Сеттеры
    public void setNumber(String number) {
        this.number = number;
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

        Car car = (Car) o;
        return maxSeat == car.maxSeat && Objects.equals(number, car.number) && Objects.equals(color, car.color) && Objects.equals(brand, car.brand);
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
        return "Car{" +
                "number='" + number + '\'' +
                ", maxSeat=" + maxSeat +
                ", color=" + color +
                ", brand=" + brand +
                '}';
    }
}
