package Handlers;

import Models.Conference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class ConferenceHandler {
    private static SessionFactory sessionFactory;

    public static boolean add(Conference conference) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction =session.beginTransaction();
            session.save(conference);
            transaction.commit();
            System.out.println("Success");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public static boolean update(Conference conference) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction =session.beginTransaction();
            session.update(conference);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }


    public boolean delete(Conference conference) {
        try {

            Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transacsion=session.beginTransaction();
            session.delete(conference);
            transacsion.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public Conference load(Conference conference)
    {
        Session session=HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Conference bd =(Conference) session.get(Conference.class, conference);
        transaction.commit();
        return bd;
    }


    public static List<Conference> loadList()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from Models.Conference ";
        Query query=session.createQuery(hql);
        List<Conference> list =query.list();
        transacsion.commit();
        return list;
    }

}
