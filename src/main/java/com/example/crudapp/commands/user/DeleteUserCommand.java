package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.Confirm;
import com.example.crudapp.entites.user.User;

import java.util.Scanner;

public class DeleteUserCommand implements Command {
    private static DeleteUserCommand instance;
    private final Service<User> userService;
    private final Scanner scanner;

    private DeleteUserCommand(Service<User> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized DeleteUserCommand getInstance(Service<User> userService, Scanner scanner) {
        if (instance == null) {
            instance = new DeleteUserCommand(userService, scanner);
        }
        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Удаление пользователя ===");
        System.out.print("Введите ID пользователя: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            User user = userService.getById(id);
            if (user != null) {
                System.out.println("Удаление пользователя: " + user);
                System.out.print("Подтвердите удаление (y/n): ");
                String confirm = scanner.nextLine();
                if (Confirm.valueOf(confirm).equals(Confirm.y)) {
                    userService.delete(id);
                    System.out.println("Пользователь удален");
                } else {
                    System.out.println("Удаление отменено");
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
