package Controllers;

import Handlers.AccountHandler;
import Models.Account;
import Utils.AlertDialog;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField email;

    @FXML
    void cancel(ActionEvent event) {
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
    void save(ActionEvent event) {
        if(AccountHandler.loadUser(username.getText()) != null){
            AlertDialog.showAlertWithoutHeaderText("Alert", "Failed! The user exist in datatbase", "failed");
        }else{
            Account account = new Account();
            account.setName(name.getText());
            account.setUsername(username.getText());
            account.setPassword(password.getText());
            account.setEmail(email.getText());
            account.setRole("User");
            account.setStatus(1);

            if (AccountHandler.add(account)) {
                AlertDialog.showAlertWithoutHeaderText("Alert", "Successfully", "success");
            } else {
                AlertDialog.showAlertWithoutHeaderText("Alert", "Failed! Some thing went wrong", "failed");
            }
        }

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader screen = new FXMLLoader(getClass().getResource("/Views/login.fxml"));
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

}
