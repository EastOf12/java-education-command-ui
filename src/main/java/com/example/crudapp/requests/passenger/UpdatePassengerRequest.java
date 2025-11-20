package com.example.crudapp.requests.passenger;

import com.example.crudapp.entites.passanger.PassengerStatus;

import java.util.Objects;

public class UpdatePassengerRequest {
    private PassengerStatus passengerStatus;
    private Integer seats;

    public UpdatePassengerRequest() {
    }

    public PassengerStatus getPassengerStatus() {
        return passengerStatus;
    }

    public void setPassengerStatus(PassengerStatus passengerStatus) {
        this.passengerStatus = passengerStatus;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "UpdatePassengerRequest{" +
                "passengerStatus=" + passengerStatus +
                ", seats=" + seats +
                '}';
    }
}
