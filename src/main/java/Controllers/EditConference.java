package Controllers;

import Handlers.ConferenceHandler;
import Models.Conference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditConference implements Initializable {

    @FXML
    private TableView<Conference> tableView;


    public EditConference() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Conference> conferenceObservableList = FXCollections.observableList(ConferenceHandler.loadListNotYetTakenPlace());


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

        TableColumn editColumn = new TableColumn("Edit Column");
        editColumn.setCellValueFactory(new PropertyValueFactory<>("detail"));


        Callback<TableColumn<Conference, String>, TableCell<Conference, String>> editFactory = new Callback<TableColumn<Conference, String>, TableCell<Conference, String>>() {
            @Override
            public TableCell call(final TableColumn<Conference, String> param) {
                return new TableCell<Conference, String>() {

                    Button btn = new Button("Edit");

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
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/editItemConference.fxml"));
                                    Parent parent = loader.load();

                                    EditItemConference editItemConference = loader.getController();
                                    editItemConference.setConference(conference);

                                    Stage stage = new Stage();
                                    stage.setTitle("Edit conference");
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



        editColumn.setCellFactory(editFactory);
        tableView.setItems(conferenceObservableList);
        tableView.getColumns().addAll(id, name, description,startDate,endDate,place,participants,editColumn);

    }


}
