package Controllers;

import Handlers.AccountHandler;
import Handlers.ConferenceHandler;
import Models.Account;
import Models.Conference;
import Models.JoinTheConference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

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

    @FXML
    private TableView<Account> tableView;

    private Conference conference;

    ObservableList<Account> observableList = null;

    public DetailConferenceController() {
    }

    public void setConference(Conference conference) {
        this.conference = conference;

        System.out.println(conference.toString());
        try {
            this.imgUrl.setImage(new Image("/images/" + conference.getImage()));
        }catch (Exception ex){
            ex.printStackTrace();
            this.imgUrl.setImage(new Image("/images/1.png"));
        }

        this.conferenceId.setText(String.valueOf(this.conference.getConfId()));
        this.place.setText(this.conference.getPlace().toString());
        this.startDate.setText(this.conference.getStartDate().toString());
        this.endDate.setText(this.conference.getEndDate().toString());
        this.participants.setText(String.valueOf(this.conference.getParticipants()));
        this.shortDesc.setText(this.conference.getShortDesc());
        this.detailDesc.setText(this.conference.getDetailDesc());

        List<Account> accountList = new ArrayList<>();
        Set<JoinTheConference> set = this.conference.getJoinTheConference();
        System.out.println(set.size());
        set.forEach(joinTheConference -> {
            Account account = AccountHandler.getAccount(joinTheConference.getAccountId());
            accountList.add(account);
        });

        observableList = FXCollections.observableList(accountList);

        TableColumn<Account, Integer> accountId = new TableColumn<>("AccountId");
        accountId.setCellValueFactory(new PropertyValueFactory<Account, Integer>("accountId"));
        TableColumn<Account, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
        TableColumn<Account, String> email = new TableColumn<>("Name");
        email.setCellValueFactory(new PropertyValueFactory<Account, String>("email"));

        tableView.setItems(observableList);
        tableView.getColumns().addAll(accountId, name, email);
    }
}
