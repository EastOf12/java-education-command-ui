package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.commands.MainMenuCommand;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.services.ServiceInjector;
import com.example.crudapp.services.ServiceKey;

import java.util.Scanner;


public class UserMenuCommand implements Command {
    private static UserMenuCommand instance;
    private final Service<User> userService;
    private final Scanner scanner;

    private UserMenuCommand(Service<User> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    public static synchronized UserMenuCommand getInstance() {
        if (instance == null) {
            throw new IllegalStateException("UserMenuCommand не был инициализирован");
        }

        return instance;
    }

    public static synchronized UserMenuCommand getInstance(ServiceInjector serviceInjector, Scanner scanner) {
        if (instance == null) {
            instance = new UserMenuCommand(serviceInjector.injectService(ServiceKey.USER_SERVICE), scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        showMenu();
        int choice = getMenuChoice();
        return handleChoice(choice);
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

    private Command handleChoice(int choice) {
        return switch (choice) {
            case 1 -> ListUsersCommand.getInstance(userService).execute();
            case 2 -> CreateUserCommand.getInstance(userService, scanner).execute();
            case 3 -> ReadUserCommand.getInstance(userService, scanner).execute();
            case 4 -> UpdateUserCommand.getInstance(userService, scanner).execute();
            case 5 -> DeleteUserCommand.getInstance(userService, scanner).execute();
            case 0 -> MainMenuCommand.getInstance().execute();
            default -> {
                System.out.println("Неверный выбор. Попробуйте снова.");
                yield this.execute();
            }
        };
    }
}
