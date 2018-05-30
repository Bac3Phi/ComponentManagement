package CM.Controllers;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeesViewController implements Initializable {
    @FXML
    private JFXTabPane tabPane;

    public static JFXTabPane tabPaneEx;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabPaneEx = tabPane;
    }
}
