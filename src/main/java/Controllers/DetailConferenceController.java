package Controllers;

import Models.Conference;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailConferenceController {
    @FXML
    private ImageView imgUrl;

    @FXML
    private Text name;

    @FXML
    private Text conferenceId;

    @FXML
    private Text place;

    @FXML
    private Text startDate;

    @FXML
    private Text endDate;

    @FXML
    private Text participants;

    @FXML
    private Text shortDesc;

    @FXML
    private TextArea detailDesc;

    private Conference conference;

    public DetailConferenceController() {
    }


    public void setConference(Conference conference) {
        this.conference = conference;

        System.out.println(conference.toString());
        this.imgUrl.setImage(new Image("/images/" + conference.getImage()));
        this.conferenceId.setText(String.valueOf(this.conference.getConfId()));
        this.place.setText(this.conference.getPlace().toString());
        this.startDate.setText(this.conference.getStartDate().toString());
        this.endDate.setText(this.conference.getEndDate().toString());
        this.participants.setText(String.valueOf(this.conference.getParticipants()));
        this.shortDesc.setText(this.conference.getShortDesc());
        this.detailDesc.setText(this.conference.getDetailDesc());
    }
}
