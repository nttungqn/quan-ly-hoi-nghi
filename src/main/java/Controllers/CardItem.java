package Controllers;

import Models.Conference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardItem extends VBox  {

    @FXML
    private Text startDate;

    @FXML
    private Text endDate;

    @FXML
    private ImageView imgUrl;

    @FXML
    private Text name;


    public CardItem(String name, String startDate, String endDate, String imgUrl) {
        super();
        try {
            loadFxml(CardItem.class.getResource("/Views/cardViewItem.fxml"), this);
            this.name.setText(name);
            this.startDate.setText(startDate);
            this.endDate.setText(endDate);
            this.imgUrl.setImage(new Image("/images/" + imgUrl));
        }catch (Exception ex){

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

}
