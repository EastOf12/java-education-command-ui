package com.example.crudapp.entites.trip;

import com.example.crudapp.api.Entity;
import com.example.crudapp.entites.user.User;
import java.time.LocalDateTime;
import java.util.Objects;


public class Trip extends Entity {
    private User creator;
    private User driver;
    private LocalDateTime createdAt;
    private String description;
    private int seats;
    private int cost;
    private TripStatus tripStatus;
    private TripScheduling tripScheduling;

    public Trip() {
    }

    public Trip(
            User creator,
            User driver,
            LocalDateTime createdAt,
            String description,
            int seats,
            int cost,
            TripStatus tripStatus,
            TripScheduling tripScheduling
    ) {
        this.creator = creator;
        this.driver = driver;
        this.createdAt = createdAt;
        this.description = description;
        this.seats = seats;
        this.cost = cost;
        this.tripStatus = tripStatus;
        this.tripScheduling = tripScheduling;
    }

    //Геттеры
    public User getCreator() {
        return creator;
    }

    public User getDriver() {
        return driver;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public int getSeats() {
        return seats;
    }

    public int getCost() {
        return cost;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public TripScheduling getTripScheduling() {
        return tripScheduling;
    }


    //Сеттеры

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public void setTripScheduling(TripScheduling tripScheduling) {
        this.tripScheduling = tripScheduling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;
        return seats == trip.seats && cost == trip.cost && Objects.equals(creator, trip.creator) && Objects.equals(driver, trip.driver) && Objects.equals(createdAt, trip.createdAt) && Objects.equals(description, trip.description) && Objects.equals(tripStatus, trip.tripStatus) && Objects.equals(tripScheduling, trip.tripScheduling);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(creator);
        result = 31 * result + Objects.hashCode(driver);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + seats;
        result = 31 * result + cost;
        result = 31 * result + Objects.hashCode(tripStatus);
        result = 31 * result + Objects.hashCode(tripScheduling);
        return result;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "creator=" + creator +
                ", driver=" + driver +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", seats=" + seats +
                ", cost=" + cost +
                ", tripStatus=" + tripStatus +
                ", tripScheduling=" + tripScheduling +
                ", id=" + id +
                '}';
    }
}
