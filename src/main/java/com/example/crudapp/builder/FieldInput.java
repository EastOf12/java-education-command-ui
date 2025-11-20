package com.example.crudapp.builder;

import java.util.Scanner;
import java.util.function.BiConsumer;

public class FieldInput<T> {
    private final String prompt;
    private final BiConsumer<Scanner, T> setter;

    public FieldInput(String prompt, BiConsumer<Scanner, T> setter) {
        this.prompt = prompt;
        this.setter = setter;
    }

    public void readAndSet(Scanner scanner, T entity) {
        System.out.print(prompt);
        setter.accept(scanner, entity);
    }
}
