package com.example.crudapp.entites.trip;

import java.util.Objects;

public class TripStatus{
    private String status;

    public TripStatus() {
    }

    public TripStatus(String status) {
        this.status = status;
    }

    //Геттеры
    public String getStatus() {
        return status;
    }


    //Сеттеры
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripStatus that = (TripStatus) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }

    @Override
    public String toString() {
        return "TripStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
