package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.RequestBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.user.Gender;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.entites.user.UserAuth;
import com.example.crudapp.entites.user.UserContacts;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateUserCommand implements Command {
    private static CreateUserCommand instance;
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;
    private final Scanner scanner;

    private CreateUserCommand(Service<User, CreateUserRequest, UpdateUserRequest> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized CreateUserCommand getInstance(
            Service<User, CreateUserRequest, UpdateUserRequest> userService,
            Scanner scanner) {
        if (instance == null) {
            instance = new CreateUserCommand(userService, scanner);
        }

        return new CreateUserCommand(userService, scanner);
    }

    @Override
    public Command execute() {
        System.out.println("=== Создание пользователя ===");

        CreateUserRequest createUserRequest = new RequestBuilder<>(CreateUserRequest::new)
                .addField("Введите имя: ", (sc, u) -> u.setFirstName(sc.nextLine()))
                .addField("Введите фамилию: ", (sc, u) -> u.setMiddleName(sc.nextLine()))
                .addField("Введите отчество: ", (sc, u) -> u.setLastName(sc.nextLine()))
                .addField("Введите пол (Man или Woman): ", (sc, u) -> {
                    String input = sc.nextLine();
                    try {
                        u.setGender(Gender.valueOf(input));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный пол. Установлено значение по умолчанию: Man");
                        u.setGender(Gender.Man);
                    }
                })
                .addField("Введите дату рождения (dd/MM/yyyy): ", (sc, u) -> {
                    String pattern = "dd/MM/yyyy";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                    LocalDate date = null;

                    while (date == null) {
                        try {
                            date = LocalDate.parse(sc.nextLine(), formatter);
                            u.setBirthday(date);
                        } catch (DateTimeParseException e) {
                            System.out.println("Ошибка: неверный формат даты. Пожалуйста, введите дату в формате " + pattern + ".");
                        }
                    }
                })
                .addField("Введите номер телефона: ", (sc, u) -> {
                    String phone = sc.nextLine();
                    // email пока неизвестен — временно null
                    u.setUserContacts(new UserContacts(null, phone));
                })
                .addField("Введите email: ", (sc, u) -> {
                    String email = sc.nextLine();
                    UserContacts contacts = u.getUserContacts();
                    if (contacts != null) {
                        contacts.setEmail(email);
                    } else {
                        contacts = new UserContacts(email, "");
                        u.setUserContacts(contacts);
                    }
                })
                .addField("Введите пароль: ", (sc, u) -> {
                    String password = sc.nextLine();
                    u.setUserAuth(new UserAuth(password, LocalDateTime.now()));
                })
                .build(scanner);

        // Сохраняем
        User user = userService.save(createUserRequest);
        System.out.println("Пользователь создан с ID: " + user.getId());
        System.out.println(user);

        return UserMenuCommand.getInstance();
    }
}
