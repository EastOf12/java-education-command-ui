package com.example.crudapp;

import java.lang.reflect.Field;
import java.util.Objects;

public class ObjectUtils {

     //Универсальный метод для equals через рефлексию
    public static boolean equals(Object obj1, Object obj2, String... fieldNames) {
        if (obj1 == obj2) return true;
        if (obj2 == null || obj1 == null) return false;
        if (obj1.getClass() != obj2.getClass()) return false;

        Class<?> clazz = obj1.getClass();

        for (String fieldName : fieldNames) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true); // чтобы получить доступ к private полям

                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);

                if (!Objects.equals(value1, value2)) {
                    return false;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Ошибка при доступе к полю: " + fieldName, e);
            }
        }

        return true;
    }

    //Универсальный метод для hashCode через рефлексию
    public static int hashCode(Object obj, String... fieldNames) {
        if (obj == null) return 0;

        Class<?> clazz = obj.getClass();
        Object[] values = new Object[fieldNames.length];

        for (int i = 0; i < fieldNames.length; i++) {
            try {
                Field field = clazz.getDeclaredField(fieldNames[i]);
                field.setAccessible(true);
                values[i] = field.get(obj);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Ошибка при доступе к полю: " + fieldNames[i], e);
            }
        }

        return Objects.hash(values);
    }
}
