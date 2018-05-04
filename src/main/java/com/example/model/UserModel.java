package com.example.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by araksgyulumyan
 * Date - 5/4/18
 * Time - 3:25 PM
 */
public class UserModel implements Serializable {
    private static final long serialVersionUID = -7614239450017410436L;

    @NotNull
    private String username;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private Integer phone;

    @NotNull
    private String photo;

    public String getUsername() {
        return username;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getPhone() {
        return phone;
    }

    public UserModel setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public UserModel setPhoto(final String photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("username", username)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("phone", phone)
                .append("photo", photo)
                .toString();
    }
}
