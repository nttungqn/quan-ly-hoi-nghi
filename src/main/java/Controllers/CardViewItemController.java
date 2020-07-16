package Controllers;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CardViewItemController{
    @FXML
    private Text name;

    @FXML
    private Text startDate;

    @FXML
    private Text endDate;

    @FXML
    private ImageView imgUrl;


    public CardViewItemController (String name, String startDate, String endDate, String imgURL) {
        this.name.setText(name);
        this.startDate.setText(startDate);
        this.endDate.setText(endDate);
        this.imgUrl.setImage(new Image("/images/1.png"));
    }
}
