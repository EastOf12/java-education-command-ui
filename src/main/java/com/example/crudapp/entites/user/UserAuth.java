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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(LocalDateTime lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "password='" + password + '\'' +
                ", lastPasswordReset=" + lastPasswordReset +
                '}';
    }
}
