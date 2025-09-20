package com.example.crudapp.factories;

import com.example.crudapp.services.UserService;
import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.User;
import com.example.crudapp.repositories.inmemory.InMemoryUserDAO;
public class ServiceFactory {
    public static Service<User> createUserService() {
        DAO<User> userDAO = new InMemoryUserDAO();
        return new UserService(userDAO);
    }
}
