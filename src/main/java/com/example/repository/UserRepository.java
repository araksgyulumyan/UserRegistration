package com.example.repository;

import com.example.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 4/10/18
 * Time - 10:28 PM
 */
@Repository
public interface UserRepository {

    User saveUser(User user);

    List<User> getUsers();

    User getUserByEmail(String email);

    User getUserById(Long id);

    void deleteUser(Long id);
}
