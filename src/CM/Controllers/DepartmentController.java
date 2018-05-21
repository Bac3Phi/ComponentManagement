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
    Alert alert;
    @FXML
    public TextField txtNVID, txtTenNV, txtPBID;
    public RadioButton rbMALE, rbFEMALE;
    public Button btnCANCEL, btnSEARCH, btnADD, btnUPDATE, btnDELETE;
    public TableView<Department> tbvNV;
    public TableColumn<Department, String> colNVID, colTenNV, colGender, colPBID;
    public AnchorPane paneQLNV;

    DataProvider dbConn;
    ObservableList<Department> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        dbConn = new DataProvider();
//        paneQLNV = new AnchorPane();
//        data = FXCollections.observableArrayList();
//        tbvNV.setEditable(false);
//
//        colNVID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
//        colTenNV.setCellValueFactory(new PropertyValueFactory<>("Name"));
//        colGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
//        colPBID.setCellValueFactory(new PropertyValueFactory<>("DeprtID"));
//
//        try {
//            showData();
//        }
//        catch (IOException io){}
//        catch (SQLException e) {}
//
//        tbvNV.setItems(data);
//        tbvNV.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                getSelectedData();
//            }
//        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
//        resultSet = dbConn.getData("SELECT * FROM NHANVIEN");
//        data.removeAll(data);
//        while (resultSet.next()){
//            data.add(new Employees(
//                    resultSet.getString("MaNV"),
//                    resultSet.getString("TenNV"),
//                    resultSet.getString("Phai"),
//                    resultSet.getString("MaPB")
//            ));
//        }
//        resultSet.close();
//        dbConn.close();
    }

    @FXML
    //Hàm refresh xóa text
    public void refresh() {
//        txtNVID.setText("");
//        txtTenNV.setText("");
//        txtPBID.setText("");
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
//        Employees selectedRow = tbvNV.getSelectionModel().getSelectedItem();
//        txtNVID.setText(selectedRow.getMaNV());
//        txtTenNV.setText(selectedRow.getTenNV());
//        txtPBID.setText(selectedRow.getMaPhong());
    }
}
