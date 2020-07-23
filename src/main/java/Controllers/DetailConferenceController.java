package Controllers;

import Handlers.AccountHandler;
import Handlers.ConferenceHandler;
import Handlers.JoinTheConferenceHandler;
import Models.Account;
import Models.Conference;
import Models.JoinTheConference;
import Utils.AlertDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
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

    @FXML
    private Button register;

    @FXML
    void handlerRegister(ActionEvent event) {

    }

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
        this.name.setText(this.conference.getName());
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
            Account account = AccountHandler.getAccount(joinTheConference.getAccountByAccountId().getAccountId());
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

        if(LocalDate.now().compareTo(conference.getStartDate()) >= 0){
            register.setText("Joined");
            register.setStyle("-fx-background-color: #039903");
            register.setDisable(true);
        }else if(conference.getParticipants() == conference.getJoinTheConference().size()) {
            register.setText("Enough");
            register.setStyle("-fx-background-color: #d57000");
            register.setDisable(true);
        }

        if(DashboardUserController.account != null){
            accountList.forEach(account -> {
                if (account.getAccountId() == DashboardUserController.account.getAccountId()) {
                    register.setDisable(true);
                }
            });
        }

        register.setOnAction(event -> {
            if(DashboardUserController.account != null) {
                JoinTheConference joinTheConference = new JoinTheConference(conference, DashboardUserController.account);
                System.out.println(joinTheConference.toString());
                if (JoinTheConferenceHandler.add(joinTheConference)) {
                    AlertDialog.showAlertWithoutHeaderText("Alert", "Successfully", "success");

                } else {
                    AlertDialog.showAlertWithoutHeaderText("Alert", "Failed something went wrong", "failed");
                }
                Stage stage = (Stage) imgUrl.getScene().getWindow();
                stage.close();
            }else if(DashboardAdminController.account != null){

            }else {
                AlertDialog.showConfirmation();
            }
        });







    }
}
