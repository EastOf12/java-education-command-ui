package com.example.crudapp.requests.trip;

import com.example.crudapp.entites.trip.TripScheduling;

import java.util.Objects;

public class CreateTripRequest {
    private Long creatorId;
    private Long driverId;
    private String description;
    private int seats;
    private int cost;
    private TripScheduling tripScheduling;

    public CreateTripRequest() {
        this.tripScheduling = new TripScheduling();
    }

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

    public TripScheduling getTripScheduling() {
        return tripScheduling;
    }

    public void setTripScheduling(TripScheduling tripScheduling) {
        this.tripScheduling = tripScheduling;
    }

    @Override
    public String toString() {
        return "CreateTripRequest{" +
                "creatorId=" + creatorId +
                ", driverId=" + driverId +
                ", description='" + description + '\'' +
                ", seats=" + seats +
                ", cost=" + cost +
                ", tripScheduling=" + tripScheduling +
                '}';
    }
}
