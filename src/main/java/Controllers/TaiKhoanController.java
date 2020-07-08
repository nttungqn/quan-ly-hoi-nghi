package Controllers;

import Models.DiaDiem;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class TaiKhoanController {
    public boolean add(DiaDiem diaDiem) {
        try {

            Session session= HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.save(diaDiem);
            transacsion.commit();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean update(DiaDiem diaDiem) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transaction =session.beginTransaction();
            session.update(diaDiem);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }


    public boolean delete(DiaDiem diaDiem) {
        try {

            Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.delete(diaDiem);
            transacsion.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public DiaDiem load(DiaDiem maDD)
    {
        Session session=HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transaction=session.beginTransaction();
        DiaDiem bd =(DiaDiem) session.get(Models.DiaDiem.class, maDD);
        transaction.commit();
        return bd;
    }


    public List<DiaDiem> loadList()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from DiaDiem ";
        Query query=session.createQuery(hql);
        List<DiaDiem > list =query.list();
        transacsion.commit();
        return list;
    }
}
