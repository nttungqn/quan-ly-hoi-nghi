import Models.DiaDiem;
import Utils.HibernateAnnotationUtil;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    private static SessionFactory sessionFactory;

    public static void main(final String[] args) throws Exception {
        Session session;
        Transaction transaction;
        // create session to connect database
        sessionFactory = HibernateAnnotationUtil.getSessionFactory();
        session = sessionFactory.openSession();
        System.out.println("Session created");
        transaction = session.beginTransaction();

        String hql;
        Query query;
        // sql

        hql = "delete from DiaDiem ";
        query = session.createQuery(hql);
        query.executeUpdate();
        // error: Transaction not successfully started
//        transaction.commit();

        DiaDiem diaDiem = new DiaDiem("Hoi truong A", "HCMUS", 150);
        session.save(diaDiem);
        hql = "from DiaDiem";
        query = session.createQuery(hql);
        List<DiaDiem> list = query.list();
        for(DiaDiem dd : list)
            System.out.println(dd.toString());
        transaction.commit();

        session.close();

    }
}