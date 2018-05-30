package CM.Controllers;

import CM.Models.Bills;
import CM.Models.BillsInfo;
import CM.Models.DataProvider;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class BillController implements Initializable {
    Alert alert;
    @FXML
    public TextField txtBillID, txtBillInfoID, txtTaxCode, txtComponentID, txtSellingPrice,
                    txtQuantities, txtCustomerID, txtEmployeeID, txtMoney;
    public Button btnEXPORT, btnADD, btnUPDATE, btnDELETE, btnADDinfo, btnUPDATEinfo,
                    btnDELETEinfo, btnREFRESH, btnSEARCH;
    public ComboBox<String> cbbBillType;
    public DatePicker dtPublishDate;

    public TableView<Bills> tbvBill;
    public TableColumn<Bills, String> colBillID, colTaxCode, colMoney, colBillType,
                                        colCustomerID, colEmployeeID;
    public TableColumn<Bills, Date> colPublishDate;

    public TableView<BillsInfo> tbvBillInfo;
    public TableColumn<BillsInfo, String> colBillInfoID, colSellingPrice, colComponentID;
    public TableColumn<BillsInfo, Integer> colQuantities;
    public AnchorPane paneBill, paneBillInfo, paneBillManagement;

    DataProvider dbConn;
    ObservableList<Bills> data;
    ObservableList<BillsInfo> datainfo;
    ObservableList<String> list;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneBill = new AnchorPane();
        paneBillManagement = new AnchorPane();
        paneBillInfo = new AnchorPane();
        data = FXCollections.observableArrayList();
        datainfo = FXCollections.observableArrayList();
        list = FXCollections.observableArrayList("Bán lẻ", "Bán sỉ");
        cbbBillType.setItems(list);
        btnUPDATE.setVisible(false);

        tbvBill.setEditable(false);
        tbvBillInfo.setEditable(false);

        colBillID.setCellValueFactory(new PropertyValueFactory<>("BillID"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colTaxCode.setCellValueFactory(new PropertyValueFactory<>("TaxCode"));
        colMoney.setCellValueFactory(new PropertyValueFactory<>("PurchaseMoney"));
        colBillType.setCellValueFactory(new PropertyValueFactory<>("TypeOfBill"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));

        colBillInfoID.setCellValueFactory(new PropertyValueFactory<>("BillsInfoID"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("SellingPrice"));
        colQuantities.setCellValueFactory(new PropertyValueFactory<>("Quantities"));
        colComponentID.setCellValueFactory(new PropertyValueFactory<>("ComponentID"));

        try {
            showData();

        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvBill.setItems(data);
        tbvBillInfo.setItems(datainfo);
        tbvBill.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
                btnUPDATE.setVisible(true);
                try { showDataInfo(); }
                catch (IOException io){}
                catch (SQLException e) {}
            }
        });

        tbvBillInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedDataInfo();
                btnUPDATEinfo.setVisible(true);
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM HOADON");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Bills(
                    resultSet.getString("MaHD"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getString("MaSoThue"),
                    resultSet.getString("TienThanhToan"),
                    resultSet.getString("LoaiHD"),
                    resultSet.getString("MaNV"),
                    resultSet.getString("MaKH")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showDataInfo() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM CHITIETHOADON");
        datainfo.removeAll(datainfo);
        while (resultSet.next()){
            datainfo.add(new BillsInfo(
                    resultSet.getString("MaCTHD"),
                    resultSet.getString("DonGiaBan"),
                    resultSet.getInt("SoLuong"),
                    resultSet.getString("MaHD"),
                    resultSet.getString("MaMH")
            ));
        }
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
        txtComponentID.setText("");
        txtCustomerID.setText("");
        txtEmployeeID.setText("");
    }

    //lay thong tin du lieu duoc HOADON
    public void getSelectedData() {
        Bills selectedRow = tbvBill.getSelectionModel().getSelectedItem();
        txtBillID.setText(selectedRow.getBillID());
        dtPublishDate.setValue(LocalDate.parse(selectedRow.getPublishDate().toString()));
        txtTaxCode.setText(selectedRow.getTaxCode());
        txtMoney.setText(selectedRow.getPurchaseMoney());
        if (selectedRow.getTypeOfBill().contentEquals("Bán lẻ"))
            cbbBillType.getSelectionModel().select("Bán lẻ");
        else if (selectedRow.getTypeOfBill().contentEquals("Bán sỉ"))
            cbbBillType.getSelectionModel().select("Bán sỉ");
        txtEmployeeID.setText(selectedRow.getEmployeeID());
        txtCustomerID.setText(selectedRow.getCustomerID());
    }

    //lay thong tin du lieu duoc tu CHITIETHOADON
    public void getSelectedDataInfo() {
        BillsInfo selectedRow = tbvBillInfo.getSelectionModel().getSelectedItem();
        txtBillInfoID.setText(selectedRow.getBillsInfoID());
        txtSellingPrice.setText(selectedRow.getSellingPrice());
        txtQuantities.setText(String.valueOf(selectedRow.getQuantities()));
        txtComponentID.setText(selectedRow.getComponentID());
    }

    //Thêm dữ liệu vào bảng HOADON
    public void insertData() {
        String id = "", nglap = "", mst = "", tientt = "", loaihd = "", nvid = "", khid = "";
        try {
            id = txtBillID.getText();
            nglap = dtPublishDate.getValue().toString();
            mst = txtTaxCode .getText();
            tientt = txtMoney.getText();
            if (cbbBillType.getSelectionModel().getSelectedItem().contentEquals("Bán lẻ"))
                loaihd = "Bán lẻ";
            else if (cbbBillType.getSelectionModel().getSelectedItem().contentEquals("Bán sỉ"))
                loaihd = "Bán sỉ";
            nvid = txtEmployeeID.getText();
            khid = txtCustomerID.getText();
            if (txtBillID.getText().isEmpty() || txtTaxCode.getText().isEmpty()
                    || txtMoney.getText().isEmpty() || txtEmployeeID.getText().isEmpty()
                    || txtCustomerID.getText().isEmpty() || dtPublishDate.getValue().isEqual(null)
                    || cbbBillType.getSelectionModel().getSelectedItem().contentEquals(null))
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, nglap, mst, tientt, loaihd, nvid, khid};
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
        String id = "", dongia = "", soluong = "", hdid = "", mhid = "";
        try {
            id = txtBillInfoID.getText();
            dongia = txtSellingPrice.getText();
            soluong = txtQuantities.getText();
            hdid = txtBillID.getText();
            mhid = txtComponentID.getText();
            if (txtBillInfoID.getText().isEmpty() || txtSellingPrice.getText().isEmpty()
                    || txtQuantities.getText().isEmpty() || txtComponentID.getText().isEmpty()
                    || txtBillID.getText().isEmpty())
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, dongia, soluong, hdid, mhid};
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
        String id = "", nglap = "", mst = "", tientt = "", loaihd = "", nvid = "", khid = "";
        try {
            id = txtBillID.getText();
            nglap = dtPublishDate.getValue().toString();
            mst = txtTaxCode .getText();
            tientt = txtMoney.getText();
            if (cbbBillType.getSelectionModel().getSelectedItem().contentEquals("Bán lẻ"))
                loaihd = "Bán lẻ";
            else if (cbbBillType.getSelectionModel().getSelectedItem().contentEquals("Bán sỉ"))
                loaihd = "Bán sỉ";
            nvid = txtEmployeeID.getText();
            khid = txtCustomerID.getText();
            if (txtBillID.getText().isEmpty() || txtTaxCode.getText().isEmpty()
                    || txtMoney.getText().isEmpty() || txtEmployeeID.getText().isEmpty()
                    || txtCustomerID.getText().isEmpty() || dtPublishDate.getValue().isEqual(null)
                    || cbbBillType.getSelectionModel().getSelectedItem().contentEquals(null))
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
        String[] dataUpdate = {id, nglap, mst, tientt, loaihd, nvid, khid};
        String[] colLabel = {"MaHD", "NgayLap", "MaSoThue", "TienThanhToan", "LoaiHD", "MaNV", "MaKH"};
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
        String id = "", dongia = "", soluong = "", hdid = "", mhid = "";
        try {
            id = txtBillInfoID.getText();
            dongia = txtSellingPrice.getText();
            soluong = txtQuantities.getText();
            hdid = txtBillID.getText();
            mhid = txtComponentID.getText();
            if (txtBillInfoID.getText().isEmpty() || txtSellingPrice.getText().isEmpty()
                    || txtQuantities.getText().isEmpty() || txtComponentID.getText().isEmpty()
                    || txtBillID.getText().isEmpty())
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
        String[] dataUpdate = {id, dongia, soluong, hdid, mhid};
        String[] colLabel = {"MaCTHD", "DonGiaBan", "SoLuong", "MaHD", "MaMH"};
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
}
