package Handlers;

import Models.Conference;
import Models.JoinTheConference;
import Utils.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class JoinTheConferenceHandler {
    public static List<JoinTheConference> loadList()
    {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from Models.JoinTheConference";
        Query query=session.createQuery(hql);
        List<JoinTheConference> list =query.list();
        transacsion.commit();
        return list;
    }
}
