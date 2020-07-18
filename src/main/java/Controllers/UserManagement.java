package Controllers;

import Handlers.AccountHandler;
import Handlers.ConferenceHandler;
import Models.Account;
import Models.Account;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Account> accountObservableList = FXCollections.observableList(AccountHandler.loadListUser());


        TableColumn<Account, Integer> id = new TableColumn<>("Account ID");
        id.setCellValueFactory(new PropertyValueFactory<Account, Integer>("accountId"));
        TableColumn<Account, String> name = new TableColumn<Account, String>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
        TableColumn<Account, String> username= new TableColumn<>("Username");
        username.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        TableColumn<Account, String> status = new TableColumn<Account, String>("Status");
        status.setCellValueFactory(new PropertyValueFactory<Account, String>("status"));
        TableColumn<Account, String> role = new TableColumn<Account, String>("Role");
        role.setCellValueFactory(new PropertyValueFactory<Account, String>("role"));


        TableColumn activeCol = new TableColumn("Action");
        activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));

        TableColumn blockCol = new TableColumn("Action");
        blockCol.setCellValueFactory(new PropertyValueFactory<>("block"));

        Callback<TableColumn<Account, String>, TableCell<Account, String>> activeFactory = new Callback<TableColumn<Account, String>, TableCell<Account, String>>() {
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
                                System.out.println(AccountHandler.update(account));

                            });
                            setGraphic(btn);
                            btn.setStyle("-fx-background-color: #00b73e;-fx-text-fill: white");
                            setText(null);
                        }
                    }
                };
            }
        };


        Callback<TableColumn<Account, String>, TableCell<Account, String>> blockFactory = new Callback<TableColumn<Account, String>, TableCell<Account, String>>() {
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
                                System.out.println(AccountHandler.update(account));

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

        activeCol.setCellFactory(activeFactory);
        blockCol.setCellFactory(blockFactory);
        tableView.setItems(accountObservableList);
        tableView.getColumns().addAll(id, name, username,role,status,activeCol, blockCol);

    }

}
