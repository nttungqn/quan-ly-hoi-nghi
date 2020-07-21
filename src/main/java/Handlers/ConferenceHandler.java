package Handlers;

import Models.Conference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ConferenceHandler {
    private static SessionFactory sessionFactory;

    public static boolean add(Conference conference) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction =session.beginTransaction();
            session.save(conference);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return false;
        }
    }


    public static boolean update(Conference conference) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction =session.beginTransaction();
            session.update(conference);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            session.close();
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(Conference conference) {
        Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transacsion=session.beginTransaction();
            session.delete(conference);
            transacsion.commit();
            return true;
        } catch (HibernateException e) {
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    public Conference load(Conference conference)
    {
        Session session=HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Conference bd =(Conference) session.get(Conference.class, conference);
        transaction.commit();
        session.close();
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
        session.close();
        return list;
    }

    public static List<Conference> loadListNotYetTakenPlace()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        LocalDate localDate = LocalDate.now();
        String hql="from Models.Conference as i where i.startDate > :localDate";
        Query query=session.createQuery(hql);
        query.setParameter("localDate", localDate);
        List<Conference> list =query.list();
        transacsion.commit();
        session.close();
        return list;
    }

}
