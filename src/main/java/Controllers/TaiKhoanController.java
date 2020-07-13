package Controllers;

import Models.Place;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TaiKhoanController {
    public boolean add(Place place) {
        try {

            Session session= HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.save(place);
            transacsion.commit();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean update(Place place) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transaction =session.beginTransaction();
            session.update(place);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }


    public boolean delete(Place place) {
        try {

            Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.delete(place);
            transacsion.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public Place load(Place maDD)
    {
        Session session=HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transaction=session.beginTransaction();
        Place bd =(Place) session.get(Place.class, maDD);
        transaction.commit();
        return bd;
    }


    public List<Place> loadList()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from Place ";
        Query query=session.createQuery(hql);
        List<Place> list =query.list();
        transacsion.commit();
        return list;
    }
}
