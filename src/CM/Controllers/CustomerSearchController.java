package CM.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerSearchController implements Initializable {

    @FXML
    private JFXButton btnClear12;

    @FXML
    private JFXButton btnSEARCH;

    @FXML
    private JFXButton btnADD;

    @FXML
    private JFXButton btnSEARCH1;

    @FXML
    private JFXRadioButton rdbtnCustomerID;

    @FXML
    private JFXRadioButton rdbtnCustomerName;

    @FXML
    private JFXRadioButton rdbtnCustomerAddress;

    @FXML
    private JFXRadioButton rdbtnCustomerEmail;

    @FXML
    private JFXRadioButton rdbtnCustomerPhone;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupByRaidioButton();
    }
    private void groupByRaidioButton(){
        final ToggleGroup group = new ToggleGroup();
        rdbtnCustomerID.setToggleGroup(group);
        rdbtnCustomerID.setSelected(true);
        rdbtnCustomerAddress.setToggleGroup(group);
        rdbtnCustomerEmail.setToggleGroup(group);
        rdbtnCustomerName.setToggleGroup(group);
        rdbtnCustomerPhone.setToggleGroup(group);
    }
}
