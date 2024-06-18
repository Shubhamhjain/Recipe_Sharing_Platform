/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.example.recipe.HibernateBean;

/**
 *
 * @author shubhamjain
 */

@Repository
public abstract class DAO {
    private static final SessionFactory sf = HibernateBean.getSessionFactory();
    private static final ThreadLocal sessionThread = new ThreadLocal();
    private static final Logger log = Logger.getAnonymousLogger();

protected DAO(){
    }

    protected Session getSession() {
        Session session = (Session) DAO.sessionThread.get();

        if (session == null) {
            session = sf.openSession();
            DAO.sessionThread.set(session);
        }
        return session;
    }

    public void beginTransaction() {
        getSession().beginTransaction();
    }

    public void commitTransaction() {
        getSession().getTransaction().commit();
    }

    public void rollbackTransaction() {
try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        DAO.sessionThread.set(null);    }

    public void closeSession() {
        getSession().close();
        DAO.sessionThread.set(null);

    }

}
