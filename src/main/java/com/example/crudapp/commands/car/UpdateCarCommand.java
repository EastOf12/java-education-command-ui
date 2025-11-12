package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.RequestBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.car.CarBrand;
import com.example.crudapp.entites.car.CarColor;
import com.example.crudapp.requests.car.UpdateCarRequest;

import java.util.Scanner;

public class UpdateCarCommand implements Command {
    private static UpdateCarCommand instance;
    private final Service<Car, CreateCarCommand, UpdateCarRequest> carService;
    private final Scanner scanner;

    private UpdateCarCommand(Service<Car, CreateCarCommand, UpdateCarRequest> carService, Scanner scanner) {
        this.carService = carService;
        this.scanner = scanner;
    }

    public static synchronized UpdateCarCommand getInstance(Service<Car, CreateCarCommand, UpdateCarRequest> carService, Scanner scanner) {
        if (instance == null) {
            instance = new UpdateCarCommand(carService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {

        System.out.println("=== Обновление автомобиля ===");
        System.out.print("Введите ID автомобиля: ");

        try {
            Long id = Long.parseLong(scanner.nextLine());
            Car car = carService.getById(id);

            if (car != null) {

                UpdateCarRequest updateCarRequest = new RequestBuilder<>(UpdateCarRequest::new)
                        .addField("Введите новый номер автомобиля (или Enter для пропуска): ",
                                (sc, c) -> c.setNumber(sc.nextLine()))
                        .addField("Введите новое максимальное количество мест (или Enter для пропуска): ",
                                (sc, c) -> {
                            String maxSeats = sc.nextLine();
                            if(!maxSeats.isEmpty()) {
                                c.setMaxSeat(Integer.parseInt(maxSeats));
                            }
                        })
                        .addField("Введите новый цвет (или Enter для пропуска): ",
                                (sc, c) -> {
                            String color = sc.nextLine();
                            if(!color.isEmpty()) {
                                c.setColor(new CarColor(color));
                            }
                        })
                        .addField("Введите новый бренд (или Enter для пропуска): ",
                                (sc, c) -> {
                            String brand = sc.nextLine();

                            if(!brand.isEmpty()) {
                                c.setBrand(new CarBrand(brand));
                            }
                        })
                        .build(scanner);

                if (carService.update(id, updateCarRequest) != null) {
                    System.out.println("Автомобиль обновлен");
                } else {
                    System.out.println("Автомобиль не обновлен");
                }
            } else {
                System.out.println("Автомобиль не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return CarMenuCommand.getInstance().execute();
    }
}
