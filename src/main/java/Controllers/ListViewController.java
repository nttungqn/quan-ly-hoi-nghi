package Controllers;

import Handlers.ConferenceHandler;
import Handlers.PlaceHandler;
import Models.Conference;
import Models.Place;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ListViewController implements Initializable {
    public TableColumn buttonDetail;
    @FXML
    private TableView<Conference> tableView;

//    @FXML
//    private TableColumn<Conference, Integer> id;
//
//    @FXML
//    private TableColumn<Conference, String> name;
//
//    @FXML
//    private TableColumn<Conference, String> description;
//
//    @FXML
//    private TableColumn<Conference, String> startDate;
//
//    @FXML
//    private TableColumn<Conference, String> place;
//
//    @FXML
//    private TableColumn<Conference, Integer> participants;


    public ListViewController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Conference> conferenceObservableList = FXCollections.observableList(ConferenceHandler.loadList());


        TableColumn id = new TableColumn("First Name");
        id.setCellValueFactory(new PropertyValueFactory<Conference, Integer>("confId"));
        TableColumn name = new TableColumn("First Name");
        name.setCellValueFactory(new PropertyValueFactory<Conference, String>("name"));
        TableColumn description = new TableColumn("First Name");
        description.setCellValueFactory(new PropertyValueFactory<Conference, String>("shortDesc"));
        TableColumn startDate = new TableColumn("First Name");
        startDate.setCellValueFactory(new PropertyValueFactory<Conference, String>("startDate"));
        TableColumn place = new TableColumn("First Name");
        place.setCellValueFactory(new PropertyValueFactory<Conference, String>("place"));
        TableColumn participants = new TableColumn("First Name");
        participants.setCellValueFactory(new PropertyValueFactory<Conference, Integer>("participants"));

        TableColumn actionCol = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Conference, String>, TableCell<Conference, String>> cellFactory = new Callback<TableColumn<Conference, String>, TableCell<Conference, String>>() {
            @Override
            public TableCell call(final TableColumn<Conference, String> param) {
                final TableCell<Conference, String> cell = new TableCell<Conference, String>() {

                    final Button btn = new Button("Just Do It");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
//                                        Conference person = getTableView().getItems().get(getIndex());
//                                        System.out.println(person.getFirstName()
//                                                + "   " + person.getLastName());
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);
        System.out.println(conferenceObservableList.size());
        tableView.setItems(conferenceObservableList);
        tableView.getColumns().addAll(id, name, description,startDate,place,participants,actionCol);

    }



}
