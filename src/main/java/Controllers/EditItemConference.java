package Controllers;

import Handlers.ConferenceHandler;
import Handlers.PlaceHandler;
import Models.Conference;
import Models.Place;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditItemConference implements Initializable {

    @FXML
    private AnchorPane rootPane;

    private int confId;

    @FXML
    private ImageView imageConference;

    @FXML
    private TextField topic;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField countParticipant;

    @FXML
    private ChoiceBox menuPlace;

    @FXML
    private TextField brefDes;

    @FXML
    private TextArea detailDes;

    @FXML
    private Button updateBtn;

    @FXML
    private Button cancelBtn;

    ObservableList<Place> observableList = FXCollections.observableList(PlaceHandler.loadList());

    private Conference conference;

    private String pathToImage;

    private String imgName;

    private File file;

    public EditItemConference() {
    }


    @FXML
    void handlerChooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        stage.setTitle("File choose image");
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {

            Image image = new Image(file.toURI().toString());
            imageConference.setImage(image);
//            file:/home/nttung/Pictures/ly-ca-phe-thien-chi.png
            this.pathToImage = file.toPath().toString();
//            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
//            BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

            // copy image to dir "/resource/images/.."
            String currentDir = System.getProperty("user.dir");

            String[] arr = file.toPath().toString().split("/");
            this.imgName = arr[arr.length - 1];

//        System.out.println(imgName);
            File dest = new File(currentDir + "/src/main/resources/images/" + this.imgName);

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
        }
    }

    @FXML
    public void update(ActionEvent actionEvent) {
        Conference tmp = new Conference();
        tmp.setConfId(this.confId);
        tmp.setName(topic.getText());
        tmp.setStartDate(LocalDate.parse(startDate.getValue().toString()));
        tmp.setEndDate(LocalDate.parse(endDate.getValue().toString()));
        tmp.setDetailDesc(detailDes.getText());
        tmp.setShortDesc(brefDes.getText());
        tmp.setPlace((Place) menuPlace.getValue());
        tmp.setParticipants(Integer.parseInt(countParticipant.getText()));

        tmp.setImage(this.imgName);
        System.out.println(tmp.toString());
        boolean result = ConferenceHandler.update(tmp);
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
        menuPlace.setValue(observableList.get(0));
    }

    public void setConference(Conference conference) {
        this.conference = conference;

        System.out.println(conference.toString());

        this.confId = this.conference.getConfId();
        this.topic.setText(this.conference.getName());
        this.startDate.setValue(this.conference.getStartDate());
        this.endDate.setValue(this.conference.getEndDate());
        this.countParticipant.setText(String.valueOf(this.conference.getParticipants()));
        this.brefDes.setText(this.conference.getShortDesc());
        this.detailDes.setText(this.conference.getDetailDesc());
        try {
            this.imageConference.setImage(new Image("/images/" + this.conference.getImage()));
            this.imgName = this.conference.getName();
        }catch (Exception ex){
            ex.printStackTrace();
            this.imageConference.setImage(new Image("/images/1.png"));
            this.imgName = "1.png";
        }
    }
}
