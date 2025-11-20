package com.example.crudapp.services;

import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.exception.NotFoundException;
import com.example.crudapp.mappers.PassengerMapper;
import com.example.crudapp.requests.passenger.CreatePassengerRequest;
import com.example.crudapp.requests.passenger.UpdatePassengerRequest;

import java.util.List;

public class PassengerService implements Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> {
    private final DAO<Passenger> passengerDAO;

    public PassengerService(DAO<Passenger> tripDAO) {
        this.passengerDAO = tripDAO;
    }

    @Override
    public List<Passenger> getAll() {
        return passengerDAO.findAll();
    }

    @Override
    public Passenger getById(Long id) {
        return passengerDAO.findById(id);
    }

    @Override
    public Passenger save(CreatePassengerRequest createPassengerRequest) {
        Passenger passenger = PassengerMapper.mapToNewPassenger(createPassengerRequest);

        if (passengerDAO.save(passenger)) {
            return passenger;
        } else {
            System.out.println("Ошибка при сохранении пассажира");
            return null;
        }
    }

    @Override
    public Passenger update(Long id, UpdatePassengerRequest updatePassengerRequest) {
        Passenger passenger = getById(id);

        if (passenger == null) {
            throw new NotFoundException("Пассажир с ID " + id + " не найден.");
        }

        Passenger updatePassenger = PassengerMapper.mapToUpdateUser(passenger, updatePassengerRequest);

        if (passengerDAO.update(updatePassenger)) {
            return updatePassenger;
        } else {
            System.out.println("Ошибка при обновлении пассажира");
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        return passengerDAO.delete(id);
    }
}