package Handlers;

import Models.Account;
import Models.Conference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class AccountHandler {

    public static List<Account> loadList()
    {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        // lenh hql
        String hql="from Models.Account";
        Query query=session.createQuery(hql);
        List<Account> list =query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public static List<Account> loadListUser()
    {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        // lenh hql
        String hql="from Models.Account as i where i.role='User'";
        Query query=session.createQuery(hql);
        List<Account> list =query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public static boolean update(Account account) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction =session.beginTransaction();
            session.update(account);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            session.close();
            return false;
        }
    }

    public static boolean delete(Account account) {
        Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction=session.beginTransaction();
            session.delete(account);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            session.close();
            return false;
        }
    }

    public static Account loadUser(String username){
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        try {

            Transaction transaction=session.beginTransaction();
            // lenh hql
            String hql="from Models.Account as i where i.username=:username";
            Query query=session.createQuery(hql);
            query.setParameter("username", username);
            List<Account> list =query.list();
            if(list.size() > 0) {
                Account account = list.get(0);
                transaction.commit();
                session.close();
                return account;
            }
            session.close();
            return null;
        }catch (HibernateException e){
            session.close();
            return null;
        }

    }

    public static boolean add(Account account) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    public static Account getAccount(int id){
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Account account = session.get(Account.class, new Integer(id));
        transaction.commit();
        session.close();
        return account;
    }
}
