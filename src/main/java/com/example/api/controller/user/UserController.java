package com.example.api.controller.user;

import com.example.api.facade.user.UserFacade;
import com.example.api.model.RegistrationFormModel;
import com.example.api.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by araksgyulumyan
 * Date - 4/10/18
 * Time - 10:17 PM
 */

@Controller
public class UserController {

    // Dependencies
    @Autowired
    private UserFacade userFacade;

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
        userFacade.register(registrationModel);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users")
    public ModelAndView getUserList() {
        final Map<String, Object> users = new HashMap<>();
        final List<UserModel> userModels = userFacade.getUsers();
        users.put("users", userModels);
        return new ModelAndView("users", "users", users);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ModelAndView updateUser(
            @Valid RegistrationFormModel registrationModel,
            BindingResult bindingResult,
            @PathVariable("id") Long userId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }
        userFacade.updateUser(userId, registrationModel);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(@PathVariable("id") Long userId) {
        userFacade.deleteUser(userId);
        return new ModelAndView("redirect:/users");
    }
}
