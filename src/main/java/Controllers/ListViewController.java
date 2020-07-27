package Controllers;

import Handlers.ConferenceHandler;
import Handlers.JoinTheConferenceHandler;
import Models.Account;
import Models.Conference;
import Models.JoinTheConference;
import Utils.AlertDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ListViewController implements Initializable {
    @FXML
    private TableView<Conference> tableView;

    @FXML
    private TextField searchConference;

    private Account account = null;

    ObservableList<Conference> conferenceObservableList = FXCollections.observableList(ConferenceHandler.loadList());


    public ListViewController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Conference, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<Conference, Integer>("confId"));
        TableColumn<Conference, String> name = new TableColumn<Conference, String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Conference, String>("name"));
        TableColumn<Conference, String> description = new TableColumn<>("Short description");
        description.setCellValueFactory(new PropertyValueFactory<Conference, String>("shortDesc"));
        TableColumn<Conference, String> startDate = new TableColumn<Conference, String>("Start date");
        startDate.setCellValueFactory(new PropertyValueFactory<Conference, String>("startDate"));
        TableColumn<Conference, String> endDate = new TableColumn<Conference, String>("End date");
        endDate.setCellValueFactory(new PropertyValueFactory<Conference, String>("endDate"));
        TableColumn<Conference, String> place = new TableColumn<Conference, String>("Place");
        place.setCellValueFactory(new PropertyValueFactory<Conference, String>("place"));
        TableColumn<Conference, Integer> participants = new TableColumn<>("Participant");
        participants.setCellValueFactory(new PropertyValueFactory<Conference, Integer>("participants"));

        TableColumn detailColumn = new TableColumn("Action");
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));

        TableColumn siginColumn = new TableColumn("Action");
        detailColumn.setCellValueFactory(new PropertyValueFactory<>("singin"));

        Callback<TableColumn<Conference, String>, TableCell<Conference, String>> detailFactory = new Callback<TableColumn<Conference, String>, TableCell<Conference, String>>() {
            @Override
            public TableCell call(final TableColumn<Conference, String> param) {
                return new TableCell<Conference, String>() {

                    Button btn = new Button("Detail");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Conference conference = getTableView().getItems().get(getIndex());
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/detailConference.fxml"));
                                    Parent parent = loader.load();

                                    DetailConferenceController detailConferenceController = loader.getController();
                                    detailConferenceController.setConference(conference);

                                    Stage stage = new Stage();
                                    stage.setTitle("Detail conference");
                                    stage.setScene(new Scene(parent));
                                    stage.show();
                                }
                                catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
            }
        };

        detailColumn.setCellFactory(detailFactory);
        tableView.setItems(conferenceObservableList);
        tableView.getColumns().addAll(id, name, startDate,endDate,place,participants,detailColumn);

        FilteredList<Conference> filteredList = new FilteredList<>(conferenceObservableList, el -> true);
        searchConference.setOnKeyReleased(el -> {
            searchConference.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super Conference>) conference -> {
                    if(newValue == null || newValue.isEmpty())
                        return true;
                    String lowerCaseFilter = newValue.toLowerCase();
                    // search for id, name, place and date
                    if(String.valueOf(conference.getConfId()).contains(newValue)) {
                        return true;
                    }else if(conference.getName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }else if(conference.getPlace().toString().contains(lowerCaseFilter)){
                        return true;
                    }else if(conference.getStartDate().toString().contains(lowerCaseFilter)){
                        return true;
                    }else if(conference.getEndDate().toString().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            }));

        });
        SortedList<Conference> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedList);

    }

    public void setAccount(Account account){
        this.account = account;
        System.out.println(this.account.toString());
    }


}
