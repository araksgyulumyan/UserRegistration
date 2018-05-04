package com.example.api.facade.user.impl;

import com.example.api.facade.user.UserFacade;
import com.example.api.model.RegistrationFormModel;
import com.example.api.model.UserModel;
import com.example.core.dto.UserDto;
import com.example.core.entity.User;
import com.example.core.service.user.UserService;
import com.example.core.service.util.DirectoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by araksgyulumyan
 * Date - 5/4/18
 * Time - 9:59 PM
 */
@Component
public class UserFacadeImpl implements UserFacade {

    // Dependencies
    private UserService userService;

    private DirectoryManager directoryManager;

    // Constructors
    @Autowired
    public UserFacadeImpl(final UserService userService,
                          final DirectoryManager directoryManager) {
        this.userService = userService;
        this.directoryManager = directoryManager;
    }

    // Interface public methods overrides
    @Override
    public UserModel register(final RegistrationFormModel registrationModel) {
        // Create user
        final User user = userService.createUser(toUserDto(registrationModel));
        // Store photo for created user
        storeUploadedPhoto(registrationModel.getPhoto(), user);
        return convertToUserModel(user);
    }

    @Override
    public List<UserModel> getUsers() {
//        final List<User> users = userService.getUsers();
//        final List<UserModel> userModels = new ArrayList<>();
//        for (final User user : users) {
//            userModels.add(convertToUserModel(user));
//        }
        return userService.getUsers()
                .stream().map(this::convertToUserModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel updateUser(final Long userId, final RegistrationFormModel registrationModel) {
        final User user = userService.updateUser(userId, toUserDto(registrationModel));
        return convertToUserModel(user);
    }

    @Override
    public void deleteUser(final Long userId) {
        userService.deleteUser(userId);
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

    private UserModel convertToUserModel(User user) {
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
