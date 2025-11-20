package com.example.crudapp.requests.passenger;

import java.util.Objects;

public class CreatePassengerRequest {
    private Long userId;
    private Long tripID;
    private Integer seats;
    private String message;

    public CreatePassengerRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTripID() {
        return tripID;
    }

    public void setTripID(Long tripID) {
        this.tripID = tripID;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CreatePassengerRequest{" +
                "userId=" + userId +
                ", tripID=" + tripID +
                ", seats=" + seats +
                ", message='" + message + '\'' +
                '}';
    }
}
