package com.example.crudapp.factories;

import com.example.crudapp.api.DAO;
import com.example.crudapp.api.Entity;
import com.example.crudapp.api.Service;

public class ServiceFactory {
    //Обобщенный метод для создания сервисов
    public static <T extends Entity, C, U> Service<T, C, U> createService(
            DAO<T> dao,
            Class<? extends Service<T, C, U>> serviceClass
    ) {
        try {
            return serviceClass.getDeclaredConstructor(DAO.class).newInstance(dao);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать сервис: " + serviceClass.getName(), e);
        }
    }
}
