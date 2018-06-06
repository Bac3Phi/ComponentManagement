package CM.Controllers;

import CM.Functions.GenerateID;
import CM.Functions.SmileNotification;
import CM.Models.DataProvider;
import CM.Models.Department;
import CM.Models.Employees;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import tray.notification.NotificationType;

import java.io.IOError;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    public TableView<Employees> tbvEmployees;

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
    private JFXComboBox<String> cbxDepartmentName;

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
    ObservableList<Department> list;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneEmployeesManagement = new AnchorPane();
        data = FXCollections.observableArrayList();
        list = FXCollections.observableArrayList();
        tbvEmployees.setEditable(false);
        group = new ToggleGroup();
        txtEmployeeID.setEditable(false);
        txtEmployeeID.setText(GenerateID.create("NhanVien","MaNV","NV"));

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
        updateProcess();
    }
    private static double progress1 = 0;
    private static double progress2 = 0;

    private void updateProcess() {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        //progressPersonal.setProgress(0.00);
        double sum_progress = progress1 + progress2 ;
        txtDepartmentID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.5;

                } else {
                    progress1 = 0.0;

                }

                double sum = ( progress1 + progress2);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });
        txtEmployeeName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.5;

                } else {
                    progress1 = 0.0;

                }

                double sum = ( progress1 + progress2);
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM PHONGBAN");
        list.removeAll(list);
        while(resultSet.next()) {
            list.add(new Department(
                    resultSet.getString("MaPhong"),
                    resultSet.getString("TenPhong")
            ));
        }

        ObservableList<String> listdata;
        String[] str = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i).getDepartmentName();
        }
        listdata = FXCollections.observableArrayList(str);
        cbxDepartmentName.setItems(listdata);

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
       // txtDepartmentID.setText("");
        txtEmployeeID.setText(GenerateID.create("NhanVIen","maNV","NV"));
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
        try {
            String str = txtDepartmentID.getText();
            String query = "SELECT * FROM PHONGBAN WHERE MaPhong = N'" + str + "';";
            resultSet = dbConn.getData(query);
            list.removeAll(list);
            while(resultSet.next()) {
                list.add(new Department(
                        resultSet.getString("MaPhong"),
                        resultSet.getString("TenPhong")
                ));
            }
            cbxDepartmentName.getSelectionModel().select(list.get(0).getDepartmentName());
        }catch (SQLException e) {}
    }

    public void setSelectedItem(ActionEvent event) {
        try {
            String selectedData = cbxDepartmentName.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM PHONGBAN WHERE TenPhong = N'" + selectedData + "' COLLATE utf8_unicode_ci;";
            resultSet = dbConn.getData(query);
            list.removeAll(list);
            while(resultSet.next()) {
                list.add(new Department(
                        resultSet.getString("MaPhong"),
                        resultSet.getString("TenPhong")
                ));
            }
            txtDepartmentID.setText(list.get(0).getDepartmentID());
        }catch (SQLException e) {}
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
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, ten, phai, phong};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "NHANVIEN");
        if (isInserted > 0) {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
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
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
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
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
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
        String[] dataDelete = {txtEmployeeID.getText()};
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

    public void setBtnREFRESH(ActionEvent actionEvent) {
        refresh();
        try {
            showData();
        } catch (SQLException e) {}
        catch (IOException ex) {}
    }
}
