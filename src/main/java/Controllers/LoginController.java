package Controllers;

import Handlers.AccountHandler;
import Models.Account;
import Utils.AlertDialog;
import Utils.HashCode;
import javafx.application.Platform;
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
import java.util.Base64;

public class LoginController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private Account account = null;

    public void handlerLogin(ActionEvent actionEvent) {
        this.account = AccountHandler.loadUser(username.getText());
        if (this.account == null) {
            AlertDialog.showAlertWithoutHeaderText("Alert Login", "Failed! Account does not exist","failed");
        } else {
            System.out.println(account.toString());
            byte[] salt = this.account.getSalt();
            String passwordLogin = HashCode.getSecurePassword(this.password.getText(), salt);
            System.out.println(Base64.getEncoder().encodeToString(salt));
            System.out.println(passwordLogin);
            if (passwordLogin.compareTo(this.account.getPassword()) == 0) {
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/dashboardUser.fxml"));
                Parent parent = loader.load();
                Stage stage = new Stage();
                stage.setTitle("User's Dashboard");

                DashboardUserController user = loader.getController();
                user.setAccount(account);

                stage.setScene(new Scene(parent));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
