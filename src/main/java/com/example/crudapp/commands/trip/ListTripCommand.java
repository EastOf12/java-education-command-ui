package com.example.crudapp.commands.trip;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.trip.Trip;

public class ListTripCommand implements Command {

    private final Service<Trip> tripService;

    public ListTripCommand(Service<Trip> tripService) {
        this.tripService = tripService;
    }

    @Override
    public void execute() {
        System.out.println("=== Список поездок ===");
        java.util.List<Trip> trips = tripService.getAll();
        if (trips.isEmpty()) {
            System.out.println("Нет поездок");
        } else {
            trips.forEach(System.out::println);
        }
    }
}
