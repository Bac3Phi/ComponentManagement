package CM.Controllers;

import CM.Models.ComponentImport;
import CM.Models.Customer;
import CM.Models.DataProvider;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ComponentImportSearchController implements Initializable {

    @FXML
    private JFXTextArea txtSearch;

    @FXML
    private JFXRadioButton rdbtnImportComponentId, rdbtnOrderId, rdbtnPublshDate;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        list = FXCollections.observableArrayList();
        tbvSearch.setItems(list);
        groupByRaidioButton();

        colImportComponentId.setCellValueFactory(new PropertyValueFactory<>("CompImportId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
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
                    resultSet.getString("NgayLapPhieu"),
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
        rdbtnPublshDate.setToggleGroup(group);
    }

    public void setBtnSEARCHdata (ActionEvent event) {
        String[] str = {"MaPN", "MaDDH", "NgayLapPhieu"};
        try {
            if (rdbtnImportComponentId.isSelected())
                searchData(str[0], txtSearch.getText());
            else if (rdbtnOrderId.isSelected())
                searchData(str[1], txtSearch.getText());
            else if (rdbtnPublshDate.isSelected())
                searchData(str[2], txtSearch.getText());
        } catch (SQLException e) {}
    }

    public void setBtnGETINFO(ActionEvent actionEvent) {
    }

    public void setBtnREFRESH(ActionEvent actionEvent) {
        txtSearch.setText("");
        rdbtnImportComponentId.setSelected(true);
        for ( int i = 0; i<tbvSearch.getItems().size(); i++) {
            tbvSearch.getItems().clear();
        }
    }
}
