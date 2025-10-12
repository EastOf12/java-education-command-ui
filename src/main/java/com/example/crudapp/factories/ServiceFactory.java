package com.example.crudapp.factories;

import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Entity;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.car.Car;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.entites.user.User;
import com.example.crudapp.repositories.inmemory.InMemoryCarDAO;
import com.example.crudapp.repositories.inmemory.InMemoryPassengerDAO;
import com.example.crudapp.repositories.inmemory.InMemoryTripDAO;
import com.example.crudapp.repositories.inmemory.InMemoryUserDAO;
import com.example.crudapp.services.CarService;
import com.example.crudapp.services.PassengerService;
import com.example.crudapp.services.TripService;
import com.example.crudapp.services.UserService;

public class ServiceFactory {
    //Обобщенный метод для создания сервисов
    public static <T extends Entity> Service<T> createService(DAO<T> dao, Class<? extends Service<T>> serviceClass) {
        try {
            return serviceClass.getDeclaredConstructor(DAO.class).newInstance(dao);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать сервис: " + serviceClass.getName(), e);
        }
    }

    public static Service<User> createUserService() {
        return createService(new InMemoryUserDAO(), UserService.class);
    }

    public static Service<Trip> createTripService() {
        return createService(new InMemoryTripDAO(), TripService.class);
    }

    public static Service<Passenger> createPassengerService() {
        return createService(new InMemoryPassengerDAO(), PassengerService.class);
    }

    public static Service<Car> createCarService() {
        return createService(new InMemoryCarDAO(), CarService.class);
    }
}
