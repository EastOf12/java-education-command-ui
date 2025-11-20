package com.example.crudapp.entites.trip;

import java.time.LocalDateTime;
import java.util.Objects;


public class TripScheduling {
    private LocalDateTime planedDepartureDateTime;
    private LocalDateTime planedArrivalDateTime;

    public TripScheduling() {
    }

    public TripScheduling(
            LocalDateTime planedDepartureDateTime,
            LocalDateTime planedArrivalDateTime
    ) {
        this.planedDepartureDateTime = planedDepartureDateTime;
        this.planedArrivalDateTime = planedArrivalDateTime;
    }

    public LocalDateTime getPlanedDepartureDateTime() {
        return planedDepartureDateTime;
    }

    public void setPlanedDepartureDateTime(LocalDateTime planedDepartureDateTime) {
        this.planedDepartureDateTime = planedDepartureDateTime;
    }

    public LocalDateTime getPlanedArrivalDateTime() {
        return planedArrivalDateTime;
    }

    public void setPlanedArrivalDateTime(LocalDateTime planedArrivalDateTime) {
        this.planedArrivalDateTime = planedArrivalDateTime;
    }

    @Override
    public String toString() {
        return "TripScheduling{" +
                "planedDepartureDateTime=" + planedDepartureDateTime +
                ", planedArrivalDateTime=" + planedArrivalDateTime +
                '}';
    }
}
