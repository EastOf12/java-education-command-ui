package com.example.crudapp.entites.user;

import java.time.LocalDateTime;
import java.util.Objects;


public class UserAuth {
    private String password;
    private LocalDateTime lastPasswordReset;

    public UserAuth() {
    }

    public UserAuth(
            String password,
            LocalDateTime lastPasswordReset
    ) {
        this.password = password;
        this.lastPasswordReset = lastPasswordReset;
    }

    //Геттеры
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastPasswordReset() {
        return lastPasswordReset;
    }

    //Сеттеры
    public void setLastPasswordReset(LocalDateTime lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuth userAuth = (UserAuth) o;
        return Objects.equals(password, userAuth.password) && Objects.equals(lastPasswordReset, userAuth.lastPasswordReset);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(lastPasswordReset);
        return result;
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "password='" + password + '\'' +
                ", lastPasswordReset=" + lastPasswordReset +
                '}';
    }
}
