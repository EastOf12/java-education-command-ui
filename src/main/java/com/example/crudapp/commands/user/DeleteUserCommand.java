package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.Confirm;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;

import java.util.Scanner;

public class DeleteUserCommand implements Command {
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;
    private final Scanner scanner;

    private DeleteUserCommand(Service<User, CreateUserRequest, UpdateUserRequest> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized DeleteUserCommand getInstance(Service<User, CreateUserRequest, UpdateUserRequest> userService, Scanner scanner) {
        return new DeleteUserCommand(userService, scanner);
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
                    if(userService.delete(id)) {
                        System.out.println("Пользователь удален");
                    } else {
                        System.out.println("Ошибка при удалении пользователя");
                    }
                } else {
                    System.out.println("Удаление отменено");
                }
            } else {
                System.out.println("Пользователь не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }

        return UserMenuCommand.getInstance();
    }
}
