package com.example.crudapp;


import com.example.crudapp.api.Service;
import com.example.crudapp.commands.MainMenuCommand;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.factories.ServiceFactory;

import java.util.Scanner;

public class Application {
    private final Scanner scanner;

    public Application() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        System.out.println("Добро пожаловать в учебное приложение!");

        Service<User> userService = ServiceFactory.createUserService();
        Service<Trip> tripService = ServiceFactory.createTripService();
        Service<Passenger> passengerService = ServiceFactory.createPassengerService();
        Service<Car> carService = ServiceFactory.createCarService();
        MainMenuCommand mainMenu = new MainMenuCommand(userService, tripService, passengerService, carService, scanner);
        mainMenu.execute();

        scanner.close();
    }
}
