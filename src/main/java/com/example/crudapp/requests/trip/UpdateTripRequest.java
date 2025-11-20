package com.example.crudapp.requests.trip;

import com.example.crudapp.entites.trip.TripScheduling;
import com.example.crudapp.entites.trip.TripStatus;

import java.util.Objects;

public class UpdateTripRequest {
    private Long driverId;
    private String description;
    private Integer seats;
    private Integer cost;
    private TripStatus tripStatus;
    private TripScheduling tripScheduling;

    public UpdateTripRequest() {
        this.tripScheduling = new TripScheduling();
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
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
    public String toString() {
        return "UpdateTripRequest{" +
                "driverId=" + driverId +
                ", description='" + description + '\'' +
                ", seats=" + seats +
                ", cost=" + cost +
                ", tripStatus=" + tripStatus +
                ", tripScheduling=" + tripScheduling +
                '}';
    }
}
