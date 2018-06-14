package CM.Controllers;

import CM.Models.ComponentImport;
import CM.Models.Customer;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ComponentImportSearchController implements Initializable {

    @FXML
    private JFXTextArea txtSearch;

    @FXML
    private JFXRadioButton rdbtnImportComponentId, rdbtnOrderId;

    @FXML
    private JFXDatePicker dateCheckIn, dateCheckOut;

    @FXML
    private TableView<ComponentImport> tbvSearch;

    @FXML
    private TableColumn<ComponentImport, String> colImportComponentId, colOrderId, colEmployeeName, colPublishDate,
            colAmount;

    @FXML
    private JFXButton btnSearch, btnGetInfo, btnRefresh;

    DataProvider dbConn;
    ObservableList<ComponentImport> list;
    ResultSet resultSet;
    ComponentImportController pointer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        list = FXCollections.observableArrayList();
        tbvSearch.setItems(list);
        groupByRaidioButton();
        rdbtnOrderId.setSelected(false);
        rdbtnImportComponentId.setSelected(false);

        colImportComponentId.setCellValueFactory(new PropertyValueFactory<>("CompImportId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));

        tbvSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGetInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        ComponentImport selectedRow = tbvSearch.getSelectionModel().getSelectedItem();
                        try {
                            pointer.dataImport.removeAll(pointer.dataImport);
                            pointer.dataImport.add(selectedRow);
                            pointer.tbvImportComponent.setItems(pointer.dataImport);
                        }
                        catch (NullPointerException e) {}
                    }
                });
            }
        });
    }

    public void searchData (String field, String str) throws SQLException {
        String query = "SELECT MaPN, MaDDH, NgayLapPhieu, TenNV, TongTienPN" +
                " FROM PHIEUNHAPHANG PN join NHANVIEN NV on PN.MaNV = NV.MaNV" +
                " WHERE " + field + " LIKE '%" + str +"%';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new ComponentImport(
                    resultSet.getString("MaPN"),
                    resultSet.getString("MaDDH"),
                    resultSet.getDate("NgayLapPhieu"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("TongTienPN")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    public void searchDate (String date1, String date2) throws SQLException {
        String query = "SELECT PN.MaPN, PN.MaDDH, PN.NgayLapPhieu, NV.TenNV, PN.TongTienPN FROM PHIEUNHAPHANG PN JOIN NHANVIEN NV ON NV.MaNV = PN.MaNV WHERE NgayLapPhieu >= '" + date1 +"' AND NgayLapPhieu <= '" + date2 + "';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new ComponentImport(
                    resultSet.getString("MaPN"),
                    resultSet.getString("MaDDH"),
                    resultSet.getDate("NgayLapPhieu"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("TongTienPN")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    private void groupByRaidioButton(){
        final ToggleGroup group = new ToggleGroup();
        rdbtnImportComponentId.setToggleGroup(group);
        rdbtnImportComponentId.setSelected(true);
        rdbtnOrderId.setToggleGroup(group);
    }

    public void setBtnSEARCHdata (ActionEvent event) {
        String[] str = {"MaPN", "MaDDH"};
        try {
            if (rdbtnImportComponentId.isSelected())
                searchData(str[0], txtSearch.getText());
            else if (rdbtnOrderId.isSelected())
                searchData(str[1], txtSearch.getText());
            else if (!rdbtnImportComponentId.isSelected() && !rdbtnOrderId.isSelected()
                    && !dateCheckOut.getValue().equals(null) && !dateCheckIn.getValue().equals(null))
                searchDate(dateCheckIn.getValue().toString(), dateCheckOut.getValue().toString());
        } catch (SQLException e) {}
    }

    public void setBtnREFRESH(ActionEvent actionEvent) {
        txtSearch.setText("");
        rdbtnImportComponentId.setSelected(false);
        for ( int i = 0; i<tbvSearch.getItems().size(); i++) {
            tbvSearch.getItems().clear();
        }
        dateCheckIn.setValue(null);
        dateCheckOut.setValue(null);
    }
}
