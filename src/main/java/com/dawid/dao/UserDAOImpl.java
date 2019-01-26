package com.dawid.dao;

import com.dawid.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByUserName(String theUserName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        // now retrieve/read from database using username
        Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
        theQuery.setParameter("uName", theUserName);
        User theUser = null;
        try {
            theUser = theQuery.getSingleResult();
        } catch (Exception e) {
            theUser = null;
        }
        return theUser;
    }

    @Override
    public void save(User theUser) {
        // get current hibernate session
        System.out.println("getting session");
        Session currentSession = sessionFactory.getCurrentSession();
        // create the user ... finally LOL

        currentSession.saveOrUpdate(theUser);
    }

}
