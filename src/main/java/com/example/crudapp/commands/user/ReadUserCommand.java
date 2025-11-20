package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;

import java.util.Scanner;

public class ReadUserCommand implements Command {
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;
    private final Scanner scanner;

    private ReadUserCommand(Service<User, CreateUserRequest, UpdateUserRequest> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized ReadUserCommand getInstance(Service<User, CreateUserRequest, UpdateUserRequest> userService, Scanner scanner) {
        return new ReadUserCommand(userService, scanner);
    }

    @Override
    public Command execute() {
        System.out.println("=== Просмотр пользователя ===");
        System.out.print("Введите ID пользователя: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            User user = userService.getById(id);
            if (user != null) {
                System.out.println(user);
            } else {
                System.out.println("Пользователь не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return UserMenuCommand.getInstance();
    }
}
