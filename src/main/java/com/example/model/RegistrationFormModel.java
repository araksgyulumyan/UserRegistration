package com.example.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by araksgyulumyan
 * Date - 4/12/18
 * Time - 3:49 PM
 */
public class RegistrationFormModel implements Serializable {

    private static final long serialVersionUID = -5333261064318291093L;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private Integer phone;

    @NotNull
    private CommonsMultipartFile photo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public CommonsMultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(final CommonsMultipartFile photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("username", username)
                .append("password", password)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("email", email)
                .append("phone", phone)
                .append("photo", photo)
                .toString();
    }
}
