package CM.Controllers;

import CM.Models.DataProvider;
import CM.Models.Department;
import CM.Models.Employees;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private JFXTextArea txtSEARCH;

    @FXML
    private JFXRadioButton rdbtnEmployeesID;

    @FXML
    private JFXRadioButton rdbtnEmployeesName;

    @FXML
    private JFXRadioButton rdbtnDepartmentName;

    @FXML
    private TableView<Employees> tbvSEARCH;

    @FXML
    private TableColumn<Employees, String> colEmployeeID;

    @FXML
    private TableColumn<Employees, String> colEmployeeName;

    @FXML
    private TableColumn<Employees, String> colEmployeeGender;

    @FXML
    private TableColumn<Employees, String> colDepartmentName;

    DataProvider dbConn;
    ObservableList<Employees> list;
    ObservableList<Department> otherlist;
    ResultSet resultSet;
    EmployeesManagerController pointer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupByRaidioButton();
        dbConn = new DataProvider();
        list = FXCollections.observableArrayList();
        tbvSEARCH.setEditable(false);
        tbvSEARCH.setItems(list);
        pointer = new EmployeesManagerController();

        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colEmployeeGender.setCellValueFactory(new PropertyValueFactory<>("EmployeeGender"));
        colDepartmentName.setCellValueFactory(new PropertyValueFactory<>("DepartmentName"));

        tbvSEARCH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGETINFO.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Employees selectedRow = tbvSEARCH.getSelectionModel().getSelectedItem();
                        try {
                            pointer.data.removeAll(pointer.data);
                            pointer.data.add(selectedRow);
                            pointer.tbvEmployees.setItems(pointer.data);
                        }
                        catch (NullPointerException e) {}
                    }
                });
            }
        });

        tbvSEARCH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGETINFO.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Employees selectedRow = tbvSEARCH.getSelectionModel().getSelectedItem();
                        try {
                            pointer.data.removeAll(pointer.data);
                            pointer.data.add(selectedRow);
                            pointer.tbvEmployees.setItems(pointer.data);
                        }
                        catch (NullPointerException e) {}
                    }
                });
            }
        });
    }

    public void searchData (String field, String str) throws SQLException {
        String query = "SELECT NV.MaNV, NV.TenNV, NV.Phai, PB.TenPhong FROM NHANVIEN NV JOIN PHONGBAN PB ON PB.MaPhong = NV.MaPhong WHERE " + field + " LIKE N'%" + str +"%';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new Employees(
                    resultSet.getString("MaNV"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("Phai"),
                    resultSet.getString("TenPhong")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    private void groupByRaidioButton(){
        final ToggleGroup group = new ToggleGroup();
        rdbtnEmployeesID.setToggleGroup(group);
        rdbtnEmployeesID.setSelected(true);
        rdbtnEmployeesName.setToggleGroup(group);
        rdbtnDepartmentName.setToggleGroup(group);
    }

    public void setBtnSEARCH (ActionEvent event) {
        String[] str = {"MaNV", "TenNV", "TenPhong"};
        try {
            if (rdbtnEmployeesID.isSelected())
                searchData(str[0], txtSEARCH.getText());
            else if (rdbtnEmployeesName.isSelected())
                searchData(str[1], txtSEARCH.getText());
            else if (rdbtnDepartmentName.isSelected())
                searchData(str[2], txtSEARCH.getText());
        } catch (SQLException e) {}
    }

    public void setBtnREFRESH(ActionEvent event) {
        txtSEARCH.setText(null);
        list.removeAll(list);
        rdbtnEmployeesID.setSelected(true);
        rdbtnEmployeesName.setSelected(false);
        rdbtnDepartmentName.setSelected(false);
    }
}
