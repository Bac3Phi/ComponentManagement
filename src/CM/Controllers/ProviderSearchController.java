package CM.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ProviderSearchController implements Initializable {
    @FXML
    private ProgressBar progressPersonal;

    @FXML
    private Label lblComplete;

    @FXML
    private JFXButton btnPRINT;

    @FXML
    private JFXButton btnSEARCH;

    @FXML
    private JFXButton btnGETINFO;

    @FXML
    private JFXButton btnREFRESH;

    @FXML
    private JFXRadioButton rdbtnProviderID;

    @FXML
    private JFXRadioButton rdbtnProviderName;

    @FXML
    private JFXRadioButton rdbtnProviderAddress;

    @FXML
    private JFXRadioButton rdbtnProviderEmail;

    @FXML
    private JFXRadioButton rdbtnProviderPhone;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setBtnPRINT(javafx.event.ActionEvent actionEvent) {
    }

    public void setBtnSEARCH(javafx.event.ActionEvent actionEvent) {
    }

    public void setBtnGETINFO(javafx.event.ActionEvent actionEvent) {
    }

    public void setBtnREFRESH(javafx.event.ActionEvent actionEvent) {
    }
}
