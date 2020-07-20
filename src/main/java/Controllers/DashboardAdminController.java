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

public class DashboardAdminController implements Initializable {
    @FXML
    private Text name;

    @FXML
    private BorderPane mainView;

    private Account account;

    private String typeView = "listview";

    @FXML
    void handlerDashboard(ActionEvent event) {
        changeChildView("listview");
    }

    @FXML
    void handlerAddConference(ActionEvent event) {
        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/addConference.fxml"));
            Parent parent = screen.load();
            Stage stage = new Stage();
            stage.setTitle("Add new conference");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


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
    void handlerApproval(ActionEvent actionEvent) {
        String view = ((Button) actionEvent.getSource()).getId();
//        System.out.println(view);
        changeChildView(view);
    }

    @FXML
    void handlerEditConference(ActionEvent actionEvent) {
        String view = ((Button) actionEvent.getSource()).getId();
//        System.out.println(view);
        changeChildView(view);
    }

    @FXML
    void handlerUserManagement(ActionEvent actionEvent) {
        String view = ((Button) actionEvent.getSource()).getId();
//        System.out.println(view);
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

    public void setAccount(Account account){
        this.account = account;
        this.name.setText(account.getName());
    }

}
