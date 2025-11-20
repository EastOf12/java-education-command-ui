package com.example.crudapp.entites.trip;

import com.example.crudapp.ObjectUtils;
import com.example.crudapp.api.Entity;

import java.time.LocalDateTime;


public class Trip extends Entity {
    private Long creatorId;
    private Long driverId;
    private LocalDateTime createdAt;
    private String description;
    private int seats;
    private int cost;
    private TripStatus tripStatus;
    private TripScheduling tripScheduling;

    public Trip() {
    }

    public Trip(
            Long creatorId,
            Long driverId,
            String description,
            int seats,
            int cost,
            TripScheduling tripScheduling
    ) {
        this.creatorId = creatorId;
        this.driverId = driverId;
        this.createdAt = LocalDateTime.now();
        this.description = description;
        this.seats = seats;
        this.cost = cost;
        this.tripStatus = new TripStatus("new");
        this.tripScheduling = tripScheduling;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreator(Long creatorId) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
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

    @Override
    public boolean equals(Object o) {
        return ObjectUtils.equals(this, o, "creatorId", "driverId", "createdAt", "description", "seats",
                "cost", "tripStatus", "tripScheduling");
    }

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(this,"creatorId", "driverId", "createdAt", "description",
                "seats", "cost", "tripStatus", "tripScheduling");
    }

    @Override
    public String toString() {
        return "Trip{" +
                "creatorId=" + creatorId +
                ", driverId=" + driverId +
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
