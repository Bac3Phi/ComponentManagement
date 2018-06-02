package CM.Controllers;


import CM.Models.Bills;
import CM.Models.DataProvider;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class BillSearchController implements Initializable {
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
    private JFXRadioButton rdbtnBillID;

    @FXML
    private JFXRadioButton rdbtnCustomerID;

    @FXML
    private JFXRadioButton rdbtnEmployeesID;

    @FXML
    private JFXDatePicker dateCheckIn;

    @FXML
    private JFXDatePicker dateCheckOut;

    @FXML
    private AnchorPane paneSEARCH;

    public TableView<Bills> tbvSEARCH;
    public TableColumn<Bills, String> colBillID, colTaxCode, colCustomerID, colEmployeeID;
    public TableColumn<Bills, Date> colPublishDate;

    DataProvider dbConn;
    ObservableList<Bills> list;
    ResultSet resultSet;
    public BillManagerController pointer;

    @FXML
    void setBtnGETINFO(ActionEvent event) {

    }

    @FXML
    void setBtnPRINT(ActionEvent event) {

    }

    @FXML
    void setBtnREFRESH(ActionEvent event) {
        rdbtnBillID.setSelected(false);
        rdbtnEmployeesID.setSelected(false);
        rdbtnCustomerID.setSelected(false);
        list.removeAll(list);
        dateCheckOut.setValue(null);
        dateCheckIn.setValue(null);
    }

    public void setBtnSEARCH (ActionEvent event) {
        String[] str = {"MaHD", "MaNV", "MaKH"};
        try {
            if (rdbtnBillID.isSelected())
                searchData(str[0], txtSEARCH.getText());
            else if (rdbtnEmployeesID.isSelected())
                searchData(str[1], txtSEARCH.getText());
            else if (rdbtnCustomerID.isSelected())
                searchData(str[2], txtSEARCH.getText());
            else if (!rdbtnCustomerID.isSelected() && !rdbtnEmployeesID.isSelected() && !rdbtnCustomerID.isSelected()
                    && !dateCheckIn.getValue().equals(null) && !dateCheckOut.getValue().equals(null))
                searchDate(dateCheckIn.getValue().toString(), dateCheckOut.getValue().toString());
        } catch (SQLException e) {}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneSEARCH = new AnchorPane();
        list = FXCollections.observableArrayList();
        tbvSEARCH.setItems(list);
        //groupByRaidioButton();
        pointer = new BillManagerController();

        colBillID.setCellValueFactory(new PropertyValueFactory<>("BillID"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colTaxCode.setCellValueFactory(new PropertyValueFactory<>("TaxCode"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        tbvSEARCH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGETINFO.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Bills selectedRow = tbvSEARCH.getSelectionModel().getSelectedItem();
                        try {
                            pointer.data.removeAll(pointer.data);
                            pointer.data.add(selectedRow);
                            pointer.tbvBill.setItems(pointer.data);
                        }
                        catch (NullPointerException e) {}
                    }
                });
            }
        });
    }

    public void searchData (String field, String str) throws SQLException {
        String query = "SELECT * FROM HOADON WHERE " + field + " LIKE '%" + str +"%';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new Bills(
                    resultSet.getString("MaHD"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getString("MaSoThue"),
                    resultSet.getString("MaNV"),
                    resultSet.getString("MaKH")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    public void searchDate (String date1, String date2) throws SQLException {
        String query = "SELECT * FROM HOADON WHERE NgayLap >= '" + date1 +"' AND NgayLap <= '" + date2 + "';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new Bills(
                    resultSet.getString("MaHD"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getString("MaSoThue"),
                    resultSet.getString("MaNV"),
                    resultSet.getString("MaKH")
            ));
        }
        resultSet.close();
        dbConn.close();
    }
}
