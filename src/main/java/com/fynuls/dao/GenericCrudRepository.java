package com.fynuls.dao;

import com.fynuls.entity.Patient;
import com.fynuls.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericCrudRepository<T> implements CrudRepository<T> {

    private final Map<Integer, T> dataStore = new HashMap<>();
    private final Class<T> entityClass;

    public GenericCrudRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> getAll() {
        // Assuming you have a proper Hibernate mapping and setup
        // Fetch all objects of type T using Hibernate
        return HibernateUtil.getDBObjectsFromRepository( entityClass);
    }

    @Override
    public T getById(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        dataStore.remove(id);
    }

    @Override
    public void deleteAll() {
        dataStore.clear();
    }

    @Override
    public boolean save(T entity) {
        if (entity == null) {
            return false;
        }

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Assuming you have a proper Hibernate mapping and setup
            // Save the entity using Hibernate
            session.save(entity);

            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            // Handle exception or log it
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public Integer saveAndGetId(T entity) {
        if (entity == null) {
            return null;
        }

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            // Assuming you have a proper Hibernate mapping and setup
            // Save the entity using Hibernate
            Integer id = (Integer) session.save(entity);

            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            // Handle exception or log it
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
                session.close();
            }
        }
    }
}