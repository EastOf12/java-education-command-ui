package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.User;

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
        String name = scanner.nextLine();
        System.out.print("Введите email: ");
        String email = scanner.nextLine();

        User user = new User(name, email);
        userService.save(user);
        System.out.println("Пользователь создан с ID: " + user.getId());
    }
}
