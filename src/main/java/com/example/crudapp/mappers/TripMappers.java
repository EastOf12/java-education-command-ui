package com.example.crudapp.mappers;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;

public class TripMappers {
    public static Trip mapToNewTrip(CreateTripRequest createTripRequest) {
        return new Trip(

        );
    }

    public static Trip mapToUpdateTrip(Trip trip, UpdateTripRequest updateTripRequest) {


        return trip;
    }
}
