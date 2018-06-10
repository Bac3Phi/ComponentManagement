package CM.Controllers;

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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class BillManagerController implements Initializable {

    Alert alert;
    @FXML
    private JFXTextField txtBillID;

    @FXML
    private JFXTextField txtTaxCode;

    @FXML
    private JFXDatePicker dtPublishDate;

    @FXML
    private JFXComboBox<Employees> cbbEmployeeName;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtMoney;

    @FXML
    private JFXButton btnPRINT;

    @FXML
    private JFXButton btnSEARCH;

    @FXML
    private JFXButton btnDELETE;

    @FXML
    private JFXButton btnUPDATE;

    @FXML
    private JFXButton btnADD;

    @FXML
    private JFXButton btnREFRESH;
    @FXML
    private JFXTextField txtBillInfoID;

    @FXML
    private JFXTextField txtSellingPrice;

    @FXML
    private JFXTextField txtQuantities, txtSumMoney;

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

    public TableView<Bills> tbvBill;
    public TableColumn<Bills, String> colBillID, colTaxCode, colCustomerName, colEmployeeName;
    public TableColumn<Bills, Date> colPublishDate;
    public TableColumn<Bills, Integer> colSumMoney;

    public TableView<BillsInfo> tbvBillInfo;
    public TableColumn<BillsInfo, String> colBillInfoID, colSellingPrice, colComponentName, colMoney;
    public TableColumn<BillsInfo, Integer> colQuantities;
    public AnchorPane paneBill, paneBillInfo, paneBillManagement;

    DataProvider dbConn;
    public static ObservableList<Bills> data;
    ObservableList<BillsInfo> datainfo;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneBill = new AnchorPane();
        paneBillManagement = new AnchorPane();
        paneBillInfo = new AnchorPane();
        data = FXCollections.observableArrayList();
        datainfo = FXCollections.observableArrayList();
        btnUPDATE.setDisable(true);
        btnUPDATEinfo.setDisable(true);
        btnDELETE.setDisable(true);
        btnDELETEinfo.setDisable(true);
        txtMoney.setEditable(false);
        txtMoney.setText(String.valueOf(0));

        tbvBill.setEditable(false);
        tbvBillInfo.setEditable(false);

        colBillID.setCellValueFactory(new PropertyValueFactory<>("BillID"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colTaxCode.setCellValueFactory(new PropertyValueFactory<>("TaxCode"));
        colSumMoney.setCellValueFactory(new PropertyValueFactory<>("SumMoney"));
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));

        colBillInfoID.setCellValueFactory(new PropertyValueFactory<>("BillsInfoID"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("SellingPrice"));
        colQuantities.setCellValueFactory(new PropertyValueFactory<>("Quantities"));
        colComponentName.setCellValueFactory(new PropertyValueFactory<>("ComponentName"));
        colMoney.setCellValueFactory(new PropertyValueFactory<>("PurchaseMoney"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvBill.setItems(data);
        tbvBillInfo.setItems(datainfo);

        if (!data.isEmpty())
        {
            tbvBill.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        getSelectedData();
                        btnUPDATE.setDisable(false);
                        btnDELETE.setDisable(false);
                        String str = data.get(tbvBill.getSelectionModel().getSelectedIndex()).getBillID();
                        showDataInfo(str);
                    }
                    catch (IOException io){}
                    catch (SQLException e) {}
                    catch (NullPointerException e) {}
                }
            });
        }

        tbvBillInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    getSelectedDataInfo();
                    btnUPDATEinfo.setDisable(false);
                    btnDELETEinfo.setDisable(false);
                }
                catch(NullPointerException e) {}
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT MaHD, NgayLap, MaSoThue, TongTien, TenNV, MaKH FROM HOADON HD JOIN NHANVIEN NV ON HD.MaNV = NV.MaNV");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Bills(
                    resultSet.getString("MaHD"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getString("MaSoThue"),
                    resultSet.getInt("TongTien"),
                    resultSet.getString("MaNV"),
                    resultSet.getString("MaKH")
            ));
        }
        resultSet = dbConn.getData("SELECT * FROM NHANVIEN");
        ObservableList<Employees> list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            list.add(new Employees(
                    resultSet.getString("MaNV"),
                    resultSet.getString("TenNV"),"",""
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
        resultSet.close();
        dbConn.close();
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showDataInfo(String billid) throws SQLException, IOException{
        String query = "SELECT MaCTHD, DonGiaBan, SoLuong, MaHD, TenMH, TienThanhToan FROM CHITIETHOADON CTHD JOIN MATHANG MH ON CTHD.MaMH = MH.MaMH WHERE MaHD = '" + billid  +"'";
        resultSet = dbConn.getData(query);
        datainfo.removeAll(datainfo);
        while (resultSet.next()){
            datainfo.add(new BillsInfo(
                    resultSet.getString("MaCTHD"),
                    resultSet.getString("DonGiaBan"),
                    resultSet.getInt("SoLuong"),
                    resultSet.getString("MaHD"),
                    resultSet.getString("TenMH"),
                    resultSet.getInt("TienThanhToan")
            ));
        }
        resultSet = dbConn.getData("SELECT * FROM MATHANG");
        ObservableList<Components> list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            list.add(new Components(
                    resultSet.getString("MaMH"),
                    resultSet.getString("TenMH"),
                    "", "", "", "", "", ""
            ));
        }
        cbbComponentName.setItems(list);
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

    //Hàm refresh xóa text
    public void refresh() {
        txtBillID.setText("");
        txtBillInfoID.setText("");
        txtMoney.setText("");
        txtTaxCode.setText("");
        txtQuantities.setText("");
        txtSellingPrice.setText("");
        cbbComponentName.getSelectionModel().select(0);
        txtCustomerID.setText("");
        cbbEmployeeName.getSelectionModel().select(0);
    }

    //lay thong tin du lieu duoc HOADON
    public void getSelectedData() {
        Bills selectedRow = tbvBill.getSelectionModel().getSelectedItem();
        txtBillID.setText(selectedRow.getBillID());
        dtPublishDate.setValue(LocalDate.parse(selectedRow.getPublishDate().toString()));
        txtTaxCode.setText(selectedRow.getTaxCode());
        txtSumMoney.setText(String.valueOf(selectedRow.getSumMoney()));
        for (Employees ten:
             cbbEmployeeName.getItems()) {
            if (ten.getEmployeeName().matches(selectedRow.getEmployeeName()))
                cbbEmployeeName.getSelectionModel().select(ten);
        }
        txtCustomerID.setText(selectedRow.getCustomerName());
    }

    //lay thong tin du lieu duoc tu CHITIETHOADON
    public void getSelectedDataInfo() {
        btnUPDATEinfo.setDisable(false);
        BillsInfo selectedRow = tbvBillInfo.getSelectionModel().getSelectedItem();
        txtBillInfoID.setText(selectedRow.getBillsInfoID());
        txtSellingPrice.setText(selectedRow.getSellingPrice());
        txtQuantities.setText(String.valueOf(selectedRow.getQuantities()));
        for (Components ten:
                cbbComponentName.getItems()) {
            if (ten.getComponentName().matches(selectedRow.getComponentName()))
                cbbComponentName.getSelectionModel().select(ten);
        }
        if (selectedRow.getPurchaseMoney() == 0)
            txtMoney.setText(String.valueOf(calculate()));
        else if (selectedRow.getPurchaseMoney() != 0)
            txtMoney.setText(String.valueOf(selectedRow.getPurchaseMoney()));
    }

    private int calculate() {
        int result = 0;
        result = Integer.parseInt(txtQuantities.getText()) * Integer.parseInt(txtSellingPrice.getText());
        return result;
    }

    //Thêm dữ liệu vào bảng HOADON
    public void insertData() {
        String id = "", nglap = "", mst = "", sum = "", nvid = "", khid = "";
        try {
            id = txtBillID.getText();
            nglap = dtPublishDate.getValue().toString();
            mst = txtTaxCode .getText();
            sum = txtSumMoney.getText();
            nvid = cbbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeID();
            khid = txtCustomerID.getText();
            if (txtBillID.getText().isEmpty() || txtTaxCode.getText().isEmpty() || txtSumMoney.getText().isEmpty()
                    || txtMoney.getText().isEmpty() || cbbEmployeeName.getSelectionModel().getSelectedItem().equals(null)
                    || txtCustomerID.getText().isEmpty() || dtPublishDate.getValue().isEqual(null))
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, nglap, mst, sum, nvid, khid};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "HOADON");
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

    //Thêm dữ liệu vào bảng CHITIETHOADON
    public void insertDataInfo() {
        String id = "", dongia = "", soluong = "", hdid = "", mhid = "", tientt = "";
        try {
            id = txtBillInfoID.getText();
            dongia = txtSellingPrice.getText();
            soluong = txtQuantities.getText();
            hdid = txtBillID.getText();
            mhid = cbbComponentName.getSelectionModel().getSelectedItem().getComponentID();
            tientt = String.valueOf(calculate());
            txtMoney.setText(tientt);
            if (txtBillInfoID.getText().isEmpty() || txtSellingPrice.getText().isEmpty()
                    || txtQuantities.getText().isEmpty() || cbbComponentName.getSelectionModel().getSelectedItem().equals(null)
                    || txtBillID.getText().isEmpty() || txtBillID.getText().isEmpty())
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, dongia, soluong, hdid, mhid, tientt};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "CHITIETHOADON");
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

    //Update dữ liệu
    public void updateData() {
        String id = "", nglap = "", mst = "", sum = "", nvid = "", khid = "";
        try {
            id = txtBillID.getText();
            nglap = dtPublishDate.getValue().toString();
            mst = txtTaxCode .getText();
            sum = txtSumMoney.getText();
            nvid = cbbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeID();
            khid = txtCustomerID.getText();
            if (txtBillID.getText().isEmpty() || txtTaxCode.getText().isEmpty() || txtSumMoney.getText().isEmpty()
                    || txtMoney.getText().isEmpty() || cbbEmployeeName.getSelectionModel().getSelectedItem().equals(null)                    || txtCustomerID.getText().isEmpty() || dtPublishDate.getValue().isEqual(null))
            {
                alert = new Alert(Alert.AlertType.WARNING,
                        "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, nglap, mst, sum, nvid, khid};
        String[] colLabel = {"MaHD", "NgayLap", "MaSoThue", "TongTien", "MaNV", "MaKH"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "HOADON");
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

    //Update dữ liệu
    public void updateDataInfo() {
        String id = "", dongia = "", soluong = "", hdid = "", mhid = "", tientt = "";
        try {
            id = txtBillInfoID.getText();
            dongia = txtSellingPrice.getText();
            soluong = txtQuantities.getText();
            hdid = txtBillID.getText();
            mhid = cbbComponentName.getSelectionModel().getSelectedItem().getComponentID();
            tientt = String.valueOf(calculate());
            txtMoney.setText(tientt);
            if (txtBillInfoID.getText().isEmpty() || txtSellingPrice.getText().isEmpty()
                    || txtQuantities.getText().isEmpty() || cbbComponentName.getSelectionModel().getSelectedItem().equals(null)
                    || txtBillID.getText().isEmpty() || txtMoney.getText().isEmpty())
            {
                alert = new Alert(Alert.AlertType.WARNING,
                        "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, dongia, soluong, hdid, mhid, tientt};
        String[] colLabel = {"MaCTHD", "DonGiaBan", "SoLuong", "MaHD", "MaMH", "TienThanhToan"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "CHITIETHOADON");
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

    //Delete dữ liệu
    public void deleteData() {
        if (txtBillID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Please fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtBillID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "HOADON", "MaHD");
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

    //Delete dữ liệu
    public void deleteDataInfo() {
        if (txtBillInfoID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Please fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtBillInfoID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "CHITIETHOADON", "MaCTHD");
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
    public void setBtnEXPORT (ActionEvent event)throws Exception{
//        deleteData();
//        if (btnDELETE.isPressed()) {
//            refresh();
//        }
    }

    @FXML
    public void setBtnREFRESH (ActionEvent event)throws Exception{
//        deleteData();
//        if (btnDELETE.isPressed()) {
//            refresh();
//        }
    }

    @FXML
    public void setBtnSEARCH (ActionEvent event)throws Exception{
//        deleteData();
//        if (btnDELETE.isPressed()) {
//            refresh();
//        }
    }

    public void setBtnREFRESHinfo(ActionEvent actionEvent) {
    }

    public void setCalculateMoney(ActionEvent actionEvent) {
        if (!txtQuantities.getText().isEmpty() && !txtSellingPrice.getText().isEmpty())
            txtMoney.setText(String.valueOf(calculate()));
        else if (!txtQuantities.getText().isEmpty() || !txtSellingPrice.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Please fill in the previous blank !!!");
            alert.show();
        }
    }
}
