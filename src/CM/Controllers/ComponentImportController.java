package CM.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class ComponentImportController implements Initializable {
    @FXML
    private JFXTextField txtImportId;

    @FXML
    private JFXTextField txtCompMaker;

    @FXML
    private JFXDatePicker dpImportDate;

    @FXML
    private JFXTextField txtNote;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private ProgressBar progressPersonal1;

    @FXML
    private Label lblComplete1;

    @FXML
    private JFXButton btnClear1;

    @FXML
    private JFXButton btnEdit1;

    @FXML
    private JFXButton btnSave1;

    @FXML
    private JFXTreeTableView<?> tbvImportManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
