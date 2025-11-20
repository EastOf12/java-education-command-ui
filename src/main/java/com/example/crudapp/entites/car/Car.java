package com.example.crudapp.entites.car;

import com.example.crudapp.ObjectUtils;
import com.example.crudapp.api.Entity;

import java.util.Objects;

public class Car extends Entity {
    private String number;
    private Integer maxSeat;
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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        return ObjectUtils.equals(this, o, "number", "maxSeat", "color", "brand", "userid");
    }

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(this, "number", "maxSeat", "color", "brand", "userid");
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
