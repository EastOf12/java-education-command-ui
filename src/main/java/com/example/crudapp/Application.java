package com.example.crudapp;


import com.example.crudapp.api.Service;
import com.example.crudapp.commands.ConsoleMenu;
import com.example.crudapp.commands.MainMenuCommand;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.factories.ServiceFactory;
import com.example.crudapp.services.ServiceInjector;
import com.example.crudapp.services.ServiceKey;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    public void run() {
        System.out.println("Добро пожаловать в учебное приложение!");

        ServiceInjector injector = new ServiceInjector();

        // Создаём и регистрируем сервисы
        Service<User> userService = ServiceFactory.createUserService();
        Service<Trip> tripService = ServiceFactory.createTripService();
        Service<Passenger> passengerService = ServiceFactory.createPassengerService();
        Service<Car> carService = ServiceFactory.createCarService();

        injector.provide(ServiceKey.USER_SERVICE, userService);
        injector.provide(ServiceKey.TRIP_SERVICE, tripService);
        injector.provide(ServiceKey.PASSENGER_SERVICE, passengerService);
        injector.provide(ServiceKey.CAR_SERVICE, carService);

        // Запускаем консольное меню
        ConsoleMenu menu = new ConsoleMenu(MainMenuCommand.getInstance(injector, new Scanner(System.in)));
        menu.run();
    }
}
