package com.example.api.facade.user;

import com.example.api.model.RegistrationFormModel;
import com.example.api.model.UserModel;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 5/4/18
 * Time - 9:59 PM
 */
public interface UserFacade {

    UserModel register(final RegistrationFormModel registrationModel);

    List<UserModel> getUsers();

    UserModel updateUser(final Long userId, final RegistrationFormModel registrationModel);

    void deleteUser(final Long userId);
}
