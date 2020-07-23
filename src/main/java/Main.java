import Controllers.CardItem;
import Handlers.*;
import Models.Account;
import Models.Conference;
import Models.JoinTheConference;
import Models.Place;
import Utils.HibernateAnnotationUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Parent root = FXMLLoader.load(getClass().getResource("/Views/dashboardNotLogin.fxml"));
        primaryStage.setTitle("Dashboard");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

//        FlowPane root = new FlowPane();
//        root.getChildren().addAll(new CardItem("1234", "1234", "2345", "345"));
//        primaryStage.setScene(new Scene(root));
//        primaryStage.show();
    }

    public static void main(final String[] args) throws Exception {
            launch(args);


//        List<Place> placeList =  PlaceHandler.loadList();
//        placeList.forEach(place -> {
//            System.out.println(place.toString());
//        });
//
//        List<JoinTheConference> joinTheConferenceList = JoinTheConferenceHandler.loadList();
//        joinTheConferenceList.forEach(joinTheConference -> {
//            System.out.println(joinTheConference.toString());
//        });
//
//         List<Conference> conferenceList = ConferenceHandler.loadList();
//         conferenceList.forEach(conference -> {
//             System.out.println(conference.toString());
//         });
//
//         List<Account> accountList = AccountHandler.loadList();
//         accountList.forEach(account -> {
//             System.out.println(account.toString());
//         });
    }
}