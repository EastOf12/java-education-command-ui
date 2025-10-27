package com.example.crudapp.services;

import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.trip.Trip;

import java.util.List;

public class TripService implements Service<Trip> {
    private final DAO<Trip> tripDAO;

    public TripService(DAO<Trip> tripDAO) {
        this.tripDAO = tripDAO;
    }

    @Override
    public List<Trip> getAll() {
        return tripDAO.findAll();
    }

    @Override
    public Trip getById(Long id) {
        return tripDAO.findById(id);
    }

    @Override
    public void save(Trip user) {
        tripDAO.save(user);
    }

    @Override
    public boolean update(Trip user) {
        return tripDAO.update(user);
    }

    @Override
    public void delete(Long id) {
        tripDAO.delete(id);
    }
}