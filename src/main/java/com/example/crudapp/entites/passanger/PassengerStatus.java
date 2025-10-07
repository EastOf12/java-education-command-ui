package com.example.crudapp.entites.passanger;

import java.util.Objects;

public class PassengerStatus {
    private String status;

    public PassengerStatus() {
    }

    public PassengerStatus(String status) {
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

        PassengerStatus that = (PassengerStatus) o;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(status);
    }

    @Override
    public String toString() {
        return "PassengerStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
