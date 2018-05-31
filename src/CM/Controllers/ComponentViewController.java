package CM.Controllers;

import CM.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ComponentViewController implements Initializable {
    Alert alert;
    @FXML
    public TextField txtComponentID, txtComponentConfig, txtComponentName, txtComponentMaker, txtNumOfComp;
    public ComboBox<TypeOfGoods> cbTypeName;
    public ComboBox<CompUnit> cbUnit;
    public ComboBox<Stock> cbAreaName;
    public Button btnAdd, btnDelete, btnUpdate, btnSearch, btnRefresh;
    public TableView<Components> tbvComponent;
    public TableColumn<Components, String> colComponentD, colComponentName, colComponentConfig, colTypeName, colComponentMaker, colAreaName, colUnit, colNumOfComp;
    public AnchorPane paneComponentManagement;

    DataProvider dbConn;
    ObservableList<Components> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        dbConn = new DataProvider();
        paneComponentManagement = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvComponent.setEditable(false);

        colComponentD.setCellValueFactory(new PropertyValueFactory<>("ComponentID"));
        colComponentName.setCellValueFactory(new PropertyValueFactory<>("ComponentName"));
        colComponentConfig.setCellValueFactory(new PropertyValueFactory<>("CompInfo"));
        colTypeName.setCellValueFactory(new PropertyValueFactory<>("TypesOfComp"));
        colComponentMaker.setCellValueFactory(new PropertyValueFactory<>("CompMaker"));
        colAreaName.setCellValueFactory(new PropertyValueFactory<>("AreaName"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        colNumOfComp.setCellValueFactory(new PropertyValueFactory<>("NumOfComp"));

        try {
            setupCbTypeName();
            setupCbUnit();
            setupCbAreaName();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvComponent.setItems(data);
        tbvComponent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });
    }

    private void setupCbUnit() throws SQLException {
        ResultSet dataTypeOfComp = dbConn.getData("SELECT * FROM DONVITINH");
        ObservableList<CompUnit> units = FXCollections.observableArrayList();
        units.add(new CompUnit(null, "No selection"));

        while (dataTypeOfComp.next()){
            units.add(new CompUnit(dataTypeOfComp.getString("MaDV"), dataTypeOfComp.getString("TenDV")));
        }
        cbUnit.setItems(units);
        cbUnit.setConverter(new StringConverter<CompUnit>() {
            @Override
            public String toString(CompUnit object) {
                return object.getUnitName();
            }

            @Override
            public CompUnit fromString(String string) {
                return null;
            }
        });
        cbUnit.getSelectionModel().select(0);
    }

    private void setupCbAreaName() throws SQLException {
        ResultSet dataTypeOfComp = dbConn.getData("SELECT * FROM KHO");
        ObservableList<Stock> areas = FXCollections.observableArrayList();
        areas.add(new Stock(null, "No selection"));

        while (dataTypeOfComp.next()){
            areas.add(new Stock(dataTypeOfComp.getString("MaKhu"), dataTypeOfComp.getString("TenKhu")));
        }
        cbAreaName.setItems(areas);
        cbAreaName.setConverter(new StringConverter<Stock>() {

            @Override
            public String toString(Stock object) {
                return object.getAreaName();
            }

            @Override
            public Stock fromString(String string) {
                return null;
            }
        });
        cbAreaName.getSelectionModel().select(0);
    }

    private void setupCbTypeName() throws SQLException {
        ResultSet dataTypeOfComp = dbConn.getData("SELECT * FROM LOAIMATHANG");
        ObservableList<TypeOfGoods> types = FXCollections.observableArrayList();
        types.add(new TypeOfGoods(null, "No selection"));

        while (dataTypeOfComp.next()){
            types.add(new TypeOfGoods(dataTypeOfComp.getString("MaLoai"), dataTypeOfComp.getString("TenLoai")));
        }
        cbTypeName.setItems(types);
        cbTypeName.setConverter(new StringConverter<TypeOfGoods>() {
            @Override
            public String toString(TypeOfGoods object) {
                return object.getTypesOfCompName();
            }

            @Override
            public TypeOfGoods fromString(String string) {
                return null;
            }
        });
        cbTypeName.getSelectionModel().select(0);
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("select MaMH, TenMH, TenDV, HangSX, HinhMH, TenLoai, CauHinh, TenKhu, SoLuong\n" +
                                        "from MATHANG mh join LOAIMATHANG lmh on mh.MaLoai = lmh.MaLoai\n" +
                                            "\tjoin DONVITINH dv on mh.MaDV = dv.MaDV join KHO k on mh.MaKhu = k.Makhu");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Components(
                    resultSet.getString("MaMH"),
                    resultSet.getString("TenMH"),
                    resultSet.getString("TenDV"),
                    resultSet.getString("HangSX"),
                    resultSet.getString("HinhMH"),
                    resultSet.getString("TenLoai"),
                    resultSet.getString("CauHinh"),
                    resultSet.getString("TenKhu"),
                    resultSet.getString("SoLuong")
            ));
        }
        resultSet.close();
        //dbConn.close();
    }

    @FXML
    //Hàm refresh xóa text
    public void refresh() {
        txtComponentID.setText("");
        txtComponentConfig.setText("");
        txtComponentName.setText("");
        txtComponentMaker.setText("");
        txtNumOfComp.setText("");

        cbAreaName.getSelectionModel().select(0);
        cbTypeName.getSelectionModel().select(0);
        cbUnit.getSelectionModel().select(0);
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        Components selectedRow = tbvComponent.getSelectionModel().getSelectedItem();
        txtComponentID.setText(selectedRow.getComponentID());
        txtComponentConfig.setText(selectedRow.getCompInfo());
        txtComponentName.setText(selectedRow.getComponentName());
        txtComponentMaker.setText(selectedRow.getCompMaker());
        txtNumOfComp.setText(selectedRow.getNumOfComp());

        for (TypeOfGoods type:
             cbTypeName.getItems()) {
            if (type.getTypesOfCompName().matches(selectedRow.getTypesOfComp())){
                cbTypeName.getSelectionModel().select(type);
                break;
            }
        }

        for (Stock area:
                cbAreaName.getItems()) {
            if (area.getAreaName().matches(selectedRow.getAreaName())){
                cbAreaName.getSelectionModel().select(area);
                break;
            }
        }

        for (CompUnit unit:
                cbUnit.getItems()) {
            if (unit.getUnitName().matches(selectedRow.getUnit())){
                cbUnit.getSelectionModel().select(unit);
                break;
            }
        }
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "", cauhinh = "", loai = "", makhu = "", hangsx= "", donvi = "", hinh= "", soluong= "0";
        try {
            id = txtComponentID.getText();
            ten = txtComponentName.getText();
            cauhinh = txtComponentConfig.getText();
            loai = cbTypeName.getSelectionModel().getSelectedItem().getTypesOfCompID();
            makhu = cbAreaName.getSelectionModel().getSelectedItem().getAreaCode();
            hangsx = txtComponentMaker.getText();
            donvi = cbUnit.getSelectionModel().getSelectedItem().getUnitCode();
            if (txtComponentID.getText().isEmpty() || txtComponentName.getText().isEmpty()
                    || txtComponentConfig.getText().isEmpty() || txtComponentMaker.getText().isEmpty()
                    || cbTypeName == null || cbAreaName == null || cbUnit == null)
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
            else {
                String[] dataInsert = {id, ten, hangsx, donvi, cauhinh, hinh, loai, makhu, soluong};
                int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "MATHANG");
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
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Update dữ liệu
    public void updateData() {
        String id = "", ten = "", cauhinh = "", loai = "", makhu = "", hangsx= "", donvi = "", hinh= "", soluong= "";
        try {
            id = txtComponentID.getText();
            ten = txtComponentName.getText();
            cauhinh = txtComponentConfig.getText();
            loai = cbTypeName.getSelectionModel().getSelectedItem().getTypesOfCompID();
            makhu = cbAreaName.getSelectionModel().getSelectedItem().getAreaCode();
            hangsx = txtComponentMaker.getText();
            donvi = cbUnit.getSelectionModel().getSelectedItem().getUnitCode();
            if (txtComponentID.getText().isEmpty() || txtComponentName.getText().isEmpty()
                    || txtComponentConfig.getText().isEmpty() || txtNumOfComp.getText().isEmpty()
                    || txtComponentMaker.getText().isEmpty() || cbTypeName == null
                    || cbAreaName == null || cbUnit == null)
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
            else {
                String[] dataUpdate = {id, ten, hangsx, donvi, cauhinh, hinh, loai, makhu};
                String[] colLabel = {"MaMH", "TenMH", "HangSX", "MaDV", "CauHinh", "HinhMH", "MaLoai", "MaKhu"};
                int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "MATHANG");
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
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Delete dữ liệu
    public void deleteData() {
        if (txtComponentID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Plese fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        else {
            String[] dataDelete = {txtComponentID.getText()};
            int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "MATHANG", "MaMH");
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
    }

    @FXML
    public void setBtnADD (ActionEvent event)throws Exception{
        insertData();
        if (btnAdd.isPressed()) {
            refresh();
        }
    }

    @FXML
    public void setBtnUPDATE (ActionEvent event)throws Exception{
        updateData();
        if (btnUpdate.isPressed()) {
            refresh();
        }
    }

    @FXML
    public void setBtnDELETE (ActionEvent event)throws Exception{
        deleteData();
        if (btnDelete.isPressed()) {
            refresh();
        }
    }

    @FXML
    public void setBtnSEARCH (ActionEvent event)throws Exception{

    }

    public void setBtnREFRESH(ActionEvent actionEvent) {
        refresh();
    }
}