package com.example.crudapp.mappers;

import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.requests.passenger.CreatePassengerRequest;
import com.example.crudapp.requests.passenger.UpdatePassengerRequest;

public class PassengerMapper {
    public static Passenger mapToNewPassenger(CreatePassengerRequest createPassengerRequest) {
        return new Passenger(
                createPassengerRequest.getUserId(),
                createPassengerRequest.getTripID(),
                createPassengerRequest.getSeats(),
                createPassengerRequest.getMessage()
        );
    }

    public static Passenger mapToUpdateUser(Passenger passenger, UpdatePassengerRequest updatePassengerRequest) {

        if (updatePassengerRequest.getPassengerStatus() != null) {
            passenger.setPassengerStatus(updatePassengerRequest.getPassengerStatus());
        }

        if (updatePassengerRequest.getSeats() != null) {
            passenger.setSeats(updatePassengerRequest.getSeats());
        }

        return passenger;
    }
}
