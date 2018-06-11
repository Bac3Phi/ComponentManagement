package CM.Controllers;

import CM.Models.ComponentOrder;
import CM.Models.ComponentOrderInFo;
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

public class ComponentOrderSearchController implements Initializable {

    @FXML
    private JFXButton btnPRINT;

    @FXML
    private JFXButton btnSEARCH;

    @FXML
    private JFXButton btnGETINFO;

    @FXML
    private JFXButton btnREFRESH;

    @FXML
    private AnchorPane paneOrderSearch;

    @FXML
    private JFXTextArea txtSEARCH;

    @FXML
    private JFXRadioButton rdbtnOrderID;

    @FXML
    private JFXRadioButton rdbtnProviderName;

    @FXML
    private JFXRadioButton rdbtnEmployeesName;

    @FXML
    private JFXDatePicker dateCheckIn;

    @FXML
    private JFXDatePicker dateCheckOut;

    @FXML
    private AnchorPane paneSEARCH;

    @FXML
    private TableView<ComponentOrder> tbvSEARCH;

    @FXML
    private TableColumn<ComponentOrder, String> colOrderID;

    @FXML
    private TableColumn<ComponentOrder, Date> colPublishDate;

    @FXML
    private TableColumn<ComponentOrder, String> colEmployeeName;

    @FXML
    private TableColumn<ComponentOrder, String> colProviderName;

    DataProvider dbConn;
    ObservableList<ComponentOrder> list;
    ResultSet resultSet;
    public ComponentOrderController pointer;

    @FXML
    void setBtnPRINT(ActionEvent event) {

    }

    @FXML
    void setBtnREFRESH(ActionEvent event) {
        rdbtnOrderID.setSelected(false);
        rdbtnEmployeesName.setSelected(false);
        rdbtnProviderName.setSelected(false);
        list.removeAll(list);
        dateCheckOut.setValue(null);
        dateCheckIn.setValue(null);
        txtSEARCH.setText("");
    }

    @FXML
    void setBtnSEARCH(ActionEvent event) {
        String[] str = {"MaDDH", "TenNV", "TenNCC"};
        try {
            if (rdbtnOrderID.isSelected())
                searchData(str[0], txtSEARCH.getText());
            else if (rdbtnEmployeesName.isSelected())
                searchData(str[1], txtSEARCH.getText());
            else if (rdbtnProviderName.isSelected())
                searchData(str[2], txtSEARCH.getText());
            else if (!rdbtnOrderID.isSelected() && !rdbtnEmployeesName.isSelected() && !rdbtnProviderName.isSelected()
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
        pointer = new ComponentOrderController();

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("CompOrderID"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colProviderName.setCellValueFactory(new PropertyValueFactory<>("ProviderName"));

        tbvSEARCH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGETINFO.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ComponentOrder selectedRow = tbvSEARCH.getSelectionModel().getSelectedItem();
                        try {
                            pointer.data.removeAll(pointer.data);
                            pointer.data.add(selectedRow);
                            pointer.tbvOrder.setItems(pointer.data);
                        }
                        catch (NullPointerException e) {}
                    }
                });
            }
        });
    }

    public void searchData (String field, String str) throws SQLException {
        String query = "SELECT DDH.MaDDH, DDH.NgayLap, NV.TenNV, NCC.TenNCC FROM DONDATHANG DDH JOIN NHANVIEN NV JOIN NHACUNGCAP NCC ON DDH.MaNV = NV.MaNV AND DDH.MaNCC = NCC.MaNCC WHERE " + field + " LIKE '%" + str +"%';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new ComponentOrder(
                    resultSet.getString("MaDDH"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("TenNCC")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    public void searchDate (String date1, String date2) throws SQLException {
        String query = "SELECT MaDDH, NgayLap, TenNV, TenNCC FROM DONDATHANG DDH JOIN NHANVIEN NV JOIN NHACUNGCAP NCC ON DDH.MaNV = NV.MaNV AND DDH.MaNCC = NCC.MaNCC WHERE NgayLap >= '" + date1 +"' AND NgayLap <= '" + date2 + "';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new ComponentOrder(
                    resultSet.getString("MaDDH"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("TenNCC")
            ));
        }
        resultSet.close();
        dbConn.close();
    }
}
