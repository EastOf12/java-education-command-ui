package com.example.crudapp.requests.trip;

import com.example.crudapp.entites.trip.TripScheduling;
import com.example.crudapp.entites.trip.TripStatus;

import java.time.LocalDateTime;

public class CreateTripRequest {
    private Long creatorId;
    private Long driverId;
    private LocalDateTime createdAt;
    private String description;
    private int seats;
    private int cost;
    private TripStatus tripStatus;
    private TripScheduling tripScheduling;

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public TripScheduling getTripScheduling() {
        return tripScheduling;
    }

    public void setTripScheduling(TripScheduling tripScheduling) {
        this.tripScheduling = tripScheduling;
    }
}
