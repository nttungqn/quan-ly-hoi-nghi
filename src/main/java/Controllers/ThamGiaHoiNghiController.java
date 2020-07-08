package Controllers;

import Models.HoiNghi;
import Models.ThamGiaHoiNghi;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ThamGiaHoiNghiController {
    public boolean add(ThamGiaHoiNghi thamGiaHoiNghi) {
        try {

            Session session= HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.save(thamGiaHoiNghi);
            transacsion.commit();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean update(ThamGiaHoiNghi thamGiaHoiNghi) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transaction =session.beginTransaction();
            session.update(thamGiaHoiNghi);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }


    public boolean delete(ThamGiaHoiNghi thamGiaHoiNghi) {
        try {

            Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.delete(thamGiaHoiNghi);
            transacsion.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public ThamGiaHoiNghi load(ThamGiaHoiNghi thamGiaHoiNghi)
    {
        Session session=HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transaction=session.beginTransaction();
        ThamGiaHoiNghi bd =(ThamGiaHoiNghi) session.get(Models.ThamGiaHoiNghi.class, thamGiaHoiNghi);
        transaction.commit();
        return bd;
    }


    public List<ThamGiaHoiNghi> loadList()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from DiaDiem ";
        Query query=session.createQuery(hql);
        List<ThamGiaHoiNghi > list =query.list();
        transacsion.commit();
        return list;
    }
}
