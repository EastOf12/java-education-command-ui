package com.example.crudapp.mappers;

import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;

public class TripMappers {
    public static Trip mapToNewTrip(CreateTripRequest createTripRequest) {
        return new Trip(
                createTripRequest.getCreatorId(),
                createTripRequest.getDriverId(),
                createTripRequest.getDescription(),
                createTripRequest.getSeats(),
                createTripRequest.getCost(),
                createTripRequest.getTripScheduling()
        );
    }

    public static Trip mapToUpdateTrip(Trip trip, UpdateTripRequest updateTripRequest) {
        if (updateTripRequest.getDriverId() != null) {
            trip.setDriverId(updateTripRequest.getDriverId());
        }

        if (updateTripRequest.getDescription() != null) {
            trip.setDescription(updateTripRequest.getDescription());
        }

        if (updateTripRequest.getSeats() != null) {
            trip.setSeats(updateTripRequest.getSeats());
        }

        if (updateTripRequest.getCost() != null) {
            trip.setCost(updateTripRequest.getCost());
        }

        if (updateTripRequest.getTripStatus() != null) {
            trip.setTripStatus(updateTripRequest.getTripStatus());
        }

        if (updateTripRequest.getTripScheduling().getPlanedArrivalDateTime() != null) {
            trip.getTripScheduling().setPlanedArrivalDateTime(updateTripRequest.getTripScheduling().getPlanedArrivalDateTime());
        }

        if (updateTripRequest.getTripScheduling().getPlanedDepartureDateTime() != null) {
            trip.getTripScheduling().setPlanedDepartureDateTime(updateTripRequest.getTripScheduling().getPlanedDepartureDateTime());
        }

        return trip;
    }
}
