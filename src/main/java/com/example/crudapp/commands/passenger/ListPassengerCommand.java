package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;


public class ListPassengerCommand implements Command {
    private static ListPassengerCommand instance;
    private final Service<Passenger> passengerService;

    private ListPassengerCommand(Service<Passenger> passengerService) {
        this.passengerService = passengerService;
    }

    public static synchronized ListPassengerCommand getInstance(Service<Passenger> passengerService) {
        if (instance == null) {
            instance = new ListPassengerCommand(passengerService);
        }
        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Список пассажиров ===");
        java.util.List<Passenger> passengers = passengerService.getAll();
        if (passengers.isEmpty()) {
            System.out.println("Нет пассажиров");
        } else {
            passengers.forEach(System.out::println);
        }

        return PassengerMenuCommand.getInstance().execute();
    }
}
