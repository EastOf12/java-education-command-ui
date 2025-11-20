package com.example.crudapp.entites.passanger;

import com.example.crudapp.ObjectUtils;
import com.example.crudapp.api.Entity;

import java.time.LocalDateTime;
import java.util.Objects;


public class Passenger extends Entity {
    private Long userId;
    private PassengerStatus passengerStatus;
    private Long tripID;
    private LocalDateTime createdAt;
    private int seats;
    private String message;

    public Passenger() {
    }

    public Passenger(Long userId, Long tripID, int seats, String message) {
        this.userId = userId;
        this.passengerStatus = new PassengerStatus("new");
        this.tripID = tripID;
        this.createdAt = LocalDateTime.now();
        this.seats = seats;
        this.message = message;
    }

    public Long getUser() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }

    public PassengerStatus getPassengerStatus() {
        return passengerStatus;
    }

    public void setPassengerStatus(PassengerStatus passengerStatus) {
        this.passengerStatus = passengerStatus;
    }

    public Long getTrip() {
        return tripID;
    }

    public void setTrip(Long tripID) {
        this.tripID = tripID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        return ObjectUtils.equals(this, o, "userId", "passengerStatus", "tripID", "createdAt", "seats", "message");
    }

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(this,"userId", "passengerStatus", "tripID", "createdAt", "seats", "message");
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "userId=" + userId +
                ", passengerStatus=" + passengerStatus +
                ", tripID=" + tripID +
                ", createdAt=" + createdAt +
                ", seats=" + seats +
                ", message='" + message + '\'' +
                '}';
    }
}
