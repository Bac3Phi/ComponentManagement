package CM.Controllers;

import CM.Functions.GenerateID;
import CM.Functions.SmileNotification;
import CM.Models.*;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tray.notification.NotificationType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

public class ReportInventoriesController implements Initializable {

    @FXML
    private AnchorPane paneReport;

    @FXML
    private Label lbReportID;

    @FXML
    private JFXTextField txtReportID;

    @FXML
    private Label lbEmployeeName;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private Label lbReportDate;

    @FXML
    private JFXDatePicker dtDate;

    @FXML
    private Label lbSumImport;

    @FXML
    private JFXTextField txtSumImport;

    @FXML
    private Label lbSumSell;

    @FXML
    private JFXTextField txtSumSell;

    @FXML
    private Label lbSumStock;

    @FXML
    private JFXTextField txtSumStock;

    @FXML
    private Label lbReport;

    @FXML
    private Label lbMonth;

    @FXML
    private JFXComboBox<Integer> cbbMonth;

    @FXML
    private TableView<InventoriesReport> tbvReport;

    @FXML
    private TableColumn<InventoriesReport, String> colReportID;

    @FXML
    private TableColumn<InventoriesReport, Date> colReportDate;

    @FXML
    private TableColumn<InventoriesReport, Integer> colMonth;

    @FXML
    private TableColumn<InventoriesReport, String> colEmployeeName;

    @FXML
    private TableColumn<InventoriesReport, Integer> colSumImport;

    @FXML
    private TableColumn<InventoriesReport, Integer> colSumSell;

    @FXML
    private TableColumn<InventoriesReport, Integer> colSumRemain;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnPrint;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private TableView<InventoriesReportInfo> tbvReportInfo;

    @FXML
    private TableColumn<InventoriesReportInfo, String> colReportInfoID;

    @FXML
    private TableColumn<InventoriesReportInfo, String> colComponentName;

    @FXML
    private TableColumn<InventoriesReportInfo, Integer> colImport;

    @FXML
    private TableColumn<InventoriesReportInfo, Integer> colSell;

    @FXML
    private TableColumn<InventoriesReportInfo, Integer> colRemain;

    DataProvider dbConn;
    ObservableList<InventoriesReport> data;
    ObservableList<InventoriesReportInfo> datainfo;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneReport = new AnchorPane();
        data = FXCollections.observableArrayList();
        datainfo = FXCollections.observableArrayList();
        tbvReport.setEditable(false);
        tbvReportInfo.setEditable(false);
        ObservableList<Integer> str = FXCollections.observableArrayList(); str.add(0);
        str.add(1); str.add(2); str.add(3); str.add(4); str.add(5); str.add(6); str.add(7); str.add(8);
        str.add(9); str.add(10); str.add(11); str.add(12);
        cbbMonth.setItems(str);

