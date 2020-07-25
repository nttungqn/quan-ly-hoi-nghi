package Controllers;

import Models.Account;
import Models.Conference;
import Utils.AlertDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CardItem extends VBox implements Initializable {

    @FXML
    private Text startDate;

    @FXML
    private Text endDate;

    @FXML
    private ImageView imgUrl;

    @FXML
    private Text name;

    @FXML
    private Button signin;

    public Conference conference;

    private Account account;

    @FXML
    void handlerDetail(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/detailConference.fxml"));
            Parent parent = loader.load();

            DetailConferenceController detailConferenceController = loader.getController();
            detailConferenceController.setConference(this.conference);

            Stage stage = new Stage();
            stage.setTitle("Detail conference");
            stage.setScene(new Scene(parent));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handlerSignIn(ActionEvent event) {
        AlertDialog.showConfirmation();
    }


    public CardItem(Conference conference) {
        super();
        try {
            loadFxml(CardItem.class.getResource("/Views/cardViewItem.fxml"), this);
            this.conference = conference;
            this.name.setText(this.conference.getName());

            try {
                this.startDate.setText(this.conference.getStartDate().toString());
            }catch (Exception ex){
                this.startDate.setText(LocalDate.now().toString());
                ex.printStackTrace();
            }

            try {
                this.endDate.setText(this.conference.getEndDate().toString());
            }catch (Exception ex){
                this.endDate.setText(LocalDate.now().toString());
                ex.printStackTrace();
            }

            try {
                String imgURL = "/images/" + this.conference.getImage();
                System.out.println(imgURL);
                this.imgUrl.setImage(new Image(imgURL));
            }catch (Exception ex){
                this.imgUrl.setImage(new Image("/images/" + this.conference.getImage()));
                ex.printStackTrace();
            }

            if(LocalDate.now().compareTo(conference.getStartDate()) >= 0){
                signin.setText("Completed");
                signin.setStyle("-fx-background-color: #039903");
                signin.setDisable(true);
            }else if(conference.getParticipants() == conference.getJoinTheConference().size()) {
                signin.setText("Enough");
                signin.setStyle("-fx-background-color: #d57000");
                signin.setDisable(true);
            }



        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected static void loadFxml(URL fxmlFile, Object rootController) {
        FXMLLoader loader = new FXMLLoader(fxmlFile);
        loader.setController(rootController);
        loader.setRoot(rootController);
        try {
            loader.load();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public Conference getConference() {
        return conference;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setAccount(Account account){
        this.account = account;
    }
}
