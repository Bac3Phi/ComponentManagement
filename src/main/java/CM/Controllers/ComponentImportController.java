package CM.Controllers;

import CM.Functions.GenerateID;
import CM.Functions.SmileNotification;
import CM.Models.*;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import tray.notification.NotificationType;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ComponentImportController implements Initializable {
    @FXML
    private JFXTextField txtImPortComponentId;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXDatePicker dpPublishDate;

    @FXML
    private JFXComboBox<Employees> cbEmployeeName;

    @FXML
    private JFXTextField txtImportComponentAmount;

    @FXML
    private JFXButton btnPrint;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnRefresh;
    @FXML
    private JFXTextField txtNote;

    @FXML
    private JFXTextField txtNumOfComp;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtInforAmount;

    @FXML
    private JFXButton btnDeleteInfor;

    @FXML
    private JFXButton btnUpdateInfor;

    @FXML
    private JFXButton btnAddInfor;

    @FXML
    private JFXButton btnREFRESHinfo;
    @FXML
    private JFXTextField txtImportComponentInforId;

    @FXML
    private JFXTextField txtImportComponentInforImportId;

    @FXML
    private JFXComboBox<Components> cbComponentName;
    @FXML
    private TableView<ComponentImportInfo> tbvImportComponentInfo;
    @FXML
    private TableColumn<ComponentImportInfo, String> colImportComponentInforId, colImportComponentInforImportId,
            colComponentName, colNumOfComponent, colUnitPrice, colPrice, colNote, colInforAmount;
    @FXML
    public TableView<ComponentImport> tbvImportComponent;
    @FXML
    private TableColumn<ComponentImport, String> colImportComponentId, colOrderId, colEmployeeName, colPublishDate, colImportComponentAmount;

    DataProvider dbConn;
    public static ObservableList<ComponentImport> dataImport;
    ObservableList<ComponentImportInfo> dataInfor;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        btnAddInfor.setDisable(true);
        btnUpdateInfor.setDisable(true);
        btnDeleteInfor.setDisable(true);

        dbConn = new DataProvider();
        dataImport = FXCollections.observableArrayList();
        dataInfor = FXCollections.observableArrayList();
        tbvImportComponent.setEditable(false);
        tbvImportComponentInfo.setEditable(false);
        txtImPortComponentId.setEditable(false);
        txtImportComponentInforId.setEditable(false);
        txtImPortComponentId.setText(GenerateID.create("PhieuNhapHang","MaPN","PN"));

        setupTbv();
        setupTbvInfor();

        try {
            setupCbEmployeeName();
            setupCbComponentName();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvImportComponent.setItems(dataImport);
        tbvImportComponent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });

        tbvImportComponentInfo.setItems(dataInfor);
        tbvImportComponentInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedDataInfor();
            }
        });

        txtUnitPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("")){
                    txtPrice.setText("");
                    txtInforAmount.setText("");
                }
                else {
                    int value = (int) (Integer.parseInt(newValue)*1.1);
                    txtPrice.setText(String.valueOf(value));

                    if (!txtNumOfComp.getText().isEmpty()){
                        int total = Integer.parseInt(txtNumOfComp.getText()) * Integer.parseInt(newValue);
                        txtInforAmount.setText(String.valueOf(total));
                    }
                }
            }
        });

        txtNumOfComp.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.matches("")){
                    txtInforAmount.setText("");
                }
                else {
                    if (!txtUnitPrice.getText().isEmpty()){
                        int total = Integer.parseInt(txtUnitPrice.getText()) * Integer.parseInt(newValue);
                        txtInforAmount.setText(String.valueOf(total));
                    }
                }
            }
        });
    }

    private void setupCbEmployeeName() throws SQLException {
        ResultSet dataEmployeeName = dbConn.getData("SELECT MaNV, TenNV FROM NHANVIEN");
        ObservableList<Employees> employees = FXCollections.observableArrayList();
        employees.add(new Employees(null, "No selection", "",""));

        while (dataEmployeeName.next()){
            employees.add(new Employees(dataEmployeeName.getString("MaNV"), dataEmployeeName.getString("TenNV"), "", ""));
        }
        cbEmployeeName.setItems(employees);
        cbEmployeeName.setConverter(new StringConverter<Employees>() {
            @Override
            public String toString(Employees object) {
                return object.getEmployeeName();
            }

            @Override
            public Employees fromString(String string) {
                return null;
            }
        });
        cbEmployeeName.getSelectionModel().select(0);
    }

    private void setupCbComponentName() throws SQLException {
        ResultSet dataComponentName = dbConn.getData("SELECT MaMH, TenMH FROM MATHANG");
        ObservableList<Components> components = FXCollections.observableArrayList();
        components.add(new Components(null, "No selection", "", "", "", "", "", ""));

        while (dataComponentName.next()){
            components.add(new Components(dataComponentName.getString("MaMH"), dataComponentName.getString("TenMH"), "", "", "", "", "", ""));
        }
        cbComponentName.setItems(components);
        cbComponentName.setConverter(new StringConverter<Components>() {
            @Override
            public String toString(Components object) {
                return object.getComponentName();
            }

            @Override
            public Components fromString(String string) {
                return null;
            }
        });
        cbComponentName.getSelectionModel().select(0);
    }

    private void setupTbvInfor() {
        colImportComponentInforId.setCellValueFactory(new PropertyValueFactory<>("CompImportInforId"));
        colImportComponentInforImportId.setCellValueFactory(new PropertyValueFactory<>("CompImportInforImportId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
        colComponentName.setCellValueFactory(new PropertyValueFactory<>("ComponentName"));
        colNumOfComponent.setCellValueFactory(new PropertyValueFactory<>("NumOfComp"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("Note"));
        colInforAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    }

    private void setupTbv() {
        colImportComponentId.setCellValueFactory(new PropertyValueFactory<>("CompImportId"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colImportComponentAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("select MaPN, MaDDH, NgayLapPhieu, TenNV, TongTienPN\n" +
                "from PHIEUNHAPHANG PN join NHANVIEN NV on PN.MaNV = NV.MaNV");
        dataImport.removeAll(dataImport);
        while (resultSet.next()){
            dataImport.add(new ComponentImport(
                    resultSet.getString("MaPN"),
                    resultSet.getString("MaDDH"),
                    resultSet.getDate("NgayLapPhieu"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("TongTienPN")
            ));
        }
        resultSet.close();
        //dbConn.close();
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showDataInfor(String maPN) throws SQLException, IOException{
        resultSet = dbConn.getData("select MaCTPN, CTPN.SoLuong, DonGia, DonGiaBan, GhiChu, MaPN, TongTien, TenMH\n" +
                "from CHITIETPHIEUNHAP CTPN join MATHANG MH on CTPN.MaMH = MH.MaMH\n" +
                "where MaPN = '" + maPN + "'");
        dataInfor.removeAll(dataInfor);
        while (resultSet.next()){
            dataInfor.add(new ComponentImportInfo(
                    resultSet.getString("MaCTPN"),
                    resultSet.getString("MaPN"),
                    resultSet.getString("GhiChu"),
                    resultSet.getString("TenMH"),
                    resultSet.getString("SoLuong"),
                    resultSet.getString("DonGia"),
                    resultSet.getString("DonGiaBan"),
                    resultSet.getString("TongTien")
            ));
        }
        resultSet.close();
        //dbConn.close();
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        btnAddInfor.setDisable(false);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        txtImportComponentInforId.setText(GenerateID.create("CHITIETPHIEUNHAP","MaCTPN","CTPN"));
        ComponentImport selectedRow = tbvImportComponent.getSelectionModel().getSelectedItem();
        dpPublishDate.setValue(LocalDate.parse(selectedRow.getPublishDate().toString()));
        txtImPortComponentId.setText(selectedRow.getCompImportId());
        txtImportComponentInforImportId.setText(selectedRow.getCompImportId());
        txtOrderId.setText(selectedRow.getOrderId());
        txtImportComponentAmount.setText(selectedRow.getAmount());

        for (Employees employees:
                cbEmployeeName.getItems()) {
            if (employees.getEmployeeName().matches(selectedRow.getEmployeeName())){
                cbEmployeeName.getSelectionModel().select(employees);
                break;
            }
        }
        try {
            showDataInfor(selectedRow.getCompImportId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //lay thong tin du lieu duoc
    public void getSelectedDataInfor() {
        btnDeleteInfor.setDisable(false);
        btnUpdateInfor.setDisable(false);

        ComponentImportInfo selectedRow = tbvImportComponentInfo.getSelectionModel().getSelectedItem();
        txtImportComponentInforId.setText(selectedRow.getCompImportInforId());
        txtImportComponentInforImportId.setText(selectedRow.getCompImportInforImportId());
        txtNote.setText(selectedRow.getNote());
        txtNumOfComp.setText(selectedRow.getNumOfComp());
        txtUnitPrice.setText(selectedRow.getUnitPrice());
        txtPrice.setText(selectedRow.getPrice());
        txtInforAmount.setText(selectedRow.getAmount());

        for (Components components:
                cbComponentName.getItems()) {
            if (components.getComponentName().matches(selectedRow.getComponentName())){
                cbComponentName.getSelectionModel().select(components);
                break;
            }
        }
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ngaylap ="", manv = "", tongtien = "0", maddh = "";
        try {
            id = txtImPortComponentId.getText();
            ngaylap = dpPublishDate.getValue().toString();
            manv = cbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeID();
            maddh = txtOrderId.getText();

            if (txtImPortComponentId.getText().isEmpty()
                    || txtOrderId.getText().isEmpty() || dpPublishDate.getValue().toString().isEmpty()
                    || cbEmployeeName.getSelectionModel().getSelectedIndex() == 0)
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
            else {
                String[] dataInsert = {id, ngaylap, manv, tongtien, maddh};
                int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "PHIEUNHAPHANG");
                if (isInserted > 0) {
                    SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
                }
                else
                {
                    SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
                }
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Update dữ liệu
    public void updateData() {
        String id = "", ngaylap ="", manv = "", tongtien = "", maddh = "";
        try {
            id = txtImPortComponentId.getText();
            ngaylap = dpPublishDate.getValue().toString();
            manv = cbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeID();
            tongtien = txtImportComponentAmount.getText();
            maddh = txtOrderId.getText();

            if (txtImPortComponentId.getText().isEmpty() || txtImportComponentAmount.getText().isEmpty()
                    || txtOrderId.getText().isEmpty() || dpPublishDate.getValue().toString().isEmpty()
                    || cbEmployeeName.getSelectionModel().getSelectedIndex() == 0)
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            }
            else {
                String[] dataUpdate = {id, ngaylap, manv, tongtien, maddh};
                String[] colLabel = {"MaPN", "NgayLapPhieu", "MaNV", "TongTienPN", "MaDDH"};
                int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "PHIEUNHAPHANG");
                if (isUpdated > 0) {
                    SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
                }
                else
                {
                    SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
                }
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Delete dữ liệu
    public void deleteData() {
        if (txtImPortComponentId.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        else {
            String[] dataDelete = {txtImPortComponentId.getText()};
            int isDeletedInfor = dbConn.ExecuteSQLDelete(dataDelete, "CHITIETPHIEUNHAP", "MaPN");
            int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "PHIEUNHAPHANG", "MaPN");
            if (isDeleted > 0 && isDeletedInfor > 0) {
                SmileNotification.creatingNotification("Thông báo","Xóa dữ liệu thành công",NotificationType.SUCCESS);
            }
            else
            {
                SmileNotification.creatingNotification("Thông Báo","Xóa dữ liệu thất bại",NotificationType.INFORMATION);
            }
        }
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertDataInfor() {
        String id = "", soluong ="", dongia = "", dongiaban = "", ghichu = "", mapn = "", tongtieninfor = "", mamh = "";
        try {
            id = txtImportComponentInforId.getText();
            soluong = txtNumOfComp.getText();
            dongia = txtUnitPrice.getText();
            dongiaban = txtPrice.getText();
            ghichu = txtNote.getText();
            mapn = txtImportComponentInforImportId.getText();
            tongtieninfor = txtInforAmount.getText();
            mamh = cbComponentName.getSelectionModel().getSelectedItem().getComponentID();

            if (txtImportComponentInforId.getText().isEmpty() || txtNumOfComp.getText().isEmpty()
                    || txtUnitPrice.getText().isEmpty() || txtPrice.toString().isEmpty()
                    || txtNote.toString().isEmpty() || txtImportComponentInforImportId.toString().isEmpty()
                    || txtInforAmount.toString().isEmpty()
                    || cbComponentName.getSelectionModel().getSelectedIndex() == 0)
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
            else {
                String[] dataInsert = {id, soluong, dongia, dongiaban, ghichu, mapn, tongtieninfor, mamh};
                int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "CHITIETPHIEUNHAP");
                if (isInserted > 0) {
                    SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
                    try {
                        updateTongTien(mapn, getTongTien(mapn), tongtieninfor);
                        updateNumOfComp(mamh, getNumOfComp(mamh), soluong);
                        showData();
                    }
                    catch (SQLException e){}
                    catch (IOException io) {}
                }
                else
                {
                    SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
                }
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    private void updateNumOfComp(String mamh, String numOfComp, String soluong) {
        String newSoLuong = String.valueOf(Integer.parseInt(numOfComp) + Integer.parseInt(soluong));
        String[] dataUpdate = {mamh, newSoLuong};
        String[] colLabel = {"MaMH", "SoLuong"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "MATHANG");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
        }
    }

    private String getNumOfComp(String mamh) throws SQLException {
        resultSet = dbConn.getData("select SoLuong\n" +
                "from MATHANG where MaMH = '" + mamh + "'");
        while (resultSet.next()){
            return resultSet.getString("SoLuong");
        }
        return null;
    }

    private String getTongTien(String MaPN) throws SQLException {
        resultSet = dbConn.getData("select TongTienPN\n" +
                "from PHIEUNHAPHANG where MaPN = '" + MaPN + "'");
        while (resultSet.next()){
            return resultSet.getString("TongTienPN");
        }
        return null;
    }

    public void updateTongTien(String maPN, String tongTien, String tienCT){
        String newTongTien = String.valueOf(Long.parseLong(tongTien) + Long.parseLong(tienCT));
        String[] dataUpdate = {maPN, newTongTien};
        String[] colLabel = {"MaPN", "TongTienPN"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "PHIEUNHAPHANG");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
        }
    }

    //Update dữ liệu
    public void updateDataInfor() {
        String id = "", soluong ="", dongia = "", dongiaban = "", ghichu = "", mapn = "", tongtieninfor = "", mamh = "";
        try {
            id = txtImportComponentInforId.getText();
            soluong = txtNumOfComp.getText();
            dongia = txtUnitPrice.getText();
            dongiaban = txtPrice.getText();
            ghichu = txtNote.getText();
            mapn = txtImportComponentInforImportId.getText();
            tongtieninfor = txtInforAmount.getText();
            mamh = cbComponentName.getSelectionModel().getSelectedItem().getComponentID();

            if (txtImportComponentInforId.getText().isEmpty() || txtNumOfComp.getText().isEmpty()
                    || txtUnitPrice.getText().isEmpty() || txtPrice.toString().isEmpty()
                    || txtNote.toString().isEmpty() || txtImportComponentInforImportId.toString().isEmpty()
                    || txtInforAmount.toString().isEmpty()
                    || cbComponentName.getSelectionModel().getSelectedIndex() == 0)
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            }
            else {
                String[] dataUpdate = {id, soluong, dongia, dongiaban, ghichu, mapn, tongtieninfor, mamh};
                String[] colLabel = {"MaCTPN", "SoLuong", "DonGia", "DonGiaBan", "GhiChu", "MaPN", "TongTien", "MaMH"};
                int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "CHITIETPHIEUNHAP");
                if (isUpdated > 0) {
                    SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
                }
                else
                {
                    SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
                }
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Delete dữ liệu
    public void deleteDataInfor() {
        if (txtImportComponentInforId.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        else {
            String[] dataDelete = {txtImportComponentInforId.getText()};
            int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "CHITIETPHIEUNHAP", "MaCTPN");
            if (isDeleted > 0) {
                SmileNotification.creatingNotification("Thông báo","Xóa dữ liệu thành công",NotificationType.SUCCESS);
            }
            else
            {
                SmileNotification.creatingNotification("Thông Báo","Xóa dữ liệu thất bại",NotificationType.INFORMATION);
            }
        }
    }

    @FXML
    //Hàm refresh xóa text
    public void refresh() {
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        try {
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        txtImPortComponentId.setText("");
        txtOrderId.setText("");
        txtImportComponentAmount.setText("");
        dpPublishDate.setValue(null);
        cbEmployeeName.getSelectionModel().select(0);
        txtImPortComponentId.setText(GenerateID.create("PhieuNhapHang","MaPN","PN"));
    }

    @FXML
    //Hàm refresh xóa text
    public void refreshInfor(){
        btnDeleteInfor.setDisable(true);
        btnUpdateInfor.setDisable(true);

        try {
            showDataInfor(txtImportComponentInforImportId.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        txtImportComponentInforId.setText("");
        txtNote.setText("");
        txtNumOfComp.setText("");
        txtUnitPrice.setText("");
        txtPrice.setText("");
        txtInforAmount.setText("");

        cbComponentName.getSelectionModel().select(0);
        txtImportComponentInforId.setText(GenerateID.create("ChiTietPhieuNhap","MaCTPN","CTPN"));
    }

    @FXML
    void setBtnADD(ActionEvent event) {
        insertData();
        if (btnAdd.isPressed()) {
            refresh();
        }
    }

    @FXML
    void setBtnADDinfo(ActionEvent event) {
        insertDataInfor();
        if (btnAddInfor.isPressed()) {
            refreshInfor();
        }
    }

    @FXML
    void setBtnDELETE(ActionEvent event) {
        deleteData();
        if (btnDelete.isPressed()) {
            refresh();
        }
    }

    @FXML
    void setBtnDELETEinfo(ActionEvent event) {
        deleteDataInfor();
        if (btnDeleteInfor.isPressed()) {
            refreshInfor();
        }
    }

    @FXML
    void setBtnSEARCH(ActionEvent event) {

    }

    @FXML
    void setBtnUPDATE(ActionEvent event) {
        updateData();
        if (btnUpdate.isPressed()) {
            refresh();
        }
    }

    @FXML
    void setBtnUPDATEinfo(ActionEvent event) {
        updateDataInfor();
        if (btnUpdateInfor.isPressed()) {
            refreshInfor();
        }
    }

    public void setBtnPRINT(ActionEvent actionEvent) {
    }

    public void setBtnREFRESH(ActionEvent actionEvent) {
        refresh();
    }

    public void setBtnREFRESHinfo(ActionEvent actionEvent) {
        try {
            showDataInfor(txtImportComponentInforImportId.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        refreshInfor();
    }
}
