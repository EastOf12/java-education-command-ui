package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.User;


import java.util.Scanner;


public class UserMenuCommand implements Command {
    private final Service<User> userService;
    private final Scanner scanner;
    private boolean running = true;

    public UserMenuCommand(Service<User> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        running = true;
        while (running) {
            showMenu();
            int choice = getMenuChoice();
            handleChoice(choice);

            if (running && choice != 0) {
                System.out.println("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Меню пользователей ===");
        System.out.println("1. Показать всех пользователей");
        System.out.println("2. Создать пользователя");
        System.out.println("3. Просмотреть пользователя");
        System.out.println("4. Обновить пользователя");
        System.out.println("5. Удалить пользователя");
        System.out.println("0. Назад в главное меню");
        System.out.print("Выберите пункт: ");
    }

    private int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                new ListUsersCommand(userService).execute();
                break;
            case 2:
                new CreateUserCommand(userService, scanner).execute();
                break;
            case 3:
                new ReadUserCommand(userService, scanner).execute();
                break;
            case 4:
                new UpdateUserCommand(userService, scanner).execute();
                break;
            case 5:
                new DeleteUserCommand(userService, scanner).execute();
                break;
            case 0:
                running = false;
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
