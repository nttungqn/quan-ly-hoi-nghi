package Controllers;

import Handlers.JoinTheConferenceHandler;
import Models.Join;
import Models.JoinTheConference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ParticipationHistoryController implements Initializable {
    @FXML
    private TableView<Join> tableView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int accountID = DashboardUserController.account.getAccountId();
        ObservableList<Join> observableList = FXCollections.observableList(JoinTheConferenceHandler.loadHistory(accountID));

        TableColumn<Join, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<Join, Integer>("id"));
        TableColumn<Join, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Join, String>("name"));
        TableColumn<Join, String> place = new TableColumn<>("Place");
        place.setCellValueFactory(new PropertyValueFactory<Join, String>("place"));
        TableColumn<Join, LocalDate> startDate = new TableColumn<>("Start date");
        startDate.setCellValueFactory(new PropertyValueFactory<Join, LocalDate>("startDate"));
        TableColumn<Join, LocalDate> endDate = new TableColumn<>("End date");
        endDate.setCellValueFactory(new PropertyValueFactory<Join, LocalDate>("endDate"));

        tableView.setItems(observableList);
        tableView.getColumns().addAll(id, name, place,startDate, endDate);
    }
}
