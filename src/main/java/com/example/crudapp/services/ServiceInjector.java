package com.example.crudapp.services;

import java.util.HashMap;
import java.util.Map;

public class ServiceInjector {
    private final Map<ServiceKey, Object> services = new HashMap<>();

    // Регистрация сервиса
    public <T> void provide(ServiceKey key, T service) {
        services.put(key, service);
    }

    // Получение сервиса по ключу с приведением типа
    @SuppressWarnings("unchecked")
    public <T> T injectService(ServiceKey key) {
        T service = (T) services.get(key);
        if (service == null) {
            throw new IllegalStateException("Сервис с ключом " + key + " не зарегистрирован!");
        }
        return service;
    }
}
