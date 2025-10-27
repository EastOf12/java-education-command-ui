package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.user.User;

import java.util.Scanner;

public class ReadUserCommand implements Command {
    private static ReadUserCommand instance;
    private final Service<User> userService;
    private final Scanner scanner;

    private ReadUserCommand(Service<User> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized ReadUserCommand getInstance(Service<User> userService, Scanner scanner) {
        if (instance == null) {
            instance = new ReadUserCommand(userService, scanner);
        }
        return instance;
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

        return UserMenuCommand.getInstance().execute();
    }
}
