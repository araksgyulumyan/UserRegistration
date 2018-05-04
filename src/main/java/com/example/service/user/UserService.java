package com.example.service.user;

import com.example.dto.UserDto;
import com.example.entity.User;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 4/10/18
 * Time - 10:22 PM
 */
public interface UserService {

    User createUser(final UserDto userDto);

    List<User> getUsers();

    User updateUser(final Long id, final UserDto userDto);

    void deleteUser(final Long id);

    User getUserById(Long id);

}
