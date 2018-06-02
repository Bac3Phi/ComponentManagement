package CM.Controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class BillSearchController implements Initializable {
    @FXML
    private JFXButton btnPRINT;

    @FXML
    private JFXButton btnSEARCH;

    @FXML
    private JFXButton btnGETINFO;

    @FXML
    private JFXButton btnREFRESH;

    @FXML
    private JFXTextArea txtSEARCH;

    @FXML
    private JFXRadioButton rdbtnBillID;

    @FXML
    private JFXRadioButton rdbtnCustomerID;

    @FXML
    private JFXRadioButton rdbtnEmployeesID;

    @FXML
    private JFXDatePicker dateCheckIn;

    @FXML
    private JFXDatePicker dateCheckOut;

    @FXML
    void setBtnGETINFO(ActionEvent event) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
