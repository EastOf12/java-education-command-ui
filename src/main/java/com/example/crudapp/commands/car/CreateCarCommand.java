package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.car.CarBrand;
import com.example.crudapp.entites.car.CarColor;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.user.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CreateCarCommand implements Command {
    private final Service<User> userService;
    private final Service<Car> carService;
    private final Scanner scanner;

    public CreateCarCommand(Service<User> userService, Service<Car> carService, Scanner scanner) {
        this.userService = userService;
        this.carService = carService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        Passenger passenger = new Passenger();
        passenger.setCreatedAt(LocalDateTime.now());

        System.out.println("=== Создание автомобиля ===");
        System.out.print("Введите id пользователя, которому нужно добавить автомобиль: ");
        long userId = Long.parseLong(scanner.nextLine());
        User user = userService.getById(userId);

        if(user != null) {
            Car car = new Car();
            car.setUserid(userId);

            System.out.print("Введите номер автомобиля: ");
            car.setNumber(scanner.nextLine());

            System.out.print("Введите максимальное количество свободных мест в автомобиле: ");
            car.setMaxSeat(Integer.parseInt(scanner.nextLine()));

            System.out.print("Введите цвет автомобиля: ");
            car.setColor(new CarColor(scanner.nextLine()));

            System.out.print("Введите бренд автомобиля: ");
            car.setBrand(new CarBrand(scanner.nextLine()));

            carService.save(car);
            System.out.println("Автомобиль создан с ID: " + car.getId());
            System.out.println(car);
        } else {
            System.out.println("Пользователь с id " + userId + " не найден");
        }
    }
}
