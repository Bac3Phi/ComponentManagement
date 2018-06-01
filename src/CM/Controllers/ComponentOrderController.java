package CM.Controllers;

import CM.Models.ComponentOrder;
import CM.Models.ComponentOrderInFo;
import CM.Models.DataProvider;
import com.jfoenix.controls.JFXButton;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ComponentOrderController implements Initializable {
    Alert alert;
    @FXML
    private JFXTextField txtOrderID;

    @FXML
    private JFXDatePicker dtDate;

    @FXML
    private JFXTextField txtEmployeeID;

    @FXML
    private JFXTextField txtProviderID;

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
    private JFXTextField txtOrderInfoID;

    @FXML
    private JFXTextField txtQuantities;

    @FXML
    private JFXTextField txtComponentID;

    @FXML
    private JFXButton btnDELETEinfo;

    @FXML
    private JFXButton btnUPDATEinfo;

    @FXML
    private JFXButton btnADDinfo;

    @FXML
    private JFXButton btnREFRESHinfo;

    public TableView<ComponentOrder> tbvOrder;
    public TableColumn<ComponentOrder, String> colOrderID, colEmployeeID, colProviderID;
    public TableColumn<ComponentOrder, Date> colDate;

    public TableView<ComponentOrderInFo> tbvOrderInfo;
    public TableColumn<ComponentOrderInFo, String> colOrderInfoID, colComponentID;
    public TableColumn<ComponentOrderInFo, Integer> colQuantities;
    public AnchorPane paneOrder, paneOrderInfo, paneOrderManagement;

    DataProvider dbConn;
    ObservableList<ComponentOrder> data;
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
        btnUPDATE.setVisible(false);
        btnUPDATEinfo.setVisible(false);

        tbvOrder.setEditable(false);
        tbvOrderInfo.setEditable(false);

        colOrderID.setCellValueFactory(new PropertyValueFactory<>("CompOrderID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        colProviderID.setCellValueFactory(new PropertyValueFactory<>("ProviderID"));

        colOrderInfoID.setCellValueFactory(new PropertyValueFactory<>("CompOrderInfoID"));
        colQuantities.setCellValueFactory(new PropertyValueFactory<>("Quantities"));
        colComponentID.setCellValueFactory(new PropertyValueFactory<>("ComponentID"));

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
                    btnUPDATE.setVisible(true);
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
                    btnUPDATEinfo.setVisible(true);
                }
                catch (NullPointerException e) {}
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM DONDATHANG");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new ComponentOrder(
                    resultSet.getString("MaDDH"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getString("MaNV"),
                    resultSet.getString("MaNCC")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showDataInfo(String orderid) throws SQLException, IOException{
        String query = "SELECT * FROM CHITIETDONDATHANG WHERE MaDDH = '" + orderid  +"'";
        resultSet = dbConn.getData(query);
        datainfo.removeAll(datainfo);
        while (resultSet.next()){
            datainfo.add(new ComponentOrderInFo(
                    resultSet.getString("MaCTDDH"),
                    resultSet.getInt("SoLuong"),
                    resultSet.getString("MaDDH"),
                    resultSet.getString("MaMH")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    //Hàm refresh xóa text
    public void refresh() {
        txtOrderID.setText("");
        txtOrderInfoID.setText("");
        txtProviderID.setText("");
        txtQuantities.setText("");
        txtComponentID.setText("");
        txtEmployeeID.setText("");
    }

    //lay thong tin du lieu duoc HOADON
    public void getSelectedData() {
        ComponentOrder selectedRow = tbvOrder.getSelectionModel().getSelectedItem();
        txtOrderID.setText(selectedRow.getCompOrderID());
        dtDate.setValue(LocalDate.parse(selectedRow.getPublishDate().toString()));
        txtProviderID.setText(selectedRow.getProviderID());
        txtEmployeeID.setText(selectedRow.getEmployeeID());
    }

    //lay thong tin du lieu duoc tu CHITIETHOADON
    public void getSelectedDataInfo() {
        ComponentOrderInFo selectedRow = tbvOrderInfo.getSelectionModel().getSelectedItem();
        txtOrderInfoID.setText(selectedRow.getCompOrderInfoID());
        txtQuantities.setText(String.valueOf(selectedRow.getQuantities()));
        txtComponentID.setText(selectedRow.getComponentID());
    }

    //Thêm dữ liệu vào bảng DONDATHANG
    public void insertData() {
        String id = "", nglap = "", nvid = "", nccid = "";
        try {
            id = txtOrderID.getText();
            nglap = dtDate.getValue().toString();
            nvid = txtEmployeeID.getText();
            nccid = txtProviderID.getText();
            if (txtOrderID.getText().isEmpty() || txtProviderID.getText().isEmpty()
                    || txtEmployeeID.getText().isEmpty() || dtDate.getValue().isEqual(null))
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, nglap, nvid, nccid};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "DONDATHANG");
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

    //Thêm dữ liệu vào bảng CHITIETDONDATHANG
    public void insertDataInfo() {
        String id = "", soluong = "", ddhid = "", mhid = "";
        try {
            id = txtOrderInfoID.getText();
            soluong = txtQuantities.getText();
            ddhid = txtOrderID.getText();
            mhid = txtComponentID.getText();
            if (txtOrderInfoID.getText().isEmpty() || txtComponentID.getText().isEmpty()
                    || txtOrderID.getText().isEmpty() || dtDate.getValue().isEqual(null))
            {
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, soluong, ddhid, mhid};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "CHITIETDONDATHANG");
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

    //Update dữ liệu DONDATHANG
    public void updateData() {
        String id = "", nglap = "", nvid = "", nccid = "";
        try {
            id = txtOrderID.getText();
            nglap = dtDate.getValue().toString();
            nvid = txtEmployeeID.getText();
            nccid = txtProviderID.getText();
            if (txtOrderID.getText().isEmpty() || txtProviderID.getText().isEmpty()
                    || txtEmployeeID.getText().isEmpty() || dtDate.getValue().isEqual(null))
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
        String[] dataUpdate = {id, nglap, nvid, nccid};
        String[] colLabel = {"MaDDH", "NgayLap", "MaNV", "MaNCC"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "DONDATHANG");
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

    //Update dữ liệu CHITIETDONDATHANG
    public void updateDataInfo() {
        String id = "", soluong = "", ddhid = "", mhid = "";
        try {
            id = txtOrderInfoID.getText();
            soluong = dtDate.getValue().toString();
            ddhid = txtOrderID.getText();
            mhid = txtComponentID.getText();
            if (txtOrderInfoID.getText().isEmpty() || txtComponentID.getText().isEmpty()
                    || txtOrderID.getText().isEmpty() || dtDate.getValue().isEqual(null))
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
        String[] dataUpdate = {id, soluong, ddhid, mhid};
        String[] colLabel = {"MaCTDDH", "SoLuong", "MaDDH", "MaMH"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "CHITIETDONDATHANG");
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

    //Delete dữ liệu DONDATHANG
    public void deleteData() {
        if (txtOrderID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Please fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtOrderID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "DONDATHANG", "MaDDH");
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

    //Delete dữ liệu CHITIETDONDATHANG
    public void deleteDataInfo() {
        if (txtOrderInfoID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Please fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtOrderInfoID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "CHITIETDONDATHANG", "MaCTDDH");
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
    public void setBtnSEARCH (ActionEvent event)throws Exception{
//        deleteData();
//        if (btnDELETE.isPressed()) {
//            refresh();
//        }
    }

    public void setBtnREFRESH(ActionEvent actionEvent) {
    }

    public void setBtnREFRESHinfo(ActionEvent actionEvent) {
    }
}
