package Controllers;

import Handlers.DiaDiemHandler;
import Handlers.HoiNghiHandler;
import Models.DiaDiem;
import Models.HoiNghi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddConference implements Initializable {
    @FXML
    public TextField topic;
    @FXML
    public TextField countParticipant;
    @FXML
    public DatePicker startDate;
    @FXML
    public DatePicker endDate;
    @FXML
    public TextField brefDes;
    @FXML
    public ChoiceBox menuDiaDiem;
    @FXML
    public TextArea detailDes;
    @FXML
    public Button saveBtn;
    @FXML
    public Button cancelBtn;
    public AnchorPane rootPane;

    ObservableList<DiaDiem> observableList = FXCollections.observableList(DiaDiemHandler.loadList());

    public AddConference() {
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        HoiNghi hoiNghi = new HoiNghi();
        hoiNghi.setTenHN(topic.getText());
        hoiNghi.setDiaDiem(DiaDiemHandler.getDiaDiem(1));
        hoiNghi.setNgayBD(LocalDate.parse(startDate.getValue().toString()));
        hoiNghi.setNgayKT(LocalDate.parse(endDate.getValue().toString()));
        hoiNghi.setMtChiTiet(detailDes.getText());
        hoiNghi.setMtNganGon(brefDes.getText());
        hoiNghi.setDiaDiem((DiaDiem) menuDiaDiem.getValue());
        System.out.println(hoiNghi.toString());
        boolean result = HoiNghiHandler.add(hoiNghi);
        if(result){
            System.out.println("Add conference successfully!!");
        } else {
            System.out.println("Failed");
        }
    }
    @FXML
    private void cancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        menuDiaDiem.setItems(observableList);
        menuDiaDiem.setValue(observableList.get(0));
    }


}
