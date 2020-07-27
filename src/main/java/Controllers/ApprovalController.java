package Controllers;

import Handlers.JoinTheConferenceHandler;
import Models.Conference;
import Models.JoinTheConference;
import Utils.AlertDialog;
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

public class ApprovalController implements Initializable {
    @FXML
    private TableView<JoinTheConference> tableView;

    ObservableList<JoinTheConference> observableList;

    TableColumn<JoinTheConference, Integer> confId;
    TableColumn<JoinTheConference, Integer> accountId;
    TableColumn acceptColumn;
    TableColumn declineColumn;
    Callback<TableColumn<JoinTheConference, String>, TableCell<JoinTheConference, String>> acceptFactory;
    Callback<TableColumn<JoinTheConference, String>, TableCell<JoinTheConference, String>> declineFactory;


    public ApprovalController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        confId = new TableColumn<>("Conference ID");
        confId.setCellValueFactory(new PropertyValueFactory<>("confId"));

        accountId = new TableColumn<>("Account ID");
        accountId.setCellValueFactory(new PropertyValueFactory<>("accountId"));

        acceptColumn = new TableColumn("Action");
        acceptColumn.setCellValueFactory(new PropertyValueFactory<>("accept"));

        declineColumn = new TableColumn("Action");
        declineColumn.setCellValueFactory(new PropertyValueFactory<>("decline"));

        acceptFactory = new Callback<TableColumn<JoinTheConference, String>, TableCell<JoinTheConference, String>>() {
            @Override
            public TableCell call(final TableColumn<JoinTheConference, String> param) {
                return new TableCell<JoinTheConference, String>() {

                    Button btn = new Button("Accept");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                JoinTheConference joinTheConference = getTableView().getItems().get(getIndex());
                                joinTheConference.setStatus(1);
                                if(JoinTheConferenceHandler.update(joinTheConference)){
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Approval successfully", "success");
                                    refreshTable();
                                }else {
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Approval fail", "failed");
                                }
                            });
                            setGraphic(btn);
                            btn.setStyle("-fx-background-color: #00b73e;-fx-text-fill: white");
                            setText(null);
                        }
                    }

                };
            }
        };


        declineFactory = new Callback<TableColumn<JoinTheConference, String>, TableCell<JoinTheConference, String>>() {
            @Override
            public TableCell call(final TableColumn<JoinTheConference, String> param) {
                final TableCell<JoinTheConference, String> tableCell = new TableCell<JoinTheConference, String>() {

                    final Button btn = new Button("Decline");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                JoinTheConference joinTheConference = getTableView().getItems().get(getIndex());
                                if(JoinTheConferenceHandler.delete(joinTheConference)){
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Decline successfully", "success");
                                    refreshTable();
                                }else {
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Decline fail", "failed");
                                }
                            });
                            setGraphic(btn);
                            btn.setStyle("-fx-background-color: #e8003f;-fx-text-fill: white");
                            setText(null);
                        }
                    }
                };
                return tableCell;
            }
        };

        refreshTable();

    }

    public void refreshTable(){
        tableView.getItems().clear();
        tableView.getColumns().clear();

        observableList = FXCollections.observableList(JoinTheConferenceHandler.loadUnregisterUserList());

        acceptColumn.setCellFactory(acceptFactory);
        declineColumn.setCellFactory(declineFactory);
        tableView.setItems(observableList);
        tableView.getColumns().addAll(confId, accountId, acceptColumn, declineColumn);
    }
}
