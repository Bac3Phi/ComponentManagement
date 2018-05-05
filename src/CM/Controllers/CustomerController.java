package CM.Controllers;

import CM.Main;
import CM.Models.Customer;
import CM.Models.DataProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    public TextField txtKHID, txtTenKH, txtDiaChi, txtEmail, txtSoDT;
    public Button btnSAVE, btnCANCEL, btnADD, btnUPDATE, btnDELETE;
    public TableView<Customer> tbvKH;
    public TableColumn<Customer, String> colKHID, colTenKH, colDiaChi, colEmail, colSoDT;
    public AnchorPane paneQLKH;

    private int flag = 0;
    DataProvider dbConn;
    ObservableList<Customer> data;
    ResultSet resultSet;

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM KHACHHANG");
        while (resultSet.next()){
            data.add(new Customer(
                    resultSet.getString("KHID"),
                    resultSet.getString("TENKH"),
                    resultSet.getString("DIACHI"),
                    resultSet.getString("email"),
                    resultSet.getString("SODIENTHOAI")
            ));
        }
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        dbConn = new DataProvider();
        paneQLKH = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvKH.setEditable(false);

        colKHID.setCellValueFactory(new PropertyValueFactory<>("MaKH"));
        colTenKH.setCellValueFactory(new PropertyValueFactory<>("TenKH"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colSoDT.setCellValueFactory(new PropertyValueFactory<>("SoDT"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvKH.setItems(data);
    }
}