        txtReportID.setEditable(false);
        txtReportID.setText(GenerateID.create("BaoCaoHangTon","MaBCHT","BCHT"));

        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colReportDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("ReportMonth"));
        colReportID.setCellValueFactory(new PropertyValueFactory<>("ReportID"));
        colSumImport.setCellValueFactory(new PropertyValueFactory<>("SumImport"));
        colSumSell.setCellValueFactory(new PropertyValueFactory<>("SumSell"));
        colSumRemain.setCellValueFactory(new PropertyValueFactory<>("SumStock"));

        colComponentName.setCellValueFactory(new PropertyValueFactory<>("ComponentName"));
        colReportInfoID.setCellValueFactory(new PropertyValueFactory<>("ReportInfoID"));
        colImport.setCellValueFactory(new PropertyValueFactory<>("Import"));
        colSell.setCellValueFactory(new PropertyValueFactory<>("Selling"));
        colRemain.setCellValueFactory(new PropertyValueFactory<>("Stock"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvReport.setItems(data);
        tbvReportInfo.setItems(datainfo);
        tbvReport.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });

        btnRefresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                refresh();
            }
        });

        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                insertData();
                //insertDataInfo();
            }
        });

        btnUpdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                updateData();
            }
        });

    }


    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException {
        resultSet = dbConn.getData("SELECT BCHT.MaBCHT, BCHT.NgayLap, BCHT.Thang, NV.TenNV, SUM(CTBC.LuongNhapBĐ) AS TongNhap, SUM(CTBC.LuongBan) AS TongBan, SUM(CTBC.LuongTon) AS TongTon FROM BAOCAOHANGTON BCHT JOIN NHANVIEN NV JOIN CHITIETBAOCAOHANGTON CTBC ON BCHT.MaNV = NV.MaNV AND CTBC.MaBCHT = BCHT.MaBCHT;");
        data.removeAll(data);
        while(resultSet.next()) {
            data.add(new InventoriesReport(
                    resultSet.getString("MaBCHT"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getInt("Thang"),
                    resultSet.getString("TenNV"),
                    resultSet.getInt("TongNhap"),
                    resultSet.getInt("TongBan"),
                    resultSet.getInt("TongTon")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    //Hàm refresh xóa text
    public void refresh() {
        txtReportID.setText("");
        txtEmployeeName.setText("");
        txtSumImport.setText("");
        txtSumSell.setText("");
        txtSumStock.setText("");
        cbbMonth.getSelectionModel().select(0);
        dtDate.setValue(null);
        txtReportID.setText(GenerateID.create("BaoCaoHangTon","MaBCHT","BCHT"));
        try {
            showData();
        } catch (SQLException e) {}
        catch (IOException ex) {}
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        InventoriesReport selectedRow = tbvReport.getSelectionModel().getSelectedItem();
        txtReportID.setText(selectedRow.getReportID());
        dtDate.setValue(LocalDate.parse(selectedRow.getPublishDate().toString()));
        txtSumStock.setText(String.valueOf(selectedRow.getSumStock()));
        txtSumSell.setText(String.valueOf(selectedRow.getSumSell()));
        txtSumImport.setText(String.valueOf(selectedRow.getSumImport()));
        txtEmployeeName.setText(selectedRow.getEmployeeName());
        cbbMonth.getSelectionModel().select(selectedRow.getReportMonth());
        try {
            showDataInfo(selectedRow.getReportID());
        } catch (SQLException e) {}
//        String str = selectedRow.getPublishDate().toString();
//        cbbYear.getSelectionModel().getSelectedItem().equals(String.valueOf(str.substring(0,4)));
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showDataInfo(String orderid) throws SQLException{
        String query = "SELECT CTBCHT.MaCTBC, MH.TenMH, SUM(CTPN.SoLuong) AS LuongNhapBĐ, SUM(CTHD.SoLuong) AS LuongBan, SUM(MH.SoLuong) AS LuongTon FROM CHITIETBAOCAOHANGTON CTBCHT JOIN MATHANG MH JOIN CHITIETPHIEUNHAP CTPN JOIN CHITIETHOADON CTHD ON CTBCHT.MaMH = MH.MaMH AND CTPN.MaMH = CTBCHT.MaMH AND CTHD.MaMH = CTBCHT.MaMH WHERE MaBCHT = '" + orderid + "'" + "GROUP BY TenMH;";
        resultSet = dbConn.getData(query);
        datainfo.removeAll(datainfo);
        while (resultSet.next()){
            datainfo.add(new InventoriesReportInfo(
                    resultSet.getString("MaCTBC"),
                    resultSet.getString("TenMH"),
                    resultSet.getInt("LuongNhapBĐ"),
                    resultSet.getInt("LuongBan"),
                    resultSet.getInt("LuongTon"),
                    ""
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    //Thêm dữ liệu vào bảng DONDATHANG
    public void insertData() {
        String id = "", nglap = "", thang = "", nvid = "", nhap = "", ban = "", ton = "";
        try {
            id = txtReportID.getText();
            nglap = dtDate.getValue().toString();
            thang = cbbMonth.getValue().toString();
            resultSet = dbConn.getData("SELECT MaNV FROM NHANVIEN WHERE TenNV = N'" + txtEmployeeName.getText() + "';");
            ObservableList<Employees> ds = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ds.add(new Employees(
                        resultSet.getString("MaNV"),
                        "", "", ""
                ));
            }
            nvid = ds.get(0).getEmployeeID();
            nhap = txtSumImport.getText();
            ton = txtSumStock.getText();
            ban = txtSumSell.getText();
            if (txtReportID.getText().isEmpty() || txtEmployeeName.getText().isEmpty()
                    || txtSumStock.getText().isEmpty() || txtSumSell.getText().isEmpty()
                    || txtSumImport.getText().isEmpty() || dtDate.getValue().isEqual(null)
                    || cbbMonth.getSelectionModel().getSelectedItem().equals(0))
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
            resultSet.close();
            dbConn.close();
        } catch (SQLException ex) {}
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, nglap, thang, nhap, ton, ban, nvid};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "BAOCAOHANGTON");
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
        String id = "", nglap = "", thang = "", nvid = "", nhap = "", ban = "", ton = "";
        try {
            id = txtReportID.getText();
            nglap = dtDate.getValue().toString();
            thang = cbbMonth.getValue().toString();
            resultSet = dbConn.getData("SELECT MaNV FROM NHANVIEN WHERE TenNV = N'" + txtEmployeeName.getText() + "';");
            ObservableList<Employees> ds = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ds.add(new Employees(
                        resultSet.getString("MaNV"),
                        "", "", ""
                ));
            }
            nvid = ds.get(0).getEmployeeID();
            nhap = txtSumImport.getText();
            ton = txtSumStock.getText();
            ban = txtSumSell.getText();
            if (txtReportID.getText().isEmpty() || txtEmployeeName.getText().isEmpty()
                    || txtSumStock.getText().isEmpty() || txtSumSell.getText().isEmpty()
                    || txtSumImport.getText().isEmpty() || dtDate.getValue().isEqual(null)
                    || cbbMonth.getSelectionModel().getSelectedItem().equals(0))
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            }
        } catch (SQLException e) {}
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, nglap, thang, nhap, ton, ban, nvid};
        String[] colLabel = {"MaBCHT", "NgayLap", "Thang", "TongNhap", "TongTon", "TongBan", "MaNV"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "BAOCAOHANGTON");
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

    public void setButtonPRINT(javafx.event.ActionEvent actionEvent) {
    }

    public void setButtonDELETE(javafx.event.ActionEvent actionEvent) {
    }

    public void setButtonUPDATE(javafx.event.ActionEvent actionEvent) {
    }

    public void setButtonADD(javafx.event.ActionEvent actionEvent) {
    }

    public void setButtonREFRESH(javafx.event.ActionEvent actionEvent) {
    }
}
