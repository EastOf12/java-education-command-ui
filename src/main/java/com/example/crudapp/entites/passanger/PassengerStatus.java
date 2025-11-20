package com.example.crudapp.entites.passanger;

import java.util.Objects;

public class PassengerStatus {
    private String status;

    public PassengerStatus() {
    }

    public PassengerStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PassengerStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
