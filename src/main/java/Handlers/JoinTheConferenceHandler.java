package Handlers;

import Models.Join;
import Models.JoinTheConference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class JoinTheConferenceHandler {
    public static boolean add(JoinTheConference joinTheConference) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction =session.beginTransaction();
            session.save(joinTheConference);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    public static List<JoinTheConference> loadList()
    {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        // lenh hql
        String hql="from Models.JoinTheConference";
        Query query=session.createQuery(hql);
        List<JoinTheConference> list =query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public static List<JoinTheConference> loadUnregisterUserList(){
        {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            // lenh hql
            String hql="from Models.JoinTheConference as i where i.status=0";
            Query query=session.createQuery(hql);
            List<JoinTheConference> list =query.list();
            transaction.commit();
            session.close();
            return list;
        }
    }

    public static boolean update(JoinTheConference joinTheConference) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction =session.beginTransaction();
            session.update(joinTheConference);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
    public static boolean delete(JoinTheConference joinTheConference) {
        Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction=session.beginTransaction();
            session.delete(joinTheConference);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id) {
        Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction=session.beginTransaction();
            String hql = "delete from Models.JoinTheConference as i where i.joinId = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int count = query.executeUpdate();
            System.out.println(count + " Record(s) Deleted.");
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    public static List<Join> loadUserJoinConference(int accountID){
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        // lenh hql
        String hql="select new  Models.Join(i.id , i.conferenceByIdConfId.name,i.conferenceByIdConfId.place, i.conferenceByIdConfId.startDate, i.conferenceByIdConfId.endDate)"
                + "from Models.JoinTheConference as i where i.accountByAccountId.id = :accountID and i.conferenceByIdConfId.startDate > :localDate";
        LocalDate localDate = LocalDate.now();
        Query query=session.createQuery(hql);
        query.setParameter("accountID", accountID);
        query.setParameter("localDate", localDate);
        List<Join> result = query.list();
        transaction.commit();
        session.close();
        return result;
    }

}
