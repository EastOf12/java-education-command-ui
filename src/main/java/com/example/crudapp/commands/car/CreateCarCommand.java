package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.RequestBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.car.CarBrand;
import com.example.crudapp.entites.car.CarColor;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.requests.car.CreateCarRequest;
import com.example.crudapp.requests.car.UpdateCarRequest;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;
import java.util.Scanner;

public class CreateCarCommand implements Command {
    private static CreateCarCommand instance;
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;
    private final Service<Car, CreateCarRequest, UpdateCarRequest> carService;
    private final Scanner scanner;

    private CreateCarCommand(
            Service<User,
            CreateUserRequest,
            UpdateUserRequest> userService,
            Service<Car,
            CreateCarRequest,
            UpdateCarRequest> carService,
            Scanner scanner
    ) {
        this.userService = userService;
        this.carService = carService;
        this.scanner = scanner;
    }

    public static synchronized CreateCarCommand getInstance(
            Service<User, CreateUserRequest, UpdateUserRequest> userService,
            Service<Car, CreateCarRequest, UpdateCarRequest> carService,
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

            CreateCarRequest createCarRequest = new RequestBuilder<>(CreateCarRequest::new) //Тут передаем создание конструктора
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

            Car car = carService.save(createCarRequest);
            System.out.println("Автомобиль создан с ID: " + car.getId());
            System.out.println(car);

        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID пользователя");
        }

        return CarMenuCommand.getInstance().execute();
    }
}
