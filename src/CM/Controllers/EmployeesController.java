package CM.Controllers;

import CM.Models.Customer;
import CM.Models.DataProvider;
import CM.Models.Employees;
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

public class EmployeesController implements Initializable {
    Alert alert;
    @FXML
    public TextField txtNVID, txtTenNV, txtPBID;
    public RadioButton rbMALE, rbFEMALE;
    public Button btnCANCEL, btnSEARCH, btnADD, btnUPDATE, btnDELETE;
    public TableView<Employees> tbvNV;
    public TableColumn<Employees, String> colNVID, colTenNV, colGender, colPBID;
    public AnchorPane paneQLNV;
    public ToggleGroup group;

    DataProvider dbConn;
    ObservableList<Employees> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneQLNV = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvNV.setEditable(false);
        group = new ToggleGroup();

        colNVID.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        colTenNV.setCellValueFactory(new PropertyValueFactory<>("TenNV"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("Phai"));
        colPBID.setCellValueFactory(new PropertyValueFactory<>("MaPhong"));

        rbMALE.setToggleGroup(group);
        rbMALE.setSelected(true);
        rbFEMALE.setToggleGroup(group);

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvNV.setItems(data);
        tbvNV.setOnMouseClicked(new EventHandler<MouseEvent>() {
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

    @FXML
    //Hàm refresh xóa text
    public void refresh() {
        txtNVID.setText("");
        txtTenNV.setText("");
        rbFEMALE.setSelected(false);
        rbMALE.setSelected(true);
        txtPBID.setText("");
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        Employees selectedRow = tbvNV.getSelectionModel().getSelectedItem();
        txtNVID.setText(selectedRow.getMaNV());
        txtTenNV.setText(selectedRow.getTenNV());
        if (selectedRow.getPhai().contains("Nam"))
        {
            rbMALE.setSelected(true);
            rbFEMALE.setSelected(false);
        }
        else if (selectedRow.getPhai().contains("Nữ")) {
            rbMALE.setSelected(false);
            rbFEMALE.setSelected(true);
        }
        txtPBID.setText(selectedRow.getMaPhong());
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "", phai = "", phong = "";
        try {
            id = txtNVID.getText();
            ten = txtTenNV.getText();
            if (rbMALE.isSelected())
            {
                phai = "Nam";
                rbFEMALE.setSelected(false);
            }
            else if (rbFEMALE.isSelected()) {
                phai = "Nữ";
                rbMALE.setSelected(false);
            }
            phong = txtPBID.getText();
            if (txtNVID.getText().isEmpty() || txtTenNV.getText().isEmpty()
                    || txtPBID.getText().isEmpty() || (rbFEMALE.isSelected() == false
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
            id = txtNVID.getText();
            ten = txtTenNV.getText();
            if (rbMALE.isSelected()) {
                phai = "Nam";
                rbFEMALE.setSelected(false);
            }
            else if (rbFEMALE.isSelected()) {
                phai = "Nữ";
                rbMALE.setSelected(false);
            }
            phong = txtPBID.getText();
            if (txtNVID.getText().isEmpty() || txtTenNV.getText().isEmpty()
                    || txtPBID.getText().isEmpty() || (rbFEMALE.isSelected() == false
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
        if (txtNVID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Please fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtNVID.getText()};
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

    }

    @FXML
    public void setBtnCANCEL (ActionEvent event)throws Exception{
        alert = new Alert(Alert.AlertType.WARNING, "Do you want to close this?", ButtonType.YES, ButtonType.NO);
        alert.show();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.YES) {

        }
    }
}
