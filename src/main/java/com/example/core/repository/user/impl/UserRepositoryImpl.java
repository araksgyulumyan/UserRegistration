package com.example.core.repository.user.impl;

import com.example.core.entity.User;
import com.example.core.repository.user.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 4/11/18
 * Time - 1:16 PM
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionfactory;

    @Override
    public User saveUser(final User user) {
        return (User) sessionfactory.getCurrentSession().merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return sessionfactory.getCurrentSession()
                .createCriteria(User.class).list();
    }

    @Override
    public User getUserByEmail(String email) {
        User user = (User) sessionfactory.getCurrentSession()
                .createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
        if (user == null) {
            throw new EntityNotFoundException("User is not found");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = (User) sessionfactory.getCurrentSession()
                .createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
        if (user == null) {
            throw new EntityNotFoundException("User is not found");
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionfactory.getCurrentSession();
        User user = getUserById(id);
        if (user == null) {
            throw new RuntimeException("User is not found");
        }
        session.delete(user);
    }
}
