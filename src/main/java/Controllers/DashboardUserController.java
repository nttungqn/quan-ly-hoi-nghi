package Controllers;

import Models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardUserController implements Initializable {

    @FXML
    private Text name;

    @FXML
    private Button logout;

    @FXML
    private Button dashboard;

    @FXML
    private Button cardview;

    @FXML
    private Text contentName;

    @FXML
    private Button listview;

    @FXML
    private BorderPane mainView;

    private String typeView = "listview";

    public static Account account = null;

    @FXML
    void handlerDashboard(ActionEvent event)
    {
        contentName.setText("Conference List");
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


    @FXML
    void handlerProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/profile.fxml"));
            Parent parent = loader.load();

            EditProfileController editProfileController = loader.getController();
            editProfileController.setProfile(this.account);

            Stage stage = new Stage();
            stage.setTitle("Profile");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handlerLogout(ActionEvent event) {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/dashboardNotLogin.fxml"));
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


    public void changeChildView(String view){
        try {
            typeView = view;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + view + ".fxml"));
            mainView.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAccount(Account account){
        this.account = account;
        this.name.setText(account.getName());
    }

    @FXML
    void handlerHistory(ActionEvent event) {
        contentName.setText("History");

    }

    @FXML
    void handlerJoin(ActionEvent event) {
        contentName.setText("Registeration List");
        changeChildView("registerationList");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}