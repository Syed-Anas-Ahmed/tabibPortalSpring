package com.fynuls.utils;

import com.fynuls.entity.AppointmentModel;
import com.fynuls.entity.LoginStatus;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    private static SessionFactory sessionFactoryOracle = null;

    final static Logger LOG = Logger.getLogger(HibernateUtil.class);

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                sessionFactory = new Configuration().configure("hibernate_mysql.cfg.xml").buildSessionFactory();
            }catch(Exception e){
                LOG.error(e);
            }

        }
        return sessionFactory;
    }

    public static boolean saveOrUpdate(Object obj){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {
            session = getSessionFactory().openSession();
            session.flush();
            tx = session.beginTransaction();
            session.saveOrUpdate(obj);
            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return isSuccessful;
    }

    public static boolean updateRecord(Object obj){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {
            session = getSessionFactory().openSession();
            session.flush();
            tx = session.beginTransaction();
            session.merge(obj);
            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return isSuccessful;
    }

    public static Object getObjectById(Class t, int id) {
        Session session = null;
        Transaction tx =null;
        Object object = null;
        try {
            session = getSessionFactory().openSession();
            session.flush();
            tx = session.beginTransaction();

            object = session.get(t, id);
            tx.commit();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return object;
    }

    public static boolean save(Object obj){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return isSuccessful;
    }

    public static int saveAndGetIdOld(Object obj){
        Session session = null;
        Transaction tx =null;
        int id = 0;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            id = (int) session.save(obj);
            tx.commit();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return id;
    }
    public static ArrayList<Object> getDBObjectsFromSQLQuery(String query){
        Session session = null;
        ArrayList<Object> objects = new ArrayList<>();
        try {

            session = getSessionFactory()
                    .openSession();
            objects = (ArrayList<Object>) session.createSQLQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return objects;
    }

    public static boolean saveOrUpdateList(List<? extends Object> objs){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            for (Object obj : objs){

                session.saveOrUpdate(obj);

            }
            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return isSuccessful;
    }

    public static boolean saveList(List<? extends Object> objs){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            for (Object obj : objs){

                session.save(obj);

            }
            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return isSuccessful;
    }

    public static Object getDBObjects(String query){
        Session session = null;
        Object objects = null;
        try {

            session = getSessionFactory()
                    .openSession();
            session.clear();
            objects = session.createQuery(query).list();
        }catch(Exception e){
            LOG.error(e);
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return objects;
    }

    public static String generateToken(String uuid) {
        String random = String.valueOf(System.currentTimeMillis())+uuid;
        return random;
    }
    /*
    This method will return single cell on provided Query.
     */
    public static String getSingleString(String queryString){
        Session session = null;
        String result = "";
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createSQLQuery(queryString);
            List<Object> cell = (List<Object>) query.list();
            if(cell!=null && cell.size()>0 && cell.get(0)!=null){
                result = cell.get(0).toString();
            }
        }catch(Exception e){
            LOG.error(e);
        }finally{
            session.clear();session.close();
        }

        return result;
    }


    public static boolean executeQuery(String query){
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            int s= session.createSQLQuery(query).executeUpdate();

            tx.commit();
            isSuccessful = true;
        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return isSuccessful;
    }
    public static boolean deleteObject(String queryString) {
        Session session = null;
        Transaction tx =null;
        boolean isSuccessful = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery(queryString);
            int deletedRows = query.executeUpdate();


            tx.commit();
            if(deletedRows!=0){
                isSuccessful = true;
            }

        }catch(Exception e){
            LOG.error(e);
        }finally {
            session.clear();session.close();
        }
        return isSuccessful;
    }
    /*
        This method will return count number.
         */
    public static int getRecordCount(String queryString){
        Session session = null;
        String result = "";
        Integer count = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            count = ((Long)session.createQuery(queryString).uniqueResult()).intValue();

        }catch(Exception e){
            LOG.error(e);
        }finally{

            session.clear();session.close();
        }

        return (int) count;
    }
    public static <T> List<T> getDBObjectsFromRepository(Class<T> entityClass) {
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            String queryString = "FROM " + entityClass.getSimpleName();
            return session.createQuery(queryString).list();
        } catch (Exception e) {
            // Handle exception or log it
            return new ArrayList<>();
        } finally {
            if (session != null && session.isOpen()) {
                session.clear();
                session.close();
            }
        }
    }


}