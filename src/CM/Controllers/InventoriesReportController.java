package CM.Controllers;

import CM.Functions.SmileNotification;
import CM.Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
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

public class InventoriesReportController implements Initializable {

    @FXML
    private AnchorPane paneReport;

    @FXML
    private Label lbReportForm;

    @FXML
    private Label lbReportID;

    @FXML
    private JFXTextField txtReportID, txtComponentName, txtStock, txtSell, txtImport, txtReportInfoID;

    @FXML
    private Label lbEmployeeName;

    @FXML
    private JFXTextField txtEmployeeName;

    @FXML
    private Label lbReportDate;

    @FXML
    private DatePicker dtDate;

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
    private JFXRadioButton rdbtnMonth;

    @FXML
    private JFXRadioButton rdbtnQuarter;

    @FXML
    private Label lbMonth;

    @FXML
    private JFXComboBox<Integer> cbbMonth;

    @FXML
    private Label lbQuarter;

    @FXML
    private JFXComboBox<Integer> cbbQuarter;

    @FXML
    private Label lbYear;

    @FXML
    private JFXComboBox<Integer> cbbYear;

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
    private JFXButton btnADD, btnADDinfo;

    @FXML
    private JFXButton btnPRINT;

    @FXML
    private JFXButton btnUPDATE, btnUPDATEinfo;

    @FXML
    private JFXButton btnREFRESH;

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
        rdbtnQuarter.setDisable(true);
        cbbQuarter.setDisable(true);
        ObservableList<Integer> str = FXCollections.observableArrayList(); str.add(0);
        str.add(1); str.add(2); str.add(3); str.add(4); str.add(5); str.add(6); str.add(7); str.add(8);
        str.add(9); str.add(10); str.add(11); str.add(12);
        cbbMonth.setItems(str);

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

        tbvReportInfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedDataInfo();
            }
        });

        btnREFRESH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                refresh();
            }
        });

        btnADD.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                insertData();
                //insertDataInfo();
            }
        });

        btnADDinfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                insertDataInfo();
            }
        });

        btnUPDATE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                updateData();
            }
        });

        btnUPDATEinfo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                updateDataInfo();
            }
        });

    }


    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException {
        ObservableList<InventoriesReportInfo> list = FXCollections.observableArrayList();
        int nhap = 0, ban = 0, ton = 0;
        resultSet = dbConn.getData("SELECT MaBCHT FROM BAOCAOHANGTON");
        while (resultSet.next()) {
            list.add(new InventoriesReportInfo(
                    "", "", 0, 0,
                    0, resultSet.getString("MaBCHT")
            ));
        }

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).getReportID();
            resultSet = dbConn.getData("SELECT SUM(LuongNhapBĐ) AS TongNhap FROM CHITIETBAOCAOHANGTON WHERE MaBCHT = '"+ str + "');");
            while (resultSet.next()) {
                nhap += resultSet.getInt("TongNhap");
            }


            resultSet = dbConn.getData("SELECT SUM(LuongBan) AS TongBan FROM CHITIETBAOCAOHANGTON WHERE MaBCHT = '"+ str + "');");
            while (resultSet.next()) {
                ban += resultSet.getInt("TongBan");
            }


            resultSet = dbConn.getData("SELECT SUM(LuongTon) AS TongTon FROM CHITIETBAOCAOHANGTON WHERE MaBCHT = '"+ str + "');");
            while (resultSet.next()) {
                ton += resultSet.getInt("TongTon");
            }


            resultSet = dbConn.getData("SELECT BCHT.NgayLap, BCHT.Thang, NV.TenNV FROM BAOCAOHANGTON BCHT JOIN NHANVIEN NV ON BCHT.MaNV = NV.MaNV GROUP BY " + str + ";");
            data.removeAll(data);
            while(resultSet.next()) {
                data.add(new InventoriesReport(
                        list.get(i).getReportID(),
                        resultSet.getDate("NgayLap"),
                        resultSet.getInt("Thang"),
                        resultSet.getString("TenNV"), nhap, ban, ton
                ));
            }
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
        cbbYear.getSelectionModel().select(0);
        cbbQuarter.getSelectionModel().select(0);
        dtDate.setValue(null);
        txtStock.setText("");
        txtSell.setText("");
        txtImport.setText("");
        txtComponentName.setText("");
        txtReportInfoID.setText("");
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
        rdbtnMonth.setSelected(true);
        cbbMonth.getSelectionModel().select(selectedRow.getReportMonth());
        try {
            showDataInfo(selectedRow.getReportID());
        } catch (SQLException e) {}
        catch (IOException ex) {}
//        String str = selectedRow.getPublishDate().toString();
//        cbbYear.getSelectionModel().getSelectedItem().equals(String.valueOf(str.substring(0,4)));
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showDataInfo(String orderid) throws SQLException, IOException{
        String query = "SELECT CTBCHT.MaCTBC, MH.TenMH, CTBCHT.LuongNhapBĐ, CTBCHT.LuongBan, CTBCHT.LuongTon FROM CHITIETBAOCAOHANGTON CTBCHT JOIN MATHANG MH ON CTBCHT.MaMH = MH.MaMH WHERE MaBCHT = '" + orderid  +"'";
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

    //lay thong tin du lieu duoc tu CHITIETHOADON
    public void getSelectedDataInfo() {
        InventoriesReportInfo selectedRow = tbvReportInfo.getSelectionModel().getSelectedItem();
        txtReportInfoID.setText(selectedRow.getReportInfoID());
        txtSell.setText(String.valueOf(selectedRow.getSelling()));
        txtImport.setText(String.valueOf(selectedRow.getImport()));
        txtStock.setText(String.valueOf(selectedRow.getStock()));
        txtComponentName.setText(selectedRow.getComponentName());
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

    //Thêm dữ liệu vào bảng CHITIETDONDATHANG
    public void insertDataInfo() {
        String id = "", nhap = "", ton = "", ban = "", mhid = "", ctbcid = "";
        try {
            ctbcid = txtReportInfoID.getText();
            id = txtReportID.getText();
            nhap = txtImport.getText();
            ban = txtSell.getText();
            ton = txtStock.getText();
            resultSet = dbConn.getData("SELECT MaMH FROM MATHANG WHERE TenMH = N'" + txtComponentName.getText() + "';");
            ObservableList<Components> ds = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ds.add(new Components(
                        resultSet.getString("MaMH"),
                        "", "", "", "", "" ,"", ""
                ));
            }
            mhid = ds.get(0).getComponentID();

            if (txtEmployeeName.getText().isEmpty() || txtReportInfoID.getText().isEmpty()
                    || txtReportID.getText().isEmpty()
                    || txtStock.getText().isEmpty() || txtSell.getText().isEmpty() || txtImport.getText().isEmpty())
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
            dbConn.close();
            resultSet.close();
        } catch (SQLException ex) {}
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {ctbcid, nhap, ban, ton, mhid, id};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "CHITIETBAOCAOHANGTON");
        if (isInserted > 0) {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
        }
        try {
            showDataInfo(id);
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

    //Update dữ liệu CHITIETDONDATHANG
    public void updateDataInfo() {
        String id = "", nhap = "", ton = "", ban = "", mhid = "", ctbcid = "";
        try {
            ctbcid = txtReportInfoID.getText();
            id = txtReportID.getText();
            nhap = txtImport.getText();
            ban = txtSell.getText();
            ton = txtStock.getText();
            resultSet = dbConn.getData("SELECT MaMH FROM MATHANG WHERE TenMH = N'" + txtComponentName.getText() + "';");
            ObservableList<Components> ds = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ds.add(new Components(
                        resultSet.getString("MaMH"),
                        "", "", "", "", "" ,"", ""
                ));
            }
            mhid = ds.get(0).getComponentID();

            if (txtEmployeeName.getText().isEmpty() || txtReportInfoID.getText().isEmpty()
                    || txtReportID.getText().isEmpty()
                    || txtStock.getText().isEmpty() || txtSell.getText().isEmpty() || txtImport.getText().isEmpty())
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
            dbConn.close();
            resultSet.close();
        } catch (SQLException ex) {}
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {ctbcid, nhap, ban, ton, mhid, id};
        String[] colLabel = {"MaCTBC", "LuongNhapBĐ", "LuongBan", "LuongTon", "MaMH", "MaBCHT"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "CHITIETBAOCAOHANGTON");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
        }
        try {
            showDataInfo(ctbcid);
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }
}
