package CM.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesSearchController implements Initializable {
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
    private JFXRadioButton rdbtnEmployeesID;

    @FXML
    private JFXRadioButton rdbtnEmployeesName;

    @FXML
    private JFXRadioButton rdbtnDepartmentID;

    @FXML
    private JFXRadioButton rdbtnDepartmentName;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupByRaidioButton();
    }
    private void groupByRaidioButton(){
        final ToggleGroup group = new ToggleGroup();
        rdbtnEmployeesID.setToggleGroup(group);
        rdbtnEmployeesID.setSelected(true);
        rdbtnEmployeesName.setToggleGroup(group);
        rdbtnDepartmentID.setToggleGroup(group);
        rdbtnDepartmentName.setToggleGroup(group);
    }
}
