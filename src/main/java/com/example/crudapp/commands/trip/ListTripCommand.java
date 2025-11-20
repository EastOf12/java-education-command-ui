package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;

public class ListTripCommand implements Command {

    private static ListTripCommand instance;
    private final Service<Trip, CreateTripRequest, UpdateTripRequest> tripService;

    private ListTripCommand(Service<Trip, CreateTripRequest, UpdateTripRequest> tripService) {
        this.tripService = tripService;
    }

    public static synchronized ListTripCommand getInstance(Service<Trip, CreateTripRequest, UpdateTripRequest> tripService) {
        if (instance == null) {
            instance = new ListTripCommand(tripService);
        }
        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Список поездок ===");
        java.util.List<Trip> trips = tripService.getAll();
        if (trips.isEmpty()) {
            System.out.println("Нет поездок");
        } else {
            trips.forEach(System.out::println);
        }

        return TripMenuCommand.getInstance();
    }
}
