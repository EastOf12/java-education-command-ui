package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.RequestBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.user.Gender;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.entites.user.UserRole;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UpdateUserCommand implements Command {
    private static UpdateUserCommand instance;
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;
    private final Scanner scanner;

    private UpdateUserCommand(Service<User, CreateUserRequest, UpdateUserRequest> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized UpdateUserCommand getInstance(Service<User, CreateUserRequest, UpdateUserRequest> userService, Scanner scanner) {
        if (instance == null) {
            instance = new UpdateUserCommand(userService, scanner);
        }
        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Обновление пользователя ===");
        System.out.print("Введите ID пользователя: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            User user = userService.getById(id);
            if (user != null) {
                System.out.println("Текущие данные: " + user);

                UpdateUserRequest updateUserRequest = new RequestBuilder<>(UpdateUserRequest::new)
                        .addField("Введите новое имя (или Enter для пропуска): ", (sc, c) -> c.setFirstName(sc.nextLine()))
                        .addField("Введите новую фамилию (или Enter для пропуска): ", (sc, c) -> c.setMiddleName(sc.nextLine()))
                        .addField("Введите новое отчество (или Enter для пропуска): ", (sc, c) -> c.setLastName(sc.nextLine()))
                        .addField("Введите новый пол (или Enter для пропуска): ", (sc, c) -> {
                            String gender = sc.nextLine();

                            if(!gender.isEmpty()) {
                                c.setGender(Gender.valueOf(gender));
                            }
                        })
                        .addField("Введите новую дату рождения (dd/MM/yyyy) (или Enter для пропуска): ", (sc, c) -> {
                            String birthday = scanner.nextLine();
                            if (!birthday.isEmpty()) {
                                c.setBirthday(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            }
                        })
                        .addField("Введите новую роль (или Enter для пропуска): ", (sc, c) -> {
                            String role = scanner.nextLine();
                            if (!role.isEmpty()) {
                                c.setUserRole(new UserRole(role));
                            }
                        })
                        .addField("Введите новый email (или Enter для пропуска): ", (sc, c) -> {
                            String email = scanner.nextLine();
                            if (!email.isEmpty()) {
                                c.getUserContacts().setEmail(email);
                            }
                        })
                        .addField("Введите новый номер телефона (или Enter для пропуска): ", (sc, c) -> {
                            String phoneNumber = scanner.nextLine();
                            if (!phoneNumber.isEmpty()) {
                                c.getUserContacts().setPhoneNumber(phoneNumber);
                            }
                        })
                        .addField("Введите новый пароль (или Enter для пропуска): ", (sc, c) -> {
                            String password = scanner.nextLine();
                            if (!password.isEmpty()) {
                                c.getUserAuth().setPassword(password);
                                c.getUserAuth().setLastPasswordReset(LocalDateTime.now());
                            }
                        })
                        .build(scanner);


                if(userService.update(id, updateUserRequest) != null) {
                    System.out.println("Пользователь обновлен");
                } else {
                    System.out.println("Пользователь не обновлен");
                }
            } else {
                System.out.println("Пользователь не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return UserMenuCommand.getInstance().execute();
    }
}
