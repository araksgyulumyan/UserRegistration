package com.example.service.user.impl;

import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 4/10/18
 * Time - 10:23 PM
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(final UserDto userDto) {
        assertUserDto(userDto);
        assertEmailUniqueness(userRepository.getUserByEmail(userDto.getEmail()));
        final String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = new User();
        userDto.updateDomainModelProperties(user);
        user.setPassword(encodedPassword);
        user = userRepository.saveUser(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    @Transactional
    public User updateUser(Long id, UserDto userDto) {
        assertUserDto(userDto);
        assertEmailUniqueness(userRepository.getUserByEmail(userDto.getEmail()));
        final String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = getUserById(id);
        //todo null pointer exception
        userDto.updateDomainModelProperties(user);
        user.setPassword(encodedPassword);
        user = userRepository.saveUser(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        assertId(id);
        if (userRepository.getUserById(id) == null) {
            throw new NullPointerException("User is not found");
        }
        userRepository.deleteUser(id);
    }

    @Override
    public User getUserById(Long id) {
        assertId(id);
        return userRepository.getUserById(id);
    }

    // Utility methods
    private static void assertUserDto(final UserDto userDto) {
        Assert.notNull(userDto, "User dto should not be null");
        Assert.hasText(userDto.getUsername(), "User dto username should not be null");
        Assert.hasText(userDto.getPassword(), "User dto password should not be null");
        Assert.hasText(userDto.getFirstName(), "User dto first name should not be null");
        Assert.hasText(userDto.getLastName(), "User dto last name should not be null");
        Assert.hasText(userDto.getEmail(), "User dto email should not be null");
        Assert.notNull(userDto.getPhone(), "User dto phone should not be null");
    }

    private static void assertEmailUniqueness(User user) {
        if (user != null) {
            throw new RuntimeException("Email is already taken");
        }
    }

    private static void assertId(Long id) {
        Assert.notNull(id, "Id should not be null");
    }
}
