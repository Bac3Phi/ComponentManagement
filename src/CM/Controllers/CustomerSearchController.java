package CM.Controllers;

import CM.Models.Customer;
import CM.Models.DataProvider;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerSearchController implements Initializable {

    @FXML
    private JFXButton btnClear12;

    @FXML
    private JFXButton btnSEARCH;

    @FXML
    private JFXButton btnADD;

    @FXML
    private JFXButton btnGETINFO;

    @FXML
    private JFXTextArea txtSEARCH;

    @FXML
    private JFXRadioButton rdbtnCustomerID;

    @FXML
    private JFXRadioButton rdbtnCustomerName;

    @FXML
    private JFXRadioButton rdbtnCustomerAddress;

    @FXML
    private JFXRadioButton rdbtnCustomerEmail;

    @FXML
    private JFXRadioButton rdbtnCustomerPhone;

    @FXML
    private TableView<Customer> tbvSEARCH;

    @FXML
    private AnchorPane paneSEARCH;

    @FXML
    public TableColumn<Customer, String> colCustomerID, colCustomerName, colCustomerAddress, colCustomerEmail, colCustomerPhone;

    CustomerManagerController pointer;
    DataProvider dbConn;
    ObservableList<Customer> list;
    ResultSet resultSet;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneSEARCH = new AnchorPane();
        list = FXCollections.observableArrayList();
        tbvSEARCH.setItems(list);
        groupByRaidioButton();
        pointer = new CustomerManagerController();

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("CustomerEmail"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhoneNo"));
    }

    public void searchData (String field, String str) throws SQLException {
        String query = "SELECT * FROM KHACHHANG WHERE " + field + " LIKE '%" + str +"%';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new Customer(
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

    private void groupByRaidioButton(){
        final ToggleGroup group = new ToggleGroup();
        rdbtnCustomerID.setToggleGroup(group);
        rdbtnCustomerID.setSelected(true);
        rdbtnCustomerAddress.setToggleGroup(group);
        rdbtnCustomerEmail.setToggleGroup(group);
        rdbtnCustomerName.setToggleGroup(group);
        rdbtnCustomerPhone.setToggleGroup(group);
    }

    public void setBtnSEARCHdata (ActionEvent event) {
        String[] str = {"MaKH", "TenKH", "DiaChi", "Email", "SoDT"};
        try {
            if (rdbtnCustomerID.isSelected())
                searchData(str[0], txtSEARCH.getText());
            else if (rdbtnCustomerName.isSelected())
                searchData(str[1], txtSEARCH.getText());
            else if (rdbtnCustomerAddress.isSelected())
                searchData(str[2], txtSEARCH.getText());
            else if (rdbtnCustomerEmail.isSelected())
                searchData(str[3], txtSEARCH.getText());
            else if (rdbtnCustomerPhone.isSelected())
                searchData(str[4], txtSEARCH.getText());
        } catch (SQLException e) {}
    }

    public void setBtnGETINFO (ActionEvent event) {
        tbvSEARCH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Customer selectedRow = tbvSEARCH.getSelectionModel().getSelectedItem();
                try {
                    pointer.data.removeAll(pointer.data);
                    pointer.data.add(selectedRow);
                    pointer.tbvCustomer.setItems(pointer.data);
                }
                catch (NullPointerException e) {}
            }
        });
    }
}
