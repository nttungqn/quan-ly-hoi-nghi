package Controllers;

import Handlers.AccountHandler;
import Models.Account;
import Utils.AlertDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileController implements Initializable {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField name;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    private Account account;

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        Account account = this.account;
        account.setName(this.name.getText());
        account.setUsername(this.username.getText());
        account.setEmail(this.email.getText());
        System.out.println(account);
        if(AccountHandler.update(account)){
            AlertDialog.showAlertWithoutHeaderText("Alert", "Successfully", "success");
        }else {
            AlertDialog.showAlertWithoutHeaderText("Alert", "Failed! Some thing went wrong", "failed");
        }

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setProfile(Account account){
        this.account = account;

        this.name.setText(account.getName());
        this.username.setText(account.getUsername());
        this.email.setText(account.getEmail());
    }
}
