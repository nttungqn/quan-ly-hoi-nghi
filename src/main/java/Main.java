import Models.DiaDiem;
import Utils.HibernateAnnotationUtil;
import org.hibernate.*;
import org.hibernate.query.Query;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application{
    private static SessionFactory sessionFactory;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(final String[] args) throws Exception {
        launch(args);
//        Session session;
//        Transaction transaction;
//        // create session to connect database
//        sessionFactory = HibernateAnnotationUtil.getSessionFactory();
//        session = sessionFactory.openSession();
//        System.out.println("Session created");
//        transaction = session.beginTransaction();
//
//        String hql;
//        Query query;
//        // sql
//
//        hql = "delete from DiaDiem ";
//        query = session.createQuery(hql);
//        query.executeUpdate();
//        // error: Transaction not successfully started
////        transaction.commit();
//
//        DiaDiem diaDiem = new DiaDiem("Hoi truong A", "HCMUS", 150);
//        session.save(diaDiem);
//        hql = "from DiaDiem";
//        query = session.createQuery(hql);
//        List<DiaDiem> list = query.list();
//        for(DiaDiem dd : list)
//            System.out.println(dd.toString());
//        transaction.commit();
//
//        session.close();

    }
}