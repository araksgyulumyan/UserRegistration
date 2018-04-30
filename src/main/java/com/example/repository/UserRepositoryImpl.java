package com.example.repository;

import com.example.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by araksgyulumyan
 * Date - 4/11/18
 * Time - 1:16 PM
 */
//todo multiple usage of annotation
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionfactory;

    //todo research how to save data to db except this method of saving
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
        return (User) sessionfactory.getCurrentSession()
                .createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
    }
}
