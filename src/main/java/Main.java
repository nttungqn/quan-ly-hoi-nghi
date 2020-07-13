import Controllers.AddConference;
import Controllers.DiaDiemController;
import Handlers.DatabaseHandler;
import Handlers.DiaDiemHandler;
import Models.DiaDiem;
import Utils.HibernateAnnotationUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application{

    static {
        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();

        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            System.out.println("Create successfully!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/mainView.fxml.fxml"));
        primaryStage.setTitle("Add conference");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(final String[] args) throws Exception {

        launch(args);

//        DiaDiem diaDiem = new DiaDiem("Hoi truong A", "HCMUS", 150);
//        Session session = null;
//        try {
//            session = HibernateAnnotationUtil.getSessionFactory().openSession();
//            Transaction transaction=session.beginTransaction();
//            session.save(diaDiem);
//            transaction.commit();
//            System.out.println("Success");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        session.close();

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