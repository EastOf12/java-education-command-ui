package com.example.crudapp.entites.trip;

import java.util.Objects;

public class TripStatus {
    private String status;

    public TripStatus() {
    }

    public TripStatus(String status) {
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
        return "TripStatus{" +
                "status='" + status + '\'' +
                '}';
    }
}
