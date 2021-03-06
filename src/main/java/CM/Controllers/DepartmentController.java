package CM.Controllers;

import CM.Functions.SmileNotification;
import CM.Models.DataProvider;
import CM.Models.Department;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DepartmentController implements Initializable {

    @FXML
    public TextField txtDepartmentID, txtDepartmentName;
    public Button btnCANCEL, btnADD, btnUPDATE, btnDELETE;
    public TableView<Department> tbvDepartment;
    public TableColumn<Department, String> colDepartmentID, colDepartmentName;
    public AnchorPane paneDepartment;
    DataProvider dbConn;
    ObservableList<Department> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneDepartment = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvDepartment.setEditable(false);

        colDepartmentID.setCellValueFactory(new PropertyValueFactory<>("DepartmentID"));
        colDepartmentName.setCellValueFactory(new PropertyValueFactory<>("DepartmentName"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvDepartment.setItems(data);
        tbvDepartment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM PHONGBAN");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Department(
                    resultSet.getString("MaPhong"),
                    resultSet.getString("TenPhong")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    //Hàm refresh xóa text
    public void refresh() {
        txtDepartmentID.setText("");
        txtDepartmentName.setText("");
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        Department selectedRow = tbvDepartment.getSelectionModel().getSelectedItem();
        txtDepartmentID.setText(selectedRow.getDepartmentID());
        txtDepartmentName.setText(selectedRow.getDepartmentName());
    }

    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "";
        try {
            id = txtDepartmentID.getText();
            ten = txtDepartmentName.getText();
            if (txtDepartmentID.getText().isEmpty() || txtDepartmentName.getText().isEmpty())
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, ten};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "PHONGBAN");
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
        String id = "", ten = "";
        try {
            id = txtDepartmentID.getText();
            ten = txtDepartmentName.getText();
            if (txtDepartmentID.getText().isEmpty() || txtDepartmentName.getText().isEmpty())
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, ten};
        String[] colLabel = {"MaPhong", "TenPhong"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "PHONGBAN");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
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
        if (txtDepartmentID.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        String[] dataDelete = {txtDepartmentID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "PHONGBAN", "MaPhong");
        if (isDeleted > 0) {
            SmileNotification.creatingNotification("Thông báo","Xóa dữ liệu thành công",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông Báo","Vui lòng hoàn thành 100%",NotificationType.INFORMATION);
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
    public void setBtnCANCEL (ActionEvent event)throws Exception{
        Alert alert;
        alert = new Alert(Alert.AlertType.WARNING, "Do you want to close this?", ButtonType.YES, ButtonType.NO);
        alert.show();


        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {

        }
    }
}
