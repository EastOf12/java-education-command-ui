package com.example.crudapp.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class InteractiveBuilder<T> {
    private final Supplier<T> factory;
    private final List<FieldInput<T>> fields = new ArrayList<>();

    public InteractiveBuilder(Supplier<T> factory) {
        this.factory = factory;
    }

    public InteractiveBuilder<T> addField(String prompt, BiConsumer<Scanner, T> setter) {
        fields.add(new FieldInput<>(prompt, setter));
        return this;
    }

    public T build(Scanner scanner) {
        T entity = factory.get();
        for (FieldInput<T> field : fields) {
            field.readAndSet(scanner, entity);
        }
        return entity;
    }
}
