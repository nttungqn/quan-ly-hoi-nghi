package Controllers;

import Models.JoinTheConference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ThamGiaHoiNghiController {
    public boolean add(JoinTheConference joinTheConference) {
        try {

            Session session= HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.save(joinTheConference);
            transacsion.commit();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean update(JoinTheConference joinTheConference) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transaction =session.beginTransaction();
            session.update(joinTheConference);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }


    public boolean delete(JoinTheConference joinTheConference) {
        try {

            Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.delete(joinTheConference);
            transacsion.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public JoinTheConference load(JoinTheConference joinTheConference)
    {
        Session session=HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transaction=session.beginTransaction();
        JoinTheConference bd =(JoinTheConference) session.get(JoinTheConference.class, joinTheConference);
        transaction.commit();
        return bd;
    }


    public List<JoinTheConference> loadList()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from Place ";
        Query query=session.createQuery(hql);
        List<JoinTheConference> list =query.list();
        transacsion.commit();
        return list;
    }
}
