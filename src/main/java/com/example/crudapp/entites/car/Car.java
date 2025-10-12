package com.example.crudapp.entites.car;

import com.example.crudapp.api.Entity;

import java.util.Objects;

public class Car extends Entity {
    private String number;
    private int maxSeat;
    private CarColor color;
    private CarBrand brand;
    private Long userid;

    public Car() {
    }

    public Car(String number, int maxSeat, CarColor color, CarBrand brand, Long userId) {
        this.number = number;
        this.maxSeat = maxSeat;
        this.color = color;
        this.brand = brand;
        this.userid = userId;
    }

    //Геттеры
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

    //Сеттеры
    public void setColor(CarColor color) {
        this.color = color;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;
        return maxSeat == car.maxSeat && Objects.equals(number, car.number) && Objects.equals(color, car.color) && Objects.equals(brand, car.brand) && Objects.equals(userid, car.userid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(number);
        result = 31 * result + maxSeat;
        result = 31 * result + Objects.hashCode(color);
        result = 31 * result + Objects.hashCode(brand);
        result = 31 * result + Objects.hashCode(userid);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number='" + number + '\'' +
                ", maxSeat=" + maxSeat +
                ", color=" + color +
                ", brand=" + brand +
                ", userid=" + userid +
                ", id=" + id +
                '}';
    }
}
