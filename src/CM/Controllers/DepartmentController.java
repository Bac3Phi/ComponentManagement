package CM.Controllers;

import CM.Models.DataProvider;
import CM.Models.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {

    DataProvider dbConn;
    ObservableList<Department> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
