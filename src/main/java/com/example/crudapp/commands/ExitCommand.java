package com.example.crudapp.commands;

import java.util.Scanner;

public class ExitCommand implements Command {

    private static ExitCommand instance;
    private final Scanner scanner;

    private ExitCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    public static synchronized ExitCommand getInstance(Scanner scanner) {
        if (instance == null) {
            instance = new ExitCommand(scanner);
        }
        return instance;
    }

    public Command execute() {
        scanner.close();
        return null;
    }
}
