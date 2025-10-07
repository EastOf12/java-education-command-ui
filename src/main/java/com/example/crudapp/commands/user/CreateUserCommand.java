package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.user.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateUserCommand implements Command {
    private final Service<User> userService;
    private final Scanner scanner;

    public CreateUserCommand(Service<User> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("=== Создание пользователя ===");
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Введите фамилию: ");
        String middleName = scanner.nextLine();

        System.out.print("Введите отчество: ");
        String lastName = scanner.nextLine();

        System.out.print("Введите пол (Man или Woman): ");
        Gender gender = Gender.valueOf(scanner.nextLine());

        System.out.print("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        UserContacts userContacts = new UserContacts(email, phoneNumber);

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        UserAuth userAuth = new UserAuth(password, LocalDateTime.now());

        System.out.print("Введите дату рождения (дд/мм/гггг): ");
        String input = scanner.nextLine();

        // Определение формата даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("дд/мм/гггг");
        LocalDate birthday = null;

        try {
            // Преобразование введённой строки в LocalDate
            birthday = LocalDate.parse(input, formatter);

            // Дальнейшая работа с объектом birthday, если нужно
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: неверный формат даты. Пожалуйста, введите дату в формате дд/мм/гггг.");
        }

        User user = new User(
            firstName,
                middleName,
                lastName,
                gender,
                birthday,
                LocalDateTime.now(),
                new UserRole("public"),
                userContacts,
                userAuth
                );

        userService.save(user);
        System.out.println("Пользователь создан с ID: " + user.getId());
        System.out.println(user);
    }
}
