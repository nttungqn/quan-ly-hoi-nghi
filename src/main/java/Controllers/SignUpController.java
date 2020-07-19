package Controllers;

import Handlers.AccountHandler;
import Models.Account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    }

    @FXML
    void save(ActionEvent event) {
        Account account = new Account();
        account.setName(name.getText());
        account.setUsername(username.getText());
        account.setPassword(password.getText());
        account.setEmail(email.getText());
        account.setRole("User");

        AccountHandler.add(account);

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
