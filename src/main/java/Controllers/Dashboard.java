package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Dashboard {
    @FXML
    private BorderPane mainView;

    @FXML
    public void handleCardView(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/cardview.fxml"));
//            loader.setController(this);

            mainView.setCenter(loader.load());

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleListView(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/listview.fxml"));
//            loader.setController(this);

            mainView.setCenter(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
