package com.example.crudapp.commands.passenger;

import com.example.crudapp.api.Service;
import com.example.crudapp.builder.RequestBuilder;
import com.example.crudapp.commands.Command;
import com.example.crudapp.entites.passanger.Passenger;
import com.example.crudapp.requests.passenger.CreatePassengerRequest;
import com.example.crudapp.requests.passenger.UpdatePassengerRequest;

import java.util.Scanner;

public class CreatePassengerCommand implements Command {
    private static CreatePassengerCommand instance;
    private final Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService;
    private final Scanner scanner;

    private CreatePassengerCommand(
            Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService,
            Scanner scanner) {
        this.passengerService = passengerService;
        this.scanner = scanner;
    }

    public static synchronized CreatePassengerCommand getInstance(
            Service<Passenger, CreatePassengerRequest, UpdatePassengerRequest> passengerService,
            Scanner scanner) {
        if (instance == null) {
            instance = new CreatePassengerCommand(passengerService, scanner);
        }

        return instance;
    }

    @Override
    public Command execute() {
        System.out.println("=== Создание пассажира ===");

        CreatePassengerRequest createPassengerRequest = new RequestBuilder<>(CreatePassengerRequest::new)
                .addField("Введите id пользователя, который хочет стать пассажиром: ",
                        (sc, p) -> p.setUserId(Long.valueOf(sc.nextLine())))
                .addField("Введите id поездки, к которой хочет присоединиться пассажир: ",
                        (sc, p) -> p.setTripID(Long.valueOf(sc.nextLine())))
                .addField("Укажите необходимое количество мест: ",
                        (sc, p) -> p.setSeats(Integer.parseInt(sc.nextLine())))
                .addField("Укажите сообщение для водителя: ",
                        (sc, p) -> p.setMessage(sc.nextLine()))
                .build(scanner);

        Passenger passenger = passengerService.save(createPassengerRequest);
        System.out.println("Пассажир создан с ID: " + passenger.getId());
        System.out.println(passenger);

        return PassengerMenuCommand.getInstance();
    }
}
