package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.InteractiveBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.car.CarBrand;
import com.example.crudapp.entites.car.CarColor;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.user.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CreateCarCommand implements Command {
    private static CreateCarCommand instance;
    private final Service<User> userService;
    private final Service<Car> carService;
    private final Scanner scanner;

    private CreateCarCommand(Service<User> userService, Service<Car> carService, Scanner scanner) {
        this.userService = userService;
        this.carService = carService;
        this.scanner = scanner;
    }

    public static synchronized CreateCarCommand getInstance(
            Service<User> userService,
            Service<Car> carService,
            Scanner scanner) {
        if (instance == null) {
            instance = new CreateCarCommand(userService, carService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Создание автомобиля ===");
        System.out.print("Введите id пользователя, которому нужно добавить автомобиль: ");

        try {
            long userId = Long.parseLong(scanner.nextLine());
            User user = userService.getById(userId);

            if (user == null) {
                System.out.println("Пользователь с id " + userId + " не найден");
                return CarMenuCommand.getInstance().execute();
            }

            Car car = new InteractiveBuilder<>(Car::new)
                    .addField("Введите номер автомобиля: ", (sc, c) -> c.setNumber(sc.nextLine()))
                    .addField("Введите максимальное количество свободных мест в автомобиле: ", (sc, c) -> {
                        try {
                            c.setMaxSeat(Integer.parseInt(sc.nextLine()));
                        } catch (NumberFormatException e) {
                            System.out.println("Неверный формат числа, установлено 0");
                            c.setMaxSeat(0);
                        }
                    })
                    .addField("Введите цвет автомобиля: ", (sc, c) -> c.setColor(new CarColor(sc.nextLine())))
                    .addField("Введите бренд автомобиля: ", (sc, c) -> c.setBrand(new CarBrand(sc.nextLine())))
                    .build(scanner);

            car.setUserid(userId);

            carService.save(car);
            System.out.println("Автомобиль создан с ID: " + car.getId());
            System.out.println(car);

        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID пользователя");
        }

        return CarMenuCommand.getInstance().execute();
    }
}
