package com.example.crudapp.commands.car;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.car.CarBrand;
import com.example.crudapp.entites.car.CarColor;

import java.util.Scanner;

public class UpdateCarCommand implements Command {
    private final Service<Car> carService;
    private final Scanner scanner;

    public UpdateCarCommand(Service<Car> carService, Scanner scanner) {
        this.carService = carService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("=== Обновление автомобиля ===");
        System.out.print("Введите ID автомобиля: ");

        try {
            Long id = Long.parseLong(scanner.nextLine());
            Car car = carService.getById(id);

            if (car != null) {

                System.out.print("Введите новый номер автомобиля (или Enter для пропуска): ");
                String number = scanner.nextLine();

                if (!number.isEmpty()) {
                    car.setNumber(number);
                }

                System.out.print("Введите новый максимальное количество мест (или Enter для пропуска): ");
                String maxSeats = scanner.nextLine();

                if (!maxSeats.isEmpty()) {
                    car.setMaxSeat(Integer.parseInt(maxSeats));
                }

                System.out.print("Введите новый цвет (или Enter для пропуска): ");
                String color = scanner.nextLine();

                if (!color.isEmpty()) {
                    car.setColor(new CarColor(color));
                }

                System.out.print("Введите новый бренд (или Enter для пропуска): ");
                String brand = scanner.nextLine();

                if (!brand.isEmpty()) {
                    car.setBrand(new CarBrand(brand));
                }

                carService.update(car);
                System.out.println("Автомобиль обновлен");
            } else {
                System.out.println("Автомобиль не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}
