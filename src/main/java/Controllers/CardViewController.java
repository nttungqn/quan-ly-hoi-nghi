package Controllers;

import Handlers.ConferenceHandler;
import Models.Conference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CardViewController extends Pane implements Initializable{
    @FXML
    private FlowPane flowPane;

    public CardViewController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Conference> conferenceList = ConferenceHandler.loadList();
        conferenceList.forEach(conference -> {
            CardItem cardItem = new CardItem(conference);
            flowPane.getChildren().add(cardItem);
        });



    }


}
