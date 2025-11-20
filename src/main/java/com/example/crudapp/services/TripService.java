package com.example.crudapp.services;

import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Service;
import com.example.crudapp.entites.trip.Trip;
import com.example.crudapp.exception.NotFoundException;
import com.example.crudapp.mappers.TripMappers;
import com.example.crudapp.requests.trip.CreateTripRequest;
import com.example.crudapp.requests.trip.UpdateTripRequest;

import java.util.List;

public class TripService implements Service<Trip, CreateTripRequest, UpdateTripRequest> {
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
    public Trip save(CreateTripRequest createTripRequest) {
        Trip trip = TripMappers.mapToNewTrip(createTripRequest);

        if (tripDAO.save(trip)) {
            return trip;
        } else {
            System.out.println("Ошибка при сохранении поездки");
            return null;
        }
    }

    @Override
    public Trip update(Long id, UpdateTripRequest updateTripRequest) {
        Trip tripUpdate = getById(id);

        if (tripUpdate == null) {
            throw new NotFoundException("Поездка с ID " + id + " не найден.");
        }

        Trip trip = TripMappers.mapToUpdateTrip(tripUpdate, updateTripRequest);

        if (tripDAO.update(trip)) {
            return trip;
        } else {
            System.out.println("Ошибка при обновлении поездки");
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        return tripDAO.delete(id);
    }
}