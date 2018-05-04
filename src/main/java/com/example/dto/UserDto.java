package com.example.dto;

import com.example.entity.User;

import java.io.Serializable;

/**
 * Created by araksgyulumyan
 * Date - 4/12/18
 * Time - 10:54 PM
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = -8779224074565145655L;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phone;

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getPhone() {
        return phone;
    }

    public UserDto setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public void updateDomainModelProperties(final User user) {
        user.setUsername(this.getUsername());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setPhone(this.getPhone());
    }
}
