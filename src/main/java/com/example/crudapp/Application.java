package com.example.crudapp;


import com.example.crudapp.api.Service;
import com.example.crudapp.commands.MainMenuCommand;
import com.example.crudapp.entites.User;
import com.example.crudapp.factories.ServiceFactory;

import java.util.Scanner;

public class Application {
    private final Scanner scanner;

    public Application() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Добро пожаловать в учебное приложение!");

        Service<User> userService = ServiceFactory.createUserService();
        MainMenuCommand mainMenu = new MainMenuCommand(userService, scanner);
        mainMenu.execute();

        scanner.close();
    }

    public static void main(String[] args) {
        new Application().run();
    }
}
