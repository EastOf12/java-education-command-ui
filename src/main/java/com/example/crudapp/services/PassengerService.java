package com.example.crudapp.services;

import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.passanger.Passenger;

import java.util.List;

public class PassengerService implements Service<Passenger> {
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
    public void save(Passenger passenger) {
        passengerDAO.save(passenger);
    }

    @Override
    public void update(Passenger passenger) {
        passengerDAO.update(passenger);
    }

    @Override
    public void delete(Long id) {
        passengerDAO.delete(id);
    }
}