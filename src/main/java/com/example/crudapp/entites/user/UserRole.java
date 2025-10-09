package com.example.crudapp.entites.user;

import java.util.Objects;

public class UserRole {
    private String role;

    public UserRole() {
    }

    public UserRole(String role) {
        this.role = role;
    }

    //Геттеры
    public String getRole() {
        return role;
    }

    //Сеттеры
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;
        return Objects.equals(role, userRole.role);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(role);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                '}';
    }
}
