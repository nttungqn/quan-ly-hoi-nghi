package Handlers;

import Models.HoiNghi;
import Utils.HibernateAnnotationUtil;
import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.*;


import java.util.List;

public class HoiNghiHandler {
    public static boolean add(HoiNghi hoiNghi) {
        Session session = null;
        try {
            session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            session.save(hoiNghi);
            transaction.commit();
            System.out.println("Success");
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    public boolean update(HoiNghi hoiNghi) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transaction =session.beginTransaction();
            session.update(hoiNghi);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }


    public boolean delete(HoiNghi hoiNghi) {
        try {

            Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.delete(hoiNghi);
            transacsion.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public HoiNghi load(HoiNghi maHN)
    {
        Session session=HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transaction=session.beginTransaction();
        HoiNghi bd =(HoiNghi) session.get(Models.HoiNghi.class, maHN);
        transaction.commit();
        return bd;
    }


    public List<HoiNghi> loadList()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from DiaDiem ";
        Query query=session.createQuery(hql);
        List<HoiNghi > list =query.list();
        transacsion.commit();
        return list;
    }

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            sessionFactory = HibernateAnnotationUtil.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
