package com.example.crudapp.entites.trip;

import java.time.LocalDateTime;
import java.util.Objects;

public class TripLegs {
    private String departure;
    private String arrival;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    public TripLegs() {
    }

    public TripLegs(
            LocalDateTime arrivalDateTime,
            LocalDateTime departureDateTime,
            String arrival,
            String departure
    ) {
        this.arrivalDateTime = arrivalDateTime;
        this.departureDateTime = departureDateTime;
        this.arrival = arrival;
        this.departure = departure;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TripLegs tripLegs = (TripLegs) o;
        return Objects.equals(departure, tripLegs.departure) && Objects.equals(arrival, tripLegs.arrival) && Objects.equals(departureDateTime, tripLegs.departureDateTime) && Objects.equals(arrivalDateTime, tripLegs.arrivalDateTime);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(departure);
        result = 31 * result + Objects.hashCode(arrival);
        result = 31 * result + Objects.hashCode(departureDateTime);
        result = 31 * result + Objects.hashCode(arrivalDateTime);
        return result;
    }

    @Override
    public String toString() {
        return "TripLegs{" +
                "departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                '}';
    }
}
