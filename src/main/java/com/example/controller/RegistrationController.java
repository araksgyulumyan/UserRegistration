package com.example.controller;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.model.RegistrationFormModel;
import com.example.model.UserModel;
import com.example.service.user.UserService;
import com.example.service.util.DirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by araksgyulumyan
 * Date - 4/10/18
 * Time - 10:17 PM
 */

@Controller
public class RegistrationController {

    // Dependencies
    @Autowired
    private UserService userService;

    @Autowired
    private DirectoryManager directoryManager;

    // Endpoints
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registrationModel", new RegistrationFormModel());
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView registerUser(
            @Valid RegistrationFormModel registrationModel,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        // Create user
        final User user = userService.createUser(toUserDto(registrationModel));
        // Store photo for created user
        storeUploadedPhoto(registrationModel.getPhoto(), user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users")
    public ModelAndView getUserList() {
        final Map<String, Object> users = new HashMap<>();
        List<User> usersList = userService.getUsers();
        List<UserModel> userModelList = Collections.emptyList();
        for (User user : usersList) {
            userModelList.add(toUserModel(user));
        }
        users.put("users", userModelList);
        return new ModelAndView("users", "users", users);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ModelAndView updateUser(
            @Valid RegistrationFormModel registrationModel,
            BindingResult bindingResult,
            @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        // Update user
        final User user = userService.updateUser(id, toUserDto(registrationModel));
        // Store photo for updated user
        storeUploadedPhoto(registrationModel.getPhoto(), user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable Long id) {
        // Delete user
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");
    }

    // Utility methods
    private static UserDto toUserDto(final RegistrationFormModel registrationFormModel) {
        return new UserDto()
                .setUsername(registrationFormModel.getUsername())
                .setPassword(registrationFormModel.getPassword())
                .setFirstName(registrationFormModel.getFirstName())
                .setLastName(registrationFormModel.getLastName())
                .setEmail(registrationFormModel.getEmail())
                .setPhone(registrationFormModel.getPhone());
    }

    private UserModel toUserModel(final User user) {
        return new UserModel()
                .setUsername(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setPhoto(this.directoryManager.getUserPhoto(user.getId()));
    }

    private void storeUploadedPhoto(final CommonsMultipartFile photo, final User user) {
        final File uploadDirectory = directoryManager.getUserPhotoUploadFolder(user.getId());
        final File localPhotoFile = directoryManager.createFile("profile.jpg", uploadDirectory);
        try {
            photo.transferTo(localPhotoFile);
        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }
}
