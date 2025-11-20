package com.example.crudapp.requests.user;

import com.example.crudapp.entites.user.Gender;
import com.example.crudapp.entites.user.UserAuth;
import com.example.crudapp.entites.user.UserContacts;
import com.example.crudapp.entites.user.UserRole;

import java.time.LocalDate;

public class UpdateUserRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private LocalDate birthday;
    private UserContacts userContacts;
    private UserRole userRole;
    private UserAuth userAuth;

    public UpdateUserRequest() {
        userAuth = new UserAuth();
        userContacts = new UserContacts();
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
        return "UpdateUserRequest{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", userContacts=" + userContacts +
                ", userRole=" + userRole +
                ", userAuth=" + userAuth +
                '}';
    }
}
