package Controllers;

import Handlers.AccountHandler;
import Models.Account;
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

    public void handlerLogin(ActionEvent actionEvent) {
        Account account = AccountHandler.loadUser(username.getText());
        if (account == null) {
            System.out.println("Account does not exit");
        }else {
            System.out.println(account.getPassword() + " " + password.getText());
            if(password.getText().compareTo(account.getPassword()) == 0){
                System.out.println("Loggin successfully");
            }else{
                System.out.println("Failed!");
            }
        }

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public void handlerCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
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

}
