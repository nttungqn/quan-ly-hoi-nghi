package Controllers;

import Handlers.PlaceHandler;
import Handlers.ConferenceHandler;
import Models.Place;
import Models.Conference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddConference implements Initializable {
    @FXML
    public TextField topic;
    @FXML
    public TextField countParticipant;
    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker endDate;
    @FXML
    public TextField brefDes;
    @FXML
    public ChoiceBox menuDiaDiem;
    @FXML
    public TextArea detailDes;
    @FXML
    public Button saveBtn;
    @FXML
    public Button cancelBtn;
    public AnchorPane rootPane;

    ObservableList<Place> observableList = FXCollections.observableList(PlaceHandler.loadList());

    public AddConference() {
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        Conference conference = new Conference();
        conference.setName(topic.getText());
        conference.setPlace(PlaceHandler.getDiaDiem(1));
        conference.setStartDate(LocalDate.parse(startDate.getValue().toString()));
        conference.setEndDate(LocalDate.parse(endDate.getValue().toString()));
        conference.setDetailDesc(detailDes.getText());
        conference.setShortDesc(brefDes.getText());
        conference.setPlace((Place) menuDiaDiem.getValue());
        conference.setParticipants(Integer.parseInt(countParticipant.getText()));
        System.out.println(conference.toString());
        boolean result = ConferenceHandler.add(conference);
        if(result){
            System.out.println("Add conference successfully!!");
        } else {
            System.out.println("Failed");
        }

        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuDiaDiem.setItems(observableList);
        menuDiaDiem.setValue(observableList.get(0));
    }




}
