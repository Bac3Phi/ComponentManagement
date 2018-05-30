package CM.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class ProviderManagerController implements Initializable {
    @FXML
    private JFXTextField txtProviderID;

    @FXML
    private JFXTextField txtProviderName;

    @FXML
    private JFXTextField txtProviderAddress;

    @FXML
    private JFXTextField txtProviderEmail;

    @FXML
    private JFXTextField txtProviderPhone;



    @FXML
    private ProgressBar progressPersonal;

    @FXML
    private Label lblComplete;

    @FXML
    private JFXButton btnPRINT;

    @FXML
    private JFXButton btnSEARCH;

    @FXML
    private JFXButton btnDELETE;

    @FXML
    private JFXButton btnUPDATE;

    @FXML
    private JFXButton btnADD;

    @FXML
    private JFXButton btnREFRESH;

    @FXML
    void setBtnADD(ActionEvent event) {

    }

    @FXML
    void setBtnDELETE(ActionEvent event) {

    }

    @FXML
    void setBtnPRINT(ActionEvent event) {

    }

    @FXML
    void setBtnREFRESH(ActionEvent event) {

    }

    @FXML
    void setBtnSEARCH(ActionEvent event) {

    }

    @FXML
    void setBtnUPDATE(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
