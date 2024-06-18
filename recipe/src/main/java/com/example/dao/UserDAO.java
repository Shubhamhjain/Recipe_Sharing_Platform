/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import com.example.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 *
 * @author shubhamjain
 */
@Component
public class UserDAO extends DAO {

    public void saveUser(User user) {
        try {
            beginTransaction();
            getSession().persist(user);
            commitTransaction();
            closeSession();

        } catch (HibernateException e) {
            rollbackTransaction();

        }
    }

    public User loginByUsernamePassword(String uname, String pwd) {
        try {
            beginTransaction();
            Query q = getSession().createQuery("FROM User WHERE Username = :username", User.class);
            q.setParameter("username", uname);

            User user = (User) q.uniqueResult();
         // Check if user exists and if the provided password matches the hashed password
            if (user != null && BCrypt.checkpw(pwd, user.getPassword())) {
                commitTransaction();
                closeSession();
                return user;
            } else {
                // Invalid credentials
                rollbackTransaction();
                return null;
            }
        } catch (HibernateException e) {
            rollbackTransaction();
            return null; 
        }

    }

    public boolean isUserRegistered(String uname) {
        try {
            beginTransaction();
            Query q = getSession().createQuery("FROM User WHERE Username = :username", User.class);
            q.setParameter("username", uname);

            User user = (User) q.uniqueResult();
            commitTransaction();
            closeSession();
            return user != null;
        } catch (HibernateException e) {
            rollbackTransaction();
            return false;
        }
    }
}
