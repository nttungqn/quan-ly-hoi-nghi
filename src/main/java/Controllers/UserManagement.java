package Controllers;

import Handlers.AccountHandler;
import Handlers.ConferenceHandler;
import Models.Account;
import Models.Account;
import Utils.AlertDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;

public class UserManagement implements Initializable {
    @FXML
    private TableView<Account> tableView;

    ObservableList<Account> accountObservableList;

    TableColumn<Account, Integer> id;
    TableColumn<Account, String> name;
    TableColumn<Account, String> username;
    TableColumn<Account, String> status;
    TableColumn<Account, String> role;
    TableColumn activeCol;
    TableColumn blockCol;

    Callback<TableColumn<Account, String>, TableCell<Account, String>> activeFactory;
    Callback<TableColumn<Account, String>, TableCell<Account, String>> blockFactory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id = new TableColumn<>("Account ID");
        id.setCellValueFactory(new PropertyValueFactory<Account, Integer>("accountId"));
        name = new TableColumn<Account, String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
        username= new TableColumn<>("Email");
        username.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));
        status = new TableColumn<Account, String>("Status");
        status.setCellValueFactory(new PropertyValueFactory<Account, String>("status"));
        role = new TableColumn<Account, String>("Role");
        role.setCellValueFactory(new PropertyValueFactory<Account, String>("role"));

        activeCol = new TableColumn("Action");
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));

        blockCol = new TableColumn("Action");
        blockCol.setCellValueFactory(new PropertyValueFactory<>("block"));

        activeFactory = new Callback<TableColumn<Account, String>, TableCell<Account, String>>() {
            @Override
            public TableCell call(final TableColumn<Account, String> param) {
                return new TableCell<Account, String>() {

                    Button btn = new Button("Active");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Account account = getTableView().getItems().get(getIndex());
                                account.setStatus(1);
                                if(AccountHandler.update(account)){
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Successfully", "success");
                                    refreshTabe();
                                }else {
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Failed! Some thing went wrong", "failed");
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


        blockFactory = new Callback<TableColumn<Account, String>, TableCell<Account, String>>() {
            @Override
            public TableCell call(final TableColumn<Account, String> param) {
                final TableCell<Account, String> tableCell = new TableCell<Account, String>() {

                    final Button btn = new Button("Block");


                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Account account = getTableView().getItems().get(getIndex());
                                account.setStatus(0);
                                if(AccountHandler.update(account)){
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Successfully", "success");
                                    refreshTabe();
                                }else {
                                    AlertDialog.showAlertWithoutHeaderText("Alert", "Failed! Some thing went wrong", "failed");
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

        refreshTabe();

    }

    public void refreshTabe(){
        tableView.getItems().clear();
        tableView.getColumns().clear();
        accountObservableList = FXCollections.observableList(AccountHandler.loadListUser());

        activeCol.setCellFactory(activeFactory);
        blockCol.setCellFactory(blockFactory);
        tableView.setItems(accountObservableList);
        tableView.getColumns().addAll(id, name, username,role,status,activeCol, blockCol);
    }

}
