package Controllers;

import Models.Place;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class DiaDiemController {
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

//    public List<DiaDiem> load_danhSachCB()
//    {
//        Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
//        Transaction transacsion=session.beginTransaction();
//        // lenh hql
//        String hql="SELECT A.maDD , A.tenDD  FROM DiaDiem A";
//        Query query=session.createQuery(hql);
//        List<DiaDiem> list=query.list();
//        transacsion.commit();
//        return list;
//    }
//    public List<DiaDiem> load_danhSach_DK(String maLop, String maMonHoc)
//    {
//        Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
//        Transaction transacsion=session.beginTransaction();
//        // lenh hql
//        String hql="from DiaDiem bd  where bd.id.maLop='"+maLop+"'"
//                + "and bd.id.maMon='"+maMonHoc+"'";
//        Query query=session.createQuery(hql);
//        List<DiaDiem> list_ntt=query.list();
//        transacsion.commit();
//        return list_ntt;
//    }
//    public List<Bangdiem> loadBangDiemSV(String maSV)
//    {
//        Session session =HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction transacsion=session.beginTransaction();
//        // lenh hql
//        String hql="from Bangdiem bd  where bd.id.maSv='"+maSV+"'";
//        Query query=session.createQuery(hql);
//        List<Bangdiem> list_ntt=query.list();
//        transacsion.commit();
//        return list_ntt;
//    }
//
//    public List<String> layMaLop(){
//        Session session =HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction transacsion=session.beginTransaction();
//        // lenh hql
//        String hql="select distinct s.id.maLop from Bangdiem s";
//        Query query=session.createQuery(hql);
//        List<String> list_ntt=query.list();
//        transacsion.commit();
//        return list_ntt;
//    }
//    public List<String> layMaMonHoc(String maLop){
//        Session session =HibernateUtil.getSessionFactory().getCurrentSession();
//        Transaction transacsion=session.beginTransaction();
//        // lenh hql
//        String hql="select distinct s.id.maMon from Bangdiem s where s.id.maLop='"+maLop+"'";
//        Query query=session.createQuery(hql);
//        List<String> list_ntt=query.list();
//        transacsion.commit();
//        return list_ntt;
//    }
//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
//            // Create the SessionFactory from standard (hibernate.cfg.xml)
//            // config file.
//            sessionFactory = HibernateAnnotationUtil.buildSessionFactory();
//        } catch (Throwable ex) {
//            // Log the exception.
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }

}
