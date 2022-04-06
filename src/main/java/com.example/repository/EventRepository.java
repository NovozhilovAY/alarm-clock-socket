package com.example.repository;

import com.example.model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EventRepository {
    public List<Event> findAll() {
        List<Event> events = (List<Event>) HibernateSessionFactory.getSessionFactory()
                .openSession().createQuery("FROM Event").list();
        return events;
    }
    public void insert(Event event){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(event);
        transaction.commit();
        session.close();
    }
    public void delete(Event event){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(event);
        transaction.commit();
        session.close();
    }
}
