package com.example.crudapp.commands.user;

import com.example.crudapp.api.Service;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.user.Gender;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.entites.user.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UpdateUserCommand implements Command {
    private final Service<User> userService;
    private final Scanner scanner;

    public UpdateUserCommand(Service<User> userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("=== Обновление пользователя ===");
        System.out.print("Введите ID пользователя: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            User user = userService.getById(id);
            if (user != null) {
                System.out.println("Текущие данные: " + user);
                System.out.print("Введите новую фамилию (или Enter для пропуска): ");
                String firstName = scanner.nextLine();
                if (!firstName.isEmpty()) {
                    user.setFirstName(firstName);
                }

                System.out.print("Введите новое имя (или Enter для пропуска): ");
                String middleName = scanner.nextLine();
                if (!middleName.isEmpty()) {
                    user.setMiddleName(middleName);
                }

                System.out.print("Введите новое отчество (или Enter для пропуска): ");
                String lastName = scanner.nextLine();
                if (!lastName.isEmpty()) {
                    user.setMiddleName(lastName);
                }

                System.out.println("Введите новый пол (или Enter для пропуска): ");
                String gender = scanner.nextLine();
                if (!gender.isEmpty()) {
                    user.setGender(Gender.valueOf(gender));
                }

                System.out.println("Введите новую дату рождения (dd/MM/yyyy) (или Enter для пропуска): ");
                String birthday = scanner.nextLine();
                if (!birthday.isEmpty()) {
                    user.setBirthday(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }

                System.out.println("Введите новую роль (или Enter для пропуска): ");
                String role = scanner.nextLine();
                if (!role.isEmpty()) {
                    user.setRole(new UserRole(role));
                }

                System.out.println("Введите новый email (или Enter для пропуска): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    user.getUserContacts().setEmail(email);
                }

                System.out.println("Введите новый номер телефона (или Enter для пропуска): ");
                String phoneNumber = scanner.nextLine();
                if (!phoneNumber.isEmpty()) {
                    user.getUserContacts().setPhoneNumber(phoneNumber);
                }

                System.out.println("Введите новый пароль (или Enter для пропуска): ");
                String password = scanner.nextLine();
                if (!password.isEmpty()) {
                    user.getUserAuth().setPassword(password);
                    user.getUserAuth().setLastPasswordReset(LocalDateTime.now());
                }

                userService.update(user);
                System.out.println("Пользователь обновлен");
            } else {
                System.out.println("Пользователь не найден");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат ID");
        }
    }
}
