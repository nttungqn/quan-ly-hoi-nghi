package Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

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

}
