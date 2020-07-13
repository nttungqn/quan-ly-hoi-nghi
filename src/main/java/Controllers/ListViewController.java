package Controllers;

import Models.Place;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListViewController implements Initializable {
    @FXML
    public TableView tableView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn id = new TableColumn("id");
        TableColumn name = new TableColumn("name");
        TableColumn description = new TableColumn("description");
        TableColumn startDate = new TableColumn("startDate");
        TableColumn place = new TableColumn("place");
        TableColumn participants = new TableColumn("participants");

        tableView.getColumns().addAll(id, name, description, startDate, place, participants);

    }
}
