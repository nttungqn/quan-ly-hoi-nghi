package Controllers;

import Handlers.PlaceHandler;
import Handlers.ConferenceHandler;
import Models.Place;
import Models.Conference;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddConference implements Initializable{
    @FXML
    public TextField topic;

    @FXML
    private ImageView imageConference;

    @FXML
    public TextField countParticipant;
    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker endDate;
    @FXML
    public TextField brefDes;
    @FXML
    public ChoiceBox menuPlace;
    @FXML
    public TextArea detailDes;
    @FXML
    public Button saveBtn;
    @FXML
    public Button cancelBtn;
    public AnchorPane rootPane;

    ObservableList<Place> observableList = FXCollections.observableList(PlaceHandler.loadList());

    private String pathToImage;

    private File file;

    public AddConference() {
    }


    @FXML
    void handlerChooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        stage.setTitle("File choose image");
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            // openFile(file);

            Image image = new Image(file.toURI().toString());
            imageConference.setImage(image);
//            file:/home/nttung/Pictures/ly-ca-phe-thien-chi.png
            this.pathToImage = file.toPath().toString();
//            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
//            BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        }
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        Conference conference = new Conference();
        conference.setName(topic.getText());
        conference.setStartDate(LocalDate.parse(startDate.getValue().toString()));
        conference.setEndDate(LocalDate.parse(endDate.getValue().toString()));
        conference.setDetailDesc(detailDes.getText());
        conference.setShortDesc(brefDes.getText());
        conference.setPlace((Place) menuPlace.getValue());
        conference.setParticipants(Integer.parseInt(countParticipant.getText()));


        // copy image to dir "/resource/images/.."
        String currentDir = System.getProperty("user.dir");

        String[] arr = file.toPath().toString().split("/");
        String imgName = arr[arr.length - 1];
//        System.out.println(imgName);
        File dest = new File(currentDir + "/src/main/resources/images/" + imgName);

        synchronized (this) {
            try {
                Files.copy(file.toPath(), dest.toPath());
            } catch (IOException ex) {
                Logger.getLogger(
                        AddConference.class.getName()).log(
                        Level.SEVERE, null, ex
                );
            }
        }

        conference.setImage(imgName);
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
        menuPlace.setItems(observableList);
        menuPlace.setValue(observableList.get(1));
    }




}
