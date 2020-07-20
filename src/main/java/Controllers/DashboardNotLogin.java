package Controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardNotLogin implements Initializable {
    @FXML
    private BorderPane mainView;

    @FXML
    private Button login;

    @FXML
    private Button signup;

    private String typeView = "listview";

    private boolean isLogin = false;


    @FXML
    void handlerLogin(ActionEvent event) {
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/login.fxml"));
            Parent parent = screen.load();
            stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handlerSignUp(ActionEvent event) {
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/signup.fxml"));
            Parent parent = screen.load();
            stage = new Stage();
            stage.setTitle("Sign up");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void handlerDashboard(ActionEvent event) {
        changeChildView("listview");
    }


    @FXML
    void handlerRefreshView(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + typeView + ".fxml"));
            mainView.setCenter(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleChangeView(ActionEvent actionEvent) {
        String view = ((Button) actionEvent.getSource()).getId();
        changeChildView(view);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void changeChildView(String view){
        try {
            typeView = view;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + view + ".fxml"));
            mainView.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
