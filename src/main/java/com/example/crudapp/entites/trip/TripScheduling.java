package com.example.crudapp.entites.trip;

import com.example.crudapp.api.Entity;

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

    //Геттеры
    public LocalDateTime getPlanedDepartureDateTime() {
        return planedDepartureDateTime;
    }

    public LocalDateTime getPlanedArrivalDateTime() {
        return planedArrivalDateTime;
    }


    //Сеттеры
    public void setPlanedDepartureDateTime(LocalDateTime planedDepartureDateTime) {
        this.planedDepartureDateTime = planedDepartureDateTime;
    }

    public void setPlanedArrivalDateTime(LocalDateTime planedArrivalDateTime) {
        this.planedArrivalDateTime = planedArrivalDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripScheduling that = (TripScheduling) o;
        return Objects.equals(planedDepartureDateTime, that.planedDepartureDateTime) && Objects.equals(planedArrivalDateTime, that.planedArrivalDateTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(planedDepartureDateTime);
        result = 31 * result + Objects.hashCode(planedArrivalDateTime);
        return result;
    }

    @Override
    public String toString() {
        return "TripScheduling{" +
                "planedDepartureDateTime=" + planedDepartureDateTime +
                ", planedArrivalDateTime=" + planedArrivalDateTime +
                '}';
    }
}
