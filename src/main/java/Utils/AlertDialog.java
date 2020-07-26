package Utils;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AlertDialog {

    public static void showAlertWithoutHeaderText(String title, String contentText, String mesage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        String imgName;
        if(mesage.compareTo("success") == 0) {
            imgName = "success.png";
        }else {
            imgName = "failed.png";
        }
        alert.setGraphic(new ImageView(AlertDialog.class.getResource("/images/" + imgName).toString()));
        alert.showAndWait();
    }

    public static void showConfirmation() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Select");
        alert.setHeaderText("Do you have an account?");

        ButtonType login = new ButtonType("Log in");
        ButtonType signup = new ButtonType("Sign up");
        ButtonType cancel = new ButtonType("Cancel");

        // Loại bỏ các ButtonType mặc định
        alert.getButtonTypes().clear();

        alert.getButtonTypes().addAll(login,signup, cancel);

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == login) {
            try {
                FXMLLoader screen = new FXMLLoader(AlertDialog.class.getResource("/Views/login.fxml"));
                Parent parent = screen.load();
                Stage stage = new Stage();
                stage.setTitle("Login");
                stage.setScene(new Scene(parent));
                stage.show();

                stage.setOnCloseRequest(e -> Platform.exit());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else if(option.get() == signup){
            try {
                FXMLLoader screen = new FXMLLoader(AlertDialog.class.getResource("/Views/signup.fxml"));
                Parent parent = screen.load();
                Stage stage = new Stage();
                stage.setTitle("Sign up");
                stage.setScene(new Scene(parent));
                stage.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            alert.close();
        }


    }

}
