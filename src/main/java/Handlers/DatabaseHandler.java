package Handlers;

import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import Utils.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DatabaseHandler {
    private static DatabaseHandler handler = null;
    private static SessionFactory sessionFactory;

    static {
        createConnection();
    }

    private static void createConnection() {
        try{
            Session session;
            Transaction transaction;
            // create session to connect database
            sessionFactory = HibernateAnnotationUtil.getSessionFactory();
            session = sessionFactory.openSession();
            System.out.println("Session created");
        }catch (Exception exception) {
            System.out.println("Cant load database " + exception);
            System.exit(0);
        }
    }

    private DatabaseHandler() {
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }


}
