package com.example.crudapp.requests.user;

import com.example.crudapp.entites.user.Gender;
import com.example.crudapp.entites.user.UserAuth;
import com.example.crudapp.entites.user.UserContacts;

import java.time.LocalDate;
import java.util.Objects;

public class CreateUserRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private LocalDate birthday;
    private UserContacts userContacts;
    private UserAuth userAuth;

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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
    public String toString() {
        return "CreateUserRequest{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", userContacts=" + userContacts +
                ", userAuth=" + userAuth +
                '}';
    }
}
