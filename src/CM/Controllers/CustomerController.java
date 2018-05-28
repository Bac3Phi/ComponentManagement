package CM.Controllers;

import CM.Models.Customer;
import CM.Models.DataProvider;
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

public class CustomerController implements Initializable {
    Alert alert;
    @FXML
    public TextField txtCustomerID, txtCustomerName, txtCustomerAddress, txtCustomerEmail, txtCustomerPhone;
    public Button btnCANCEL, btnSEARCH, btnADD, btnUPDATE, btnDELETE;
    public TableView<Customer> tbvCustomer;
    public TableColumn<Customer, String> colCustomerID, colCustomerName, colCustomerAddress, colCustomerEmail, colCustomerPhone;
    public AnchorPane paneCustomerManagement;

    DataProvider dbConn;
    ObservableList<Customer> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        dbConn = new DataProvider();
        paneCustomerManagement = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvCustomer.setEditable(false);

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("CustomerEmail"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhoneNo"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvCustomer.setItems(data);
        tbvCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM KHACHHANG");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Customer(
                    resultSet.getString("MaKH"),
                    resultSet.getString("TenKH"),
                    resultSet.getString("DiaChi"),
                    resultSet.getString("Email"),
                    resultSet.getString("SoDT")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    @FXML
    //Hàm refresh xóa text
    public void refresh() {
        txtCustomerPhone.setText("");
        txtCustomerID.setText("");
        txtCustomerName.setText("");
        txtCustomerEmail.setText("");
        txtCustomerAddress.setText("");
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        Customer selectedRow = tbvCustomer.getSelectionModel().getSelectedItem();
        txtCustomerID.setText(selectedRow.getCustomerID());
        txtCustomerName.setText(selectedRow.getCustomerName());
        txtCustomerAddress.setText(selectedRow.getCustomerAddress());
        txtCustomerEmail.setText(selectedRow.getCustomerEmail());
        txtCustomerPhone.setText(selectedRow.getCustomerPhoneNo());
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "", email = "", diachi = "", sodt = "";
        try {
            id = txtCustomerID.getText();
            ten = txtCustomerName.getText();
            diachi = txtCustomerAddress.getText();
            email = txtCustomerEmail.getText();
            sodt = txtCustomerPhone.getText();
            if (txtCustomerID.getText().isEmpty() || txtCustomerName.getText().isEmpty()
                    || txtCustomerAddress.getText().isEmpty() || txtCustomerEmail.getText().isEmpty()
                    || txtCustomerPhone.getText().isEmpty())
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
            id = txtCustomerID.getText();
            ten = txtCustomerName.getText();
            diachi = txtCustomerAddress.getText();
            email = txtCustomerEmail.getText();
            sodt = txtCustomerPhone.getText();
            if (txtCustomerID.getText().isEmpty() || txtCustomerName.getText().isEmpty()
                    || txtCustomerAddress.getText().isEmpty() || txtCustomerEmail.getText().isEmpty()
                    || txtCustomerPhone.getText().isEmpty())
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
        String[] colLabel = {"MaKH", "TenKH", "DiaChi", "Email", "SoDT"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "KHACHHANG");
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
        if (txtCustomerID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Plese fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtCustomerID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "KHACHHANG", "MaKH");
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
