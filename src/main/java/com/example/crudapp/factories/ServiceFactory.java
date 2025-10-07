package com.example.crudapp.factories;

import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.repositories.inmemory.InMemoryPassengerDAO;
import com.example.crudapp.repositories.inmemory.InMemoryTripDAO;
import com.example.crudapp.services.PassengerService;
import com.example.crudapp.services.TripService;
import com.example.crudapp.services.UserService;
import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.repositories.inmemory.InMemoryUserDAO;
public class ServiceFactory {
    public static Service<User> createUserService() {
        DAO<User> userDAO = new InMemoryUserDAO();
        return new UserService(userDAO);
    }

    public static Service<Trip> createTripService() {
        DAO<Trip> tripDAO = new InMemoryTripDAO();
        return new TripService(tripDAO);
    }

    public static Service<Passenger> createPassengerService() {
        DAO<Passenger> passengerDAO = new InMemoryPassengerDAO();
        return new PassengerService(passengerDAO);
    }
}
