package CM.Controllers;

import CM.Models.Customer;
import CM.Models.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeesController implements Initializable {
    Alert alert;
    @FXML
    public TextField txtNVID, txtTenNV;
    public Button btnCANCEL, btnSEARCH, btnADD, btnUPDATE, btnDELETE;
    public TableView<Customer> tbvNV;
    public TableColumn<Customer, String> colNVID, colTenNV;
    public AnchorPane paneQLNV;

    DataProvider dbConn;
    ObservableList<Customer> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneQLNV = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvNV.setEditable(false);
    }
}
