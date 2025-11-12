package com.example.crudapp.services;


import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.exception.NotFoundException;
import com.example.crudapp.mappers.UserMapper;
import com.example.crudapp.requests.user.CreateUserRequest;
import com.example.crudapp.requests.user.UpdateUserRequest;

import java.util.List;

public class UserService implements Service<User, CreateUserRequest, UpdateUserRequest> {
    private final DAO<User> userDAO;

    public UserService(DAO<User> userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public User getById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User save(CreateUserRequest createUserRequest) {
        User user = UserMapper.mapToNewUser(createUserRequest);

        if(userDAO.save(user)) {
            return user;
        } else {
            System.out.println("Ошибка при сохранении пользователя");
            return null;
        }
    }

    @Override
    public User update(Long id,UpdateUserRequest updateUserRequest) {
        User updateUser = getById(id);

        if(updateUser == null) {
            throw new NotFoundException("Пользователь с ID " + id + " не найден.");
        }

        User user = UserMapper.mapToUpdateUser(updateUser, updateUserRequest);

        if(userDAO.update(user)) {
            return user;
        } else {
            System.out.println("Ошибка при обновлении пользователя");
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }
}