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

public class ListViewController implements Initializable {
    @FXML
    private TableView<Conference> tableView;


    public ListViewController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Conference> conferenceObservableList = FXCollections.observableList(ConferenceHandler.loadList());


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


//        Button button = new Button("Sign in");
//        button.setStyle("-fx-background-color: -fx-success; -fx-text-fill: white;");

        Callback<TableColumn<Conference, String>, TableCell<Conference, String>> signinFactory = new Callback<TableColumn<Conference, String>, TableCell<Conference, String>>() {
            @Override
            public TableCell call(final TableColumn<Conference, String> param) {
                final TableCell<Conference, String> tableCell = new TableCell<Conference, String>() {

                    final Button btn = new Button("Sign in");


                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Conference conference = getTableView().getItems().get(getIndex());
//                                try {
//                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/detailConference.fxml"));
//                                    Parent parent = loader.load();
//
//                                    DetailConferenceController detailConferenceController = loader.getController();
//                                    detailConferenceController.setConference(conference);
//
//                                    Stage stage = new Stage();
//                                    stage.setTitle("Detail conference");
//                                    stage.setScene(new Scene(parent));
//                                    stage.show();
//                                }
//                                catch (IOException e) {
//                                    e.printStackTrace();
//                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return tableCell;
            }
        };

        detailColumn.setCellFactory(detailFactory);
        siginColumn.setCellFactory(signinFactory);
        tableView.setItems(conferenceObservableList);
        tableView.getColumns().addAll(id, name, description,startDate,endDate,place,participants,detailColumn, siginColumn);

    }



}
