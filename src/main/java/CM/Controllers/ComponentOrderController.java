package CM.Controllers;

import CM.Functions.GenerateID;
import CM.Functions.SmileNotification;
import CM.Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import javafx.util.StringConverter;
import tray.notification.NotificationType;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ComponentOrderController implements Initializable {

    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXDatePicker dtDate;

    @FXML
    private JFXComboBox<Employees> cbbEmployeeName;

    @FXML
    private JFXComboBox<Providers> cbbProviderName;

    @FXML
    private JFXButton btnDELETE;

    @FXML
    private JFXButton btnUPDATE;

    @FXML
    private JFXButton btnADD;

    @FXML
    private JFXButton btnREFRESH;

    @FXML
    private JFXTextField txtOrderInfoID;

    @FXML
    private JFXTextField txtQuantities;

    @FXML
    private JFXComboBox<Components> cbbComponentName;

    @FXML
    private JFXButton btnDELETEinfo;

    @FXML
    private JFXButton btnUPDATEinfo;

    @FXML
    private JFXButton btnADDinfo;

    @FXML
    private JFXButton btnREFRESHinfo;

    public TableView<ComponentOrder> tbvOrder;
    public TableColumn<ComponentOrder, String> colOrderID, colEmployeeName, colProviderName;
    public TableColumn<ComponentOrder, Date> colDate;

    public TableView<ComponentOrderInFo> tbvOrderInfo;
    public TableColumn<ComponentOrderInFo, String> colOrderInfoID, colComponentName;
    public TableColumn<ComponentOrderInFo, Integer> colQuantities;
    public AnchorPane paneOrder, paneOrderInfo, paneOrderManagement;

    DataProvider dbConn;
    public static ObservableList<ComponentOrder> data;
    ObservableList<ComponentOrderInFo> datainfo;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneOrder = new AnchorPane();
        paneOrderManagement = new AnchorPane();
        paneOrderInfo = new AnchorPane();
        data = FXCollections.observableArrayList();
        datainfo = FXCollections.observableArrayList();
        btnUPDATE.setDisable(true);
        btnUPDATEinfo.setDisable(true);
        btnDELETE.setDisable(true);
        btnDELETEinfo.setDisable(true);
        btnADDinfo.setDisable(true);
        txtOrderID.setText(GenerateID.create("DONDATHANG","MaDDH","DDH")); //DDH001
        txtOrderInfoID.setText(GenerateID.create("CHITIETDONDATHANG","MaCTDDH","CTDDH")); //CTDDH001
        tbvOrder.setEditable(false);
        tbvOrderInfo.setEditable(false);
        txtQuantities.setDisable(true);
        txtOrderInfoID.setDisable(true);
        cbbComponentName.setDisable(true);

        txtOrderID.setEditable(false);
        txtOrderInfoID.setEditable(false);
        tbvOrder.setEditable(false);
        tbvOrderInfo.setEditable(false);

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("CompOrderID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colProviderName.setCellValueFactory(new PropertyValueFactory<>("ProviderName"));

        colOrderInfoID.setCellValueFactory(new PropertyValueFactory<>("CompOrderInfoID"));
        colQuantities.setCellValueFactory(new PropertyValueFactory<>("Quantities"));
        colComponentName.setCellValueFactory(new PropertyValueFactory<>("ComponentName"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvOrder.setItems(data);

        tbvOrder.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    getSelectedData();
                    btnUPDATE.setDisable(false);
                    btnDELETE.setDisable(false);
                    String str = data.get(tbvOrder.getSelectionModel().getSelectedIndex()).getCompOrderID();
                    showDataInfo(str);
                    tbvOrderInfo.setItems(datainfo);
                }
                catch (IOException io){}
                catch (SQLException e) {}
                catch (NullPointerException e) {}
            }
        });

        tbvOrderInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    getSelectedDataInfo();
                    btnUPDATEinfo.setDisable(false);
                    btnDELETEinfo.setDisable(false);
                }
                catch (NullPointerException e) {}
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT DDH.MaDDH, DDH.NgayLap, NV.TenNV, NCC.TenNCC FROM DONDATHANG DDH JOIN NHANVIEN NV JOIN NHACUNGCAP NCC ON DDH.MaNV = NV.MaNV AND DDH.MaNCC = NCC.MaNCC");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new ComponentOrder(
                    resultSet.getString("MaDDH"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("TenNCC")
            ));
        }

        resultSet = dbConn.getData("SELECT * FROM NHANVIEN");
        ObservableList<Employees> list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            list.add(new Employees(
                    resultSet.getString("MaNV"),
                    resultSet.getString("TenNV"), "", ""
            ));
        }
        cbbEmployeeName.setItems(list);
        cbbEmployeeName.setConverter(new StringConverter<Employees>() {
            @Override
            public String toString(Employees object) {
                return object.getEmployeeName();
            }

            @Override
            public Employees fromString(String string) {
                return null;
            }
        });

        resultSet = dbConn.getData("SELECT * FROM NHACUNGCAP");
        ObservableList<Providers> ds = FXCollections.observableArrayList();
        while (resultSet.next()) {
            ds.add(new Providers(
                    resultSet.getString("MaNCC"),
                    resultSet.getString("TenNCC"), "", "", ""
            ));
        }
        cbbProviderName.setItems(ds);
        cbbProviderName.setConverter(new StringConverter<Providers>() {
            @Override
            public String toString(Providers object) {
                return object.getProvidersName();
            }

            @Override
            public Providers fromString(String string) {
                return null;
            }
        });

        resultSet = dbConn.getData("SELECT * FROM MATHANG");
        ObservableList<Components> components = FXCollections.observableArrayList();
        while (resultSet.next()) {
            components.add(new Components(
                    resultSet.getString("MaMH"),
                    resultSet.getString("TenMH"), "", "", "", "", "", ""
            ));
        }
        cbbComponentName.setItems(components);
        cbbComponentName.setConverter(new StringConverter<Components>() {
            @Override
            public String toString(Components object) {
                return object.getComponentName();
            }

            @Override
            public Components fromString(String string) {
                return null;
            }
        });
        resultSet.close();
        dbConn.close();
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showDataInfo(String orderid) throws SQLException, IOException{
        String query = "SELECT CTDDH.MaCTDDH, CTDDH.SoLuong, MH.TenMH FROM CHITIETDONDATHANG CTDDH JOIN MATHANG MH ON CTDDH.MaMH = MH.MaMH WHERE MaDDH = '" + orderid  +"'";
        resultSet = dbConn.getData(query);
        datainfo.removeAll(datainfo);
        while (resultSet.next()){
            datainfo.add(new ComponentOrderInFo(
                    resultSet.getString("MaCTDDH"),
                    resultSet.getInt("SoLuong"),
                    "",
                    resultSet.getString("TenMH")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    //Hàm refresh xóa text
    public void refresh() {
        txtOrderID.setText("");
        txtOrderInfoID.setText("");
        cbbProviderName.getSelectionModel().select(0);
        txtQuantities.setText("");
        cbbComponentName.getSelectionModel().select(0);
        cbbEmployeeName.getSelectionModel().select(0);
        txtOrderID.setText(GenerateID.create("DonDatHang","MaDDH","DDH")); //MH001
        dtDate.setValue(null);
        try {
            showData();
        }catch (SQLException e) {}
        catch (IOException ex) {}
    }

    //lay thong tin du lieu duoc DONDATHANG
    public void getSelectedData() {
        txtQuantities.setDisable(false);
        txtOrderInfoID.setDisable(false);
        cbbComponentName.setDisable(false);
        ComponentOrder selectedRow = tbvOrder.getSelectionModel().getSelectedItem();
        txtOrderID.setText(selectedRow.getCompOrderID());
        dtDate.setValue(LocalDate.parse(selectedRow.getPublishDate().toString()));
        for (Employees ten:
                cbbEmployeeName.getItems()) {
            if (ten.getEmployeeName().matches(selectedRow.getEmployeeName()))
                cbbEmployeeName.getSelectionModel().select(ten);
        }

        for (Providers ten:
                cbbProviderName.getItems()) {
            if (ten.getProvidersName().matches(selectedRow.getProviderName()))
                cbbProviderName.getSelectionModel().select(ten);
        }
        txtOrderInfoID.setText(GenerateID.create("ChiTietDonDatHang","MaCTDDH","CTDDH"));
        btnADDinfo.setDisable(false);
    }

    //lay thong tin du lieu duoc tu CHITIETHOADON
    public void getSelectedDataInfo() {
        ComponentOrderInFo selectedRow = tbvOrderInfo.getSelectionModel().getSelectedItem();
        txtOrderInfoID.setText(selectedRow.getCompOrderInfoID());
        txtQuantities.setText(String.valueOf(selectedRow.getQuantities()));
        for (Components ten:
                cbbComponentName.getItems()) {
            if (ten.getComponentName().matches(selectedRow.getComponentName()))
                cbbComponentName.getSelectionModel().select(ten);
        }
    }

    //Thêm dữ liệu vào bảng DONDATHANG
    public void insertData() {
        String id = "", nglap = "", nvid = "", nccid = "";
        try {
            id = txtOrderID.getText();
            nglap = dtDate.getValue().toString();
            nvid = cbbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeID();
            nccid = cbbProviderName.getSelectionModel().getSelectedItem().getProvidersID();
            if (txtOrderID.getText().isEmpty() || cbbProviderName.getSelectionModel().getSelectedItem().equals(null)
                    || cbbEmployeeName.getSelectionModel().getSelectedItem().equals(null) || dtDate.getValue().isEqual(null))
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, nglap, nvid, nccid};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "DONDATHANG");
        if (isInserted > 0) {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
        }
        try {
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    //Thêm dữ liệu vào bảng CHITIETDONDATHANG
    public void insertDataInfo() {
        String id = "", soluong = "", ddhid = "", mhid = "";
        try {
            id = txtOrderInfoID.getText();
            soluong = txtQuantities.getText();
            ddhid = txtOrderID.getText();
            mhid = cbbComponentName.getSelectionModel().getSelectedItem().getComponentID();
            if (txtOrderInfoID.getText().isEmpty() || cbbComponentName.getSelectionModel().getSelectedItem().equals(null)
                    || txtOrderID.getText().isEmpty() || dtDate.getValue().isEqual(null))
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, soluong, ddhid, mhid};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "CHITIETDONDATHANG");
        if (isInserted > 0) {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
        }
        try {
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    //Update dữ liệu DONDATHANG
    public void updateData() {
        String id = "", nglap = "", nvid = "", nccid = "";
        try {
            id = txtOrderID.getText();
            nglap = dtDate.getValue().toString();
            nvid = cbbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeID();
            nccid = cbbProviderName.getSelectionModel().getSelectedItem().getProvidersID();
            if (txtOrderID.getText().isEmpty() || cbbEmployeeName.getSelectionModel().getSelectedItem().equals(null)
                    || cbbProviderName.getSelectionModel().getSelectedItem().equals(null) || dtDate.getValue().isEqual(null))
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, nglap, nvid, nccid};
        String[] colLabel = {"MaDDH", "NgayLap", "MaNV", "MaNCC"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "DONDATHANG");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
        }
        try {
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    //Update dữ liệu CHITIETDONDATHANG
    public void updateDataInfo() {
        String id = "", soluong = "", ddhid = "", mhid = "";
        try {
            id = txtOrderInfoID.getText();
            soluong = dtDate.getValue().toString();
            ddhid = txtOrderID.getText();
            mhid = cbbComponentName.getSelectionModel().getSelectedItem().getComponentID();
            if (txtOrderInfoID.getText().isEmpty() || cbbComponentName.getSelectionModel().getSelectedItem().equals(null)
                    || txtOrderID.getText().isEmpty() || dtDate.getValue().isEqual(null))
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, soluong, ddhid, mhid};
        String[] colLabel = {"MaCTDDH", "SoLuong", "MaDDH", "MaMH"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "CHITIETDONDATHANG");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
        }
        try {
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    //Delete dữ liệu DONDATHANG
    public void deleteData() {
        if (txtOrderID.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        String[] dataDelete = {txtOrderID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "DONDATHANG", "MaDDH");
        if (isDeleted > 0) {
            SmileNotification.creatingNotification("Thông báo","Xóa dữ liệu thành công",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông Báo","Vui lòng hoàn thành 100%",NotificationType.INFORMATION);
        }
        try {
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    //Delete dữ liệu CHITIETDONDATHANG
    public void deleteDataInfo() {
        if (txtOrderInfoID.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        String[] dataDelete = {txtOrderInfoID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "CHITIETDONDATHANG", "MaCTDDH");
        if (isDeleted > 0) {
            SmileNotification.creatingNotification("Thông báo","Xóa dữ liệu thành công",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông Báo","Vui lòng hoàn thành 100%",NotificationType.INFORMATION);
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
    public void setBtnADDinfo (ActionEvent event)throws Exception{
        insertDataInfo();
        if (btnADDinfo.isPressed()) {
            refresh();
        }
        txtOrderInfoID.setDisable(false);
        cbbComponentName.setDisable(false);
        txtQuantities.setDisable(false);
    }

    @FXML
    public void setBtnUPDATEinfo (ActionEvent event)throws Exception{
        updateDataInfo();
        if (btnUPDATEinfo.isPressed()) {
            refresh();
        }
    }

    @FXML
    public void setBtnDELETEinfo (ActionEvent event)throws Exception{
        deleteDataInfo();
        if (btnDELETEinfo.isPressed()) {
            refresh();
        }
    }

    @FXML
    public void setBtnREFRESH(ActionEvent actionEvent) {
        refresh();
    }

    @FXML
    public void setBtnREFRESHinfo(ActionEvent actionEvent) {
        refreshInfo();
    }

    void refreshInfo() {
        txtQuantities.setText("");
        txtOrderInfoID.setText(GenerateID.create("ChiTietDonDatHang","MaCTDDH","CTDDH"));
        cbbComponentName.getSelectionModel().select(0);
        try {
            if (!txtOrderID.getText().isEmpty())
                showDataInfo(txtOrderID.getText());
        }catch (SQLException e) {}
        catch (IOException ex) {}
    }
}