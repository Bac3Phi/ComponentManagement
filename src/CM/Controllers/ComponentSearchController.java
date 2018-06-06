package CM.Controllers;

import CM.Models.Components;
import CM.Models.Customer;
import CM.Models.DataProvider;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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

import static CM.Controllers.CustomerController.tabPaneEx;

public class ComponentSearchController implements Initializable {
    @FXML
    private JFXTextArea txtSearch;

    @FXML
    private JFXButton btnGETINFO;

    @FXML
    private JFXRadioButton rdbtnComponentName, rdbtnComponentType, rdbtnComponentMaker, rdbtnComponentConfig,
                            rdbtnArea;

    @FXML
    private TableView<Components> tbvSearch;

    @FXML
    private TableColumn<Components, String> colComponentId, colComponentName, colComponentMaker, colComponentType,
            colComponentConfig, colComponentNumber, colComponentUnit, colComponentArea;

    ComponentManagerController pointer;
    DataProvider dbConn;
    ObservableList<Components> list;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnGETINFO.setDisable(true);
        dbConn = new DataProvider();
        list = FXCollections.observableArrayList();
        tbvSearch.setItems(list);
        groupByRaidioButton();
        pointer = new ComponentManagerController();

        colComponentId.setCellValueFactory(new PropertyValueFactory<>("ComponentID"));
        colComponentName.setCellValueFactory(new PropertyValueFactory<>("ComponentName"));
        colComponentMaker.setCellValueFactory(new PropertyValueFactory<>("CompMaker"));
        colComponentType.setCellValueFactory(new PropertyValueFactory<>("TypesOfComp"));
        colComponentConfig.setCellValueFactory(new PropertyValueFactory<>("CompInfo"));
        colComponentNumber.setCellValueFactory(new PropertyValueFactory<>("NumOfComp"));
        colComponentUnit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        colComponentArea.setCellValueFactory(new PropertyValueFactory<>("AreaName"));

        tbvSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGETINFO.setDisable(false);
            }
        });

        btnGETINFO.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tabPaneEx.getSelectionModel().select(0);
                Components selectedRow = tbvSearch.getSelectionModel().getSelectedItem();
                try {
                    pointer.data.removeAll(pointer.data);
                    pointer.data.add(selectedRow);
                    pointer.tbvComponent.setItems(pointer.data);
                }
                catch (NullPointerException e) {}
            }
        });
    }

    public void searchData (String field, String str) throws SQLException {
        String query = "SELECT MaMH, TenMH, HangSX, TenDV, CauHinh, TenLoai, TenKhu, SoLuong\n" +
                        "from MATHANG mh join LOAIMATHANG lmh on mh.MaLoai = lmh.MaLoai\n" +
                        "\tjoin DONVITINH dv on mh.MaDV = dv.MaDV join KHO k on mh.MaKhu = k.Makhu\n" +
                        "WHERE " + field + " LIKE '%" + str +"%';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new Components(
                    resultSet.getString("MaMH"),
                    resultSet.getString("TenMH"),
                    resultSet.getString("TenDV"),
                    resultSet.getString("HangSX"),
                    resultSet.getString("TenLoai"),
                    resultSet.getString("CauHinh"),
                    resultSet.getString("TenKhu"),
                    resultSet.getString("SoLuong")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    private void groupByRaidioButton(){
        final ToggleGroup group = new ToggleGroup();
        rdbtnComponentName.setToggleGroup(group);
        rdbtnComponentName.setSelected(true);
        rdbtnComponentMaker.setToggleGroup(group);
        rdbtnComponentConfig.setToggleGroup(group);
        rdbtnComponentType.setToggleGroup(group);
        rdbtnArea.setToggleGroup(group);
    }

    public void setBtnSEARCHdata (ActionEvent event) {
        String[] str = {"TenMH", "HangSX", "CauHinh", "TenLoai", "TenKhu"};
        try {
            if (rdbtnComponentName.isSelected())
                searchData(str[0], txtSearch.getText());
            else if (rdbtnComponentMaker.isSelected())
                searchData(str[1], txtSearch.getText());
            else if (rdbtnComponentConfig.isSelected())
                searchData(str[2], txtSearch.getText());
            else if (rdbtnComponentType.isSelected())
                searchData(str[3], txtSearch.getText());
            else if (rdbtnArea.isSelected())
                searchData(str[4], txtSearch.getText());
        } catch (SQLException e) {}
    }

    public void setBtnREFRESH(ActionEvent event) {
        txtSearch.setText(null);
        list.removeAll(list);
        rdbtnComponentName.setSelected(true);
        rdbtnComponentMaker.setSelected(false);
        rdbtnComponentConfig.setSelected(false);
        rdbtnComponentType.setSelected(false);
        rdbtnArea.setSelected(false);
    }
}
