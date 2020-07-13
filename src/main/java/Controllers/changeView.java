package Controllers;

import javafx.scene.layout.Border;

public class changeView {
    @FXML
    private Border border

    @FXML
    private void handleChangeView(ActionEvent event) {
        try {
            String menuItemID = ((MenuItem) event.getSource()).getId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(menuItemID + ".fxml"));
            loader.setController(this);

            mainView.setCenter(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
