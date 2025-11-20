package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;

public class ListUsersCommand implements Command {
    private final Service<User, CreateUserRequest, UpdateUserRequest> userService;

    private ListUsersCommand(Service<User, CreateUserRequest, UpdateUserRequest> userService) {
        this.userService = userService;
    }

    public static synchronized ListUsersCommand getInstance(Service<User, CreateUserRequest, UpdateUserRequest> userService) {
        return new ListUsersCommand(userService);
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

        return UserMenuCommand.getInstance();
    }
}