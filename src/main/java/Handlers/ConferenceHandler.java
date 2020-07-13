package Handlers;

import Models.Conference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class ConferenceHandler {
    public static boolean add(Conference conference) {
        Session session = null;
        try {
            session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            session.save(conference);
            transaction.commit();
            System.out.println("Success");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public boolean update(Conference conference) {
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


    public List<Conference> loadList()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from Place ";
        Query query=session.createQuery(hql);
        List<Conference> list =query.list();
        transacsion.commit();
        return list;
    }

}
