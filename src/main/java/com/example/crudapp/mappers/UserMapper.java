package com.example.crudapp.mappers;

import com.example.crudapp.entites.user.User;
import com.example.crudapp.entites.user.UserRole;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;

import java.time.LocalDateTime;

public class UserMapper {
    public static User mapToNewUser(CreateUserRequest createUserRequest) {
        return new User(
                createUserRequest.getFirstName(),
                createUserRequest.getMiddleName(),
                createUserRequest.getLastName(),
                createUserRequest.getGender(),
                createUserRequest.getBirthday(),
                createUserRequest.getUserContacts(),
                createUserRequest.getUserAuth()
        );
    }

    public static User mapToUpdateUser(User user, UpdateUserRequest updateUserRequest) {
        if(updateUserRequest.getFirstName() != null) {
            user.setFirstName(updateUserRequest.getFirstName());
        }

        if(updateUserRequest.getMiddleName() != null) {
            user.setMiddleName(updateUserRequest.getMiddleName());
        }

        if(updateUserRequest.getLastName() != null) {
            user.setLastName(updateUserRequest.getLastName());
        }

        if(updateUserRequest.getGender() != null) {
            user.setGender(updateUserRequest.getGender());
        }

        if(updateUserRequest.getUserContacts().getPhoneNumber() != null) {
            user.getUserContacts().setPhoneNumber(updateUserRequest.getUserContacts().getPhoneNumber());
        }

        if(updateUserRequest.getUserContacts().getEmail() != null) {
            user.getUserContacts().setEmail(updateUserRequest.getUserContacts().getEmail());
        }

        if(updateUserRequest.getUserRole() != null) {
            user.setRole(updateUserRequest.getUserRole());
        }

        if(updateUserRequest.getUserAuth().getPassword() != null) {
            user.getUserAuth().setPassword(updateUserRequest.getUserAuth().getPassword());
            user.getUserAuth().setLastPasswordReset(LocalDateTime.now());
        }

        return user;
    }
}
