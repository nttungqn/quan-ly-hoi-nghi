import Handlers.*;
import Utils.HibernateAnnotationUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Parent root = FXMLLoader.load(getClass().getResource("/Views/dashboardNotLogin.fxml"));
        primaryStage.setTitle("Dashboard");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(final String[] args) throws Exception {
            launch(args);

    }
}