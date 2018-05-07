package CM.Controllers;

import CM.Main;
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

public class CustomerController implements Initializable {
    Alert alert;
    @FXML
    public TextField txtKHID, txtTenKH, txtDiaChi, txtEmail, txtSoDT;
    public Button btnCANCEL, btnSEARCH, btnADD, btnUPDATE, btnDELETE;
    public TableView<Customer> tbvKH;
    public TableColumn<Customer, String> colKHID, colTenKH, colDiaChi, colEmail, colSoDT;
    public AnchorPane paneQLKH;

    DataProvider dbConn;
    ObservableList<Customer> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        dbConn = new DataProvider();
        paneQLKH = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvKH.setEditable(false);

        colKHID.setCellValueFactory(new PropertyValueFactory<>("KHID"));
        colTenKH.setCellValueFactory(new PropertyValueFactory<>("TenKH"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSoDT.setCellValueFactory(new PropertyValueFactory<>("sodienthoai"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvKH.setItems(data);
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM KHACHHANG");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Customer(
                    resultSet.getString("KHID"),
                    resultSet.getString("TENKH"),
                    resultSet.getString("DIACHI"),
                    resultSet.getString("email"),
                    resultSet.getString("SODIENTHOAI")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    @FXML
    //Hàm refresh xóa text
    public void refresh() {
        txtSoDT.setText("");
        txtKHID.setText("");
        txtTenKH.setText("");
        txtEmail.setText("");
        txtDiaChi.setText("");
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "", email = "", diachi = "", sodt = "";
        try {
            id = txtKHID.getText();
            ten = txtTenKH.getText();
            diachi = txtDiaChi.getText();
            email = txtEmail.getText();
            sodt = txtSoDT.getText();
            if (txtKHID.getText().isEmpty() || txtTenKH.getText().isEmpty()
                    || txtDiaChi.getText().isEmpty() || txtEmail.getText().isEmpty()
                    || txtSoDT.getText().isEmpty())
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, ten, diachi, email, sodt};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "KHACHHANG");
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
        String id = "", ten = "", email = "", diachi = "", sodt = "";
        try {
            id = txtKHID.getText();
            ten = txtTenKH.getText();
            diachi = txtDiaChi.getText();
            email = txtEmail.getText();
            sodt = txtSoDT.getText();
            if (txtKHID.getText().isEmpty() || txtTenKH.getText().isEmpty()
                    || txtDiaChi.getText().isEmpty() || txtEmail.getText().isEmpty()
                    || txtSoDT.getText().isEmpty())
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
        String[] dataUpdate = {id, ten, diachi, email, sodt};
        int isUpdated = dbConn.ExecuteSQLUpdate(dataUpdate, "KHACHHANG");
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
        if (txtKHID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Plese fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtKHID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "KHACHHANG");
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
