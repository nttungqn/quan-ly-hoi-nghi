package Handlers;

import Models.Account;
import Models.Conference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        return list;
    }

    public static boolean update(Account account) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction =session.beginTransaction();
            System.out.println(account.toString());
            session.update(account);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static boolean delete(Account account) {
        try {
            Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            session.delete(account);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public static Account loadUser(String username){
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            // lenh hql
            String hql="from Models.Account as i where i.username=:username";
            Query query=session.createQuery(hql);
            query.setParameter("username", username);
            List<Account> list =query.list();
            if(list.size() > 0) {
                Account account = list.get(0);
                transaction.commit();
                return account;
            }
            return null;
        }catch (HibernateException e){
            return null;
        }

    }

    public static boolean add(Account account) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            System.out.println("Success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
