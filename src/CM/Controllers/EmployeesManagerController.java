package CM.Controllers;

import CM.Models.DataProvider;
import CM.Models.Employees;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import static CM.Controllers.EmployeesController.tabPaneEx;

public class EmployeesManagerController implements Initializable {
    Alert alert;
    @FXML
    private JFXTextField txtEmployeeID;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private JFXRadioButton rbMALE;

    @FXML
    private JFXRadioButton rbFEMALE;

    @FXML
    private TableView<Employees> tbvEmployees;

    @FXML
    private TableColumn<Employees, String> colEmployeeID;

    @FXML
    private TableColumn<Employees, String> colEmployeeName;

    @FXML
    private TableColumn<Employees, String> colEmployeeGender;

    @FXML
    private TableColumn<Employees, String> colDepartmentID;

    @FXML
    private JFXTextField txtDepartmentID;

    @FXML
    private JFXComboBox<?> cbxDepartmentName;

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

    public AnchorPane paneEmployeesManagement;
    public ToggleGroup group;

    DataProvider dbConn;
    ObservableList<Employees> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneEmployeesManagement = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvEmployees.setEditable(false);
        group = new ToggleGroup();

        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colEmployeeGender.setCellValueFactory(new PropertyValueFactory<>("EmployeeGender"));
        colDepartmentID.setCellValueFactory(new PropertyValueFactory<>("DepartmentID"));

        rbMALE.setToggleGroup(group);
        rbMALE.setSelected(true);
        rbFEMALE.setToggleGroup(group);

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvEmployees.setItems(data);
        tbvEmployees.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM NHANVIEN");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Employees(
                    resultSet.getString("MaNV"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("Phai"),
                    resultSet.getString("MaPhong")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    //Hàm refresh xóa text
    public void refresh() {
        txtEmployeeID.setText("");
        txtEmployeeName.setText("");
        rbFEMALE.setSelected(false);
        rbMALE.setSelected(true);
        txtDepartmentID.setText("");
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        Employees selectedRow = tbvEmployees.getSelectionModel().getSelectedItem();
        txtEmployeeID.setText(selectedRow.getEmployeeID());
        txtEmployeeName.setText(selectedRow.getEmployeeName());
        if (selectedRow.getEmployeeGender().contains("Nam"))
        {
            rbMALE.setSelected(true);
            rbFEMALE.setSelected(false);
        }
        else if (selectedRow.getEmployeeGender().contains("Nữ")) {
            rbMALE.setSelected(false);
            rbFEMALE.setSelected(true);
        }
        txtDepartmentID.setText(selectedRow.getDepartmentID());
    }

    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "", phai = "", phong = "";
        try {
            id = txtEmployeeID.getText();
            ten = txtEmployeeName.getText();
            if (rbMALE.isSelected())
            {
                phai = "Nam";
                rbFEMALE.setSelected(false);
            }
            else if (rbFEMALE.isSelected()) {
                phai = "Nữ";
                rbMALE.setSelected(false);
            }
            phong = txtDepartmentID.getText();
            if (txtEmployeeID.getText().isEmpty() || txtEmployeeName.getText().isEmpty()
                    || txtDepartmentID.getText().isEmpty() || (rbFEMALE.isSelected() == false
                    && rbMALE.isSelected() == false))
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, ten, phai, phong};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "NHANVIEN");
        if (isInserted > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data are successfully inserted !!!");
            alert.show();
        }
        else
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data are not inserted !!!");
            alert.show();
        }
        try {
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    //Update dữ liệu
    public void updateData() {
        String id = "", ten = "", phai = "", phong = "";
        try {
            id = txtEmployeeID.getText();
            ten = txtEmployeeName.getText();
            if (rbMALE.isSelected()) {
                phai = "Nam";
                rbFEMALE.setSelected(false);
            }
            else if (rbFEMALE.isSelected()) {
                phai = "Nữ";
                rbMALE.setSelected(false);
            }
            phong = txtDepartmentID.getText();
            if (txtEmployeeID.getText().isEmpty() || txtEmployeeName.getText().isEmpty()
                    || txtDepartmentID.getText().isEmpty() || (rbFEMALE.isSelected() == false
                    && rbMALE.isSelected() == false))
            {
                alert = new Alert(Alert.AlertType.WARNING,
                        "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, ten, phai, phong};
        String[] colLabel = {"MaNV", "TenNV", "Phai", "MaPhong"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "NHANVIEN");
        if (isUpdated > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data are successfully updated !!!");
            alert.show();
        }
        else
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data are not updated !!!");
            alert.show();
        }
        try {
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    //Delete dữ liệu
    public void deleteData() {
        if (txtEmployeeID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Please fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtDepartmentID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "NHANVIEN", "MaNV");
        if (isDeleted > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data are successfully deleted !!!");
            alert.show();
        }
        else
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data are not deleted !!!");
            alert.show();
        }
        try {
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    @FXML
    public void setBtnADD (ActionEvent event)throws Exception{
        insertData();
        if (btnADD.isPressed()) {
            refresh();
        }
    }

    @FXML
    public void setBtnUPDATE (ActionEvent event)throws Exception{
        updateData();
        if (btnUPDATE.isPressed()) {
            refresh();
        }
    }

    @FXML
    public void setBtnDELETE (ActionEvent event)throws Exception{
        deleteData();
        if (btnDELETE.isPressed()) {
            refresh();
        }
    }

    @FXML
    public void setBtnSEARCH (ActionEvent event)throws Exception{
        tabPaneEx.getSelectionModel().select(1);
    }

    @FXML
    public void setBtnCANCEL (ActionEvent event)throws Exception{
        alert = new Alert(Alert.AlertType.WARNING, "Do you want to close this?", ButtonType.YES, ButtonType.NO);
        alert.show();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {

        }
    }

    public void setBtnREFRESH(ActionEvent actionEvent) {
        refresh();
    }
}
