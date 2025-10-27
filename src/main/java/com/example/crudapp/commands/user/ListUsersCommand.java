package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.user.User;

public class ListUsersCommand implements Command {
    private static ListUsersCommand instance;
    private final Service<User> userService;

    private ListUsersCommand(Service<User> userService) {
        this.userService = userService;
    }

    public static synchronized ListUsersCommand getInstance(Service<User> userService) {
        if (instance == null) {
            instance = new ListUsersCommand(userService);
        }
        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Список пользователей ===");
        java.util.List<User> users = userService.getAll();
        if (users.isEmpty()) {
            System.out.println("Нет пользователей");
        } else {
            users.forEach(System.out::println);
        }

        return UserMenuCommand.getInstance().execute();
    }
}