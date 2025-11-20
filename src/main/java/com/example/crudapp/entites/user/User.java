package com.example.crudapp.entites.user;


import com.example.crudapp.ObjectUtils;
import com.example.crudapp.api.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class User extends Entity {
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private LocalDate birthday;
    private LocalDateTime createdDate;
    private UserRole role;
    private UserContacts userContacts;
    private UserAuth userAuth;

    public User() {
    }

    public User(
            String firstName,
            String midleName,
            String lastName,
            Gender gender,
            LocalDate birthday,
            UserContacts userContacts,
            UserAuth userAuth) {
        this.firstName = firstName;
        this.middleName = midleName;
        this.gender = gender;
        this.lastName = lastName;
        this.birthday = birthday;
        this.createdDate = LocalDateTime.now();
        this.role = new UserRole("public");
        this.userContacts = userContacts;
        this.userAuth = userAuth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserContacts getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(UserContacts userContacts) {
        this.userContacts = userContacts;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    @Override
    public boolean equals(Object o) {
        return ObjectUtils.equals(this, o, "firstName", "middleName", "lastName", "gender", "birthday",
                "createdDate", "role", "userContacts", "userAuth");
    }

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(this, "firstName", "middleName", "lastName", "gender", "birthday",
                "createdDate", "role", "userContacts", "userAuth");
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", createdDate=" + createdDate +
                ", role=" + role +
                ", userContacts=" + userContacts +
                ", userAuth=" + userAuth +
                ", id=" + id +
                '}';
    }
}

