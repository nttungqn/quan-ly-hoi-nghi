package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class changeView {
    @FXML
    private BorderPane mainView;

    @FXML
    private void handleChangeView(ActionEvent event) {
        try {
            String menuItemID = ((MenuItem) event.getSource()).getId();
            System.out.println(menuItemID);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + menuItemID + ".fxml"));
//            loader.setController(this);

            mainView.setCenter(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
