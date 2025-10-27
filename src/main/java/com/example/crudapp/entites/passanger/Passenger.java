package com.example.crudapp.entites.passanger;

import com.example.crudapp.api.Entity;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;

import java.time.LocalDateTime;
import java.util.Objects;


public class Passenger extends Entity {
    private User user;
    private PassengerStatus passengerStatus;
    private Trip trip;
    private LocalDateTime createdAt;
    private int seats;
    private String message;

    public Passenger() {
    }

    public Passenger(User user, PassengerStatus passengerStatus, Trip trip, LocalDateTime createdAt, int seats, String message) {
        this.user = user;
        this.passengerStatus = passengerStatus;
        this.trip = trip;
        this.createdAt = createdAt;
        this.seats = seats;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PassengerStatus getPassengerStatus() {
        return passengerStatus;
    }

    public void setPassengerStatus(PassengerStatus passengerStatus) {
        this.passengerStatus = passengerStatus;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passenger passenger = (Passenger) o;
        return seats == passenger.seats && Objects.equals(user, passenger.user) && Objects.equals(passengerStatus, passenger.passengerStatus) && Objects.equals(trip, passenger.trip) && Objects.equals(createdAt, passenger.createdAt) && Objects.equals(message, passenger.message);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(passengerStatus);
        result = 31 * result + Objects.hashCode(trip);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + seats;
        result = 31 * result + Objects.hashCode(message);
        return result;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "user=" + user +
                ", passengerStatus=" + passengerStatus +
                ", trip=" + trip +
                ", createdAt=" + createdAt +
                ", seats=" + seats +
                ", message='" + message + '\'' +
                '}';
    }
}
