package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.User;

public class ListUsersCommand implements Command {
    private final Service<User> userService;

    public ListUsersCommand(Service<User> userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        System.out.println("=== Список пользователей ===");
        java.util.List<User> users = userService.getAll();
        if (users.isEmpty()) {
            System.out.println("Нет пользователей");
        } else {
            users.forEach(System.out::println);
        }
    }
}