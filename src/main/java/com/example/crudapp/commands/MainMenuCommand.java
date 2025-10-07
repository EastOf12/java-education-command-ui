package com.example.crudapp.commands;


import com.example.crudapp.api.Service;
import com.example.crudapp.commands.user.UserMenuCommand;
import com.example.crudapp.entites.user.User;

import java.util.Scanner;

public class MainMenuCommand implements Command {
    private final Service<User> userService;
    private final Scanner scanner;
    private boolean running = true;

    public MainMenuCommand(Service<User> userService, Scanner scanner) {
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
        }
    }

    private void showMenu() {
        System.out.println("\n=== Главное меню ===");
        System.out.println("1. Работа с пользователями");
        System.out.println("0. Выход");
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
                new UserMenuCommand(userService, scanner).execute();
                break;
            case 0:
                running = false;
                System.out.println("До свидания!");
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
