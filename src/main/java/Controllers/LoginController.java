package Controllers;

import Handlers.AccountHandler;
import Models.Account;
import Utils.AlertDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private Account account = null;

    public void handlerLogin(ActionEvent actionEvent) {
        account = AccountHandler.loadUser(username.getText());
        if (account == null) {
            AlertDialog.showAlertWithoutHeaderText("Alert Login", "Failed! Account does not exist","failed");
        } else {
            if (password.getText().compareTo(account.getPassword()) == 0) {
                Stage stage = (Stage) rootPane.getScene().getWindow();
                stage.close();
                forwardToView(account);
            } else {
                AlertDialog.showAlertWithoutHeaderText("Alert Login", "Failed! Incorrect password","failed");
            }
        }
    }

    public void handlerCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();

        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/dashboardNotLogin.fxml"));
            Parent parent = screen.load();
            stage = new Stage();
            stage.setTitle("Dash board");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handlerSignUp(ActionEvent event) {
        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/signup.fxml"));
            Parent parent = screen.load();
            Stage stage = new Stage();
            stage.setTitle("Sign up");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forwardToView(Account account){
        if(String.valueOf(account.getRole()).compareTo("Admin") == 0){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/dashboardAdmin.fxml"));
                Parent parent = loader.load();

                DashboardAdminController admin = loader.getController();
                admin.setAccount(account);

                Stage stage = new Stage();
                stage.setTitle("Admin's Dashboard ");
                stage.setScene(new Scene(parent));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/signup.fxml"));
                Parent parent = screen.load();
                Stage stage = new Stage();
                stage.setTitle("User's Dashboard");
                stage.setScene(new Scene(parent));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
