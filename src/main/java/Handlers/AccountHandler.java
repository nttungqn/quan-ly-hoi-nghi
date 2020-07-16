package Handlers;

import Models.Account;
import Models.JoinTheConference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AccountHandler {
    public static List<Account> loadList()
    {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from Models.Account";
        Query query=session.createQuery(hql);
        List<Account> list =query.list();
        transacsion.commit();
        return list;
    }
}
