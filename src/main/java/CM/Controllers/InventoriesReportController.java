package CM.Controllers;

import CM.Functions.GenerateID;
import CM.Functions.SmileNotification;
import CM.Main;
import CM.Models.*;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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
import javafx.stage.DirectoryChooser;
import javafx.util.StringConverter;
import tray.notification.NotificationType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

public class InventoriesReportController implements Initializable {

    @FXML
    private AnchorPane paneReport;

    @FXML
    private Label lbReportID;

    @FXML
    private JFXTextField txtReportID;

    @FXML
    private Label lbEmployeeName;

    @FXML
    private JFXComboBox<Employees> cbbEmployeeName;

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
    private JFXButton btnADD;

    @FXML
    private JFXButton btnPRINT;

    @FXML
    private JFXButton btnUPDATE;

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
        ObservableList<Integer> str = FXCollections.observableArrayList(); str.add(0);
        str.add(1); str.add(2); str.add(3); str.add(4); str.add(5); str.add(6); str.add(7); str.add(8);
        str.add(9); str.add(10); str.add(11); str.add(12);
        cbbMonth.setItems(str);
        txtReportID.setText(GenerateID.create("BAOCAOHANGTON", "MaBCHT", "BCHT"));

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

        btnUPDATE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                updateData();
            }
        });

        btnPRINT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                printData();
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException {
        resultSet = dbConn.getData("SELECT BCHT.MaBCHT, BCHT.NgayLap, BCHT.Thang, NV.TenNV, TongNhap, TongBan, TongTon FROM BAOCAOHANGTON BCHT JOIN NHANVIEN NV ON BCHT.MaNV = NV.MaNV;");
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


        resultSet = dbConn.getData("SELECT MaNV, TenNV FROM NHANVIEN");
        ObservableList<Employees> employees = FXCollections.observableArrayList();
        while (resultSet.next()) {
            employees.add(new Employees(
                    resultSet.getString("MaNV"),
                    resultSet.getString("TenNV"),
                    "", ""
            ));
        }
        cbbEmployeeName.setItems(employees);
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

    //Hàm refresh xóa text
    public void refresh() {
        txtReportID.setText("");
        cbbEmployeeName.getSelectionModel().select(0);
        txtSumImport.setText("");
        txtSumSell.setText("");
        txtSumStock.setText("");
        cbbMonth.getSelectionModel().select(0);
        dtDate.setValue(null);
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
        for (Employees ten:
                cbbEmployeeName.getItems()) {
            if (ten.getEmployeeName().matches(selectedRow.getEmployeeName()))
                cbbEmployeeName.getSelectionModel().select(ten);
        }
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
        String query = "SELECT CTBCHT.MaCTBC, MH.TenMH, CTBCHT.LuongNhapBĐ, CTBCHT.LuongBan, CTBCHT.LuongTon FROM CHITIETBAOCAOHANGTON CTBCHT JOIN MATHANG MH ON CTBCHT.MaMH = MH.MaMH WHERE MaBCHT = '" + orderid + "'" + "GROUP BY MH.TenMH;";
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
        String id = "", nglap = "", thang = "", nvid = "", nhap = "0", ban = "0", ton = "0";
        try {
            id = txtReportID.getText();
            nglap = dtDate.getValue().toString();
            thang = cbbMonth.getValue().toString();
            resultSet = dbConn.getData("SELECT MaNV FROM NHANVIEN WHERE TenNV = N'" + cbbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeName() + "';");
            ObservableList<Employees> ds = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ds.add(new Employees(
                        resultSet.getString("MaNV"),
                        "", "", ""
                ));
            }
            nvid = ds.get(0).getEmployeeID();
            if (txtReportID.getText().isEmpty() || cbbEmployeeName.getSelectionModel().getSelectedItem().equals(null)
                    || dtDate.getValue().isEqual(null)
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
            insertSumInfo(Integer.parseInt(thang));
            refresh();
        }
        catch (SQLException e){}
    }

    //Thêm dữ liệu tổng vào bảng DONDATHANG
    public void insertSumInfo (int month) throws SQLException {
        String idht = GenerateID.create("CHITIETBAOCAOHANGTON","MaCTBC","CTBC");
        resultSet = dbConn.getData("SELECT MH.TenMH, MONTH(HD.NgayLap), SUM(CTPN.SoLuong) AS LuongNhapBĐ, SUM(CTHD.SoLuong) AS LuongBan, SUM(MH.SoLuong) AS LuongTon FROM CHITIETPHIEUNHAP CTPN, PHIEUNHAPHANG PN, HOADON HD, CHITIETHOADON CTHD, MATHANG MH WHERE CTPN.MaPN = PN.MaPN AND CTHD.MaHD = HD.MaHD AND CTHD.MaMH = MH.MaMH AND MONTH(PN.NgayLapPhieu) = '" + month + "' AND MONTH(HD.NgayLap) = '" + month + "';");
        datainfo.removeAll(datainfo);
        while (resultSet.next()) {
            datainfo.add(new InventoriesReportInfo(
                    idht,
                    resultSet.getString("TenMH"),
                    resultSet.getInt("LuongNhapBĐ"),
                    resultSet.getInt("LuongBan"),
                    resultSet.getInt("LuongTon"),
                    txtReportID.getText()
            ));
        }
        String id = "", mhid = "", nhap = "", ban = "", ton = "", bcid = "";
        for (int i = 0; i < datainfo.size(); i++) {
            try {
                id = datainfo.get(i).getReportInfoID();
                resultSet = dbConn.getData("SELECT MaMH, TenMH FROM MATHANG WHERE TenMH = N'" + datainfo.get(i).getComponentName() + "';");
                ObservableList<Components> components = FXCollections.observableArrayList();
                while (resultSet.next()) {
                    components.add(new Components(
                            resultSet.getString("MaMH"),
                            resultSet.getString("TenMH"),
                            "", "", "", "", "", ""
                    ));
                }
                mhid = components.get(0).getComponentID();
                nhap = String.valueOf(datainfo.get(i).getImport());
                ban = String.valueOf(datainfo.get(i).getSelling());
                ton = String.valueOf(datainfo.get(i).getStock());
                bcid = txtReportID.getText();
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }

        String[] dataInsert = {id, nhap, ban, ton, mhid, bcid};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "CHITIETBAOCAOHANGTON");
        if (isInserted > 0)
        {
            try{
                showDataInfo(txtReportID.getText());
                updateTongLuong();
                refresh();
            }
            catch (SQLException e) {}
        }

        resultSet.close();
        dbConn.close();
    }

    //Update dữ liệu DONDATHANG
    public void updateData() {
        String id = "", nglap = "", thang = "", nvid = "", nhap = "", ban = "", ton = "";
        try {
            id = txtReportID.getText();
            nglap = dtDate.getValue().toString();
            thang = cbbMonth.getValue().toString();
            resultSet = dbConn.getData("SELECT MaNV FROM NHANVIEN WHERE TenNV = N'" + cbbEmployeeName.getSelectionModel().getSelectedItem().equals(null) + "';");
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
            if (txtReportID.getText().isEmpty() || cbbEmployeeName.getSelectionModel().getSelectedItem().equals(null)
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
            updateTongLuong();
            showData();
            refresh();
        }
        catch (SQLException e){}
        catch (IOException io) {}
    }

    public void printData() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("BÁO CÁO HÀNG TỒN");
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(Main.window);

        try {
            PdfWriter writer;
            writer = new PdfWriter(selectedDirectory.getAbsolutePath() + "/BaoCaoThuChi_Thang" + cbbMonth.getSelectionModel().getSelectedItem() + "_2018.pdf");

            // Creating a PdfDocument
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Adding a new page
            pdfDoc.addNewPage();

            // Creating a Document
            Document document = new Document(pdfDoc);

            addTitlePage(document);
            addDate(document);
            addInfo(document);
            addContent(document);


            // Closing the document
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTongLuong() throws SQLException{
        String text = txtReportID.getText();
        resultSet = dbConn.getData("SELECT BCHT.MaBCHT, SUM(CTBC.LuongNhapBĐ) AS TongNhap, SUM(CTBC.LuongTon) AS TongTon, SUM(CTBC.LuongBan) AS TongBan FROM BAOCAOHANGTON BCHT JOIN CHITIETBAOCAOHANGTON CTBC ON CTBC.MaBCHT = BCHT.MaBCHT WHERE BCHT.MaBCHT = '" + text + "';");
        ObservableList<InventoriesReport> list = FXCollections.observableArrayList();
        while (resultSet.next()) {
            list.add(new InventoriesReport(
                    resultSet.getString("MaBCHT"),
                    null,
                    0,
                    "",
                    resultSet.getInt("TongNhap"),
                    resultSet.getInt("TongTon"),
                    resultSet.getInt("TongBan")
            ));
        }

        String id = "", nglap = "", thang = "", nvid = "", nhap = "", ban = "", ton = "";
        try {
            id = txtReportID.getText();
            nglap = dtDate.getValue().toString();
            thang = String.valueOf(cbbMonth.getSelectionModel().getSelectedItem());
            resultSet = dbConn.getData("SELECT MaNV FROM NHANVIEN WHERE TenNV = N'" + cbbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeName() + "';");
            ObservableList<Employees> ds = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ds.add(new Employees(
                        resultSet.getString("MaNV"),
                        "", "", ""
                ));
            }
            nvid = ds.get(0).getEmployeeID();

            nhap = String.valueOf(list.get(0).getSumImport());
            ton = String.valueOf(list.get(0).getSumStock());
            ban = String.valueOf(list.get(0).getSumSell());
            String[] dataUpdate = {id, nglap, thang, nhap, ton, ban, nvid};
            String[] colLabel = {"MaBCHT", "NgayLap", "Thang", "TongNhap", "TongTon", "TongBan", "MaNV"};
            int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "BAOCAOHANGTON");
            if (isUpdated > 0)
                showData();
        } catch (IOException ex) {}
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        dbConn.close();
        resultSet.close();
    }

    private void addContent(Document document) throws IOException, SQLException {
        addHeader("2. Báo Cáo", document);
        addInfo("2.1 Thông Tin Báo Cáo", document);
        addTable(document);

        addInfo("2.2 Báo Cáo Chi Tiết Hàng Tồn", document);
        addTableInventories(document);
    }

    private void addDate(Document document) throws IOException {
        Paragraph date;
        date = new Paragraph("Tháng: " + cbbMonth.getSelectionModel().getSelectedItem() + "/2018");

        // Setting font of the text
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        date.setFont(font);
        date.setFontSize(12);
        date.setTextAlignment(TextAlignment.CENTER);

        document.add(date);
    }

    private void addTitlePage(Document document) throws IOException {
        Paragraph title = new Paragraph("BÁO CÁO HÀNG TỒN");

        // Setting font of the text
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        title.setFont(font);
        title.setFontSize(22);
        title.setTextAlignment(TextAlignment.CENTER);

        document.add(title);
    }

    private void addInfo(String strinfo, Document document) throws IOException {
        Paragraph info = new Paragraph(strinfo);

        // Setting font of the text
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        info.setFont(font);
        info.setFontSize(12);
        info.setTextAlignment(TextAlignment.LEFT);
        info.setPaddingLeft(25);

        document.add(info);
    }

    private void addInfo(Document document) throws IOException {
        addHeader("1. Thông tin", document);
        addInfo("Mã Báo Cáo: " + txtReportID.getText(), document);
        addInfo("Tên Nhân Viên: " + cbbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeName(), document);
        addInfo("Ngày Lập: " + dtDate.getValue(), document);

        addInfo("Loại Báo Cáo: Tháng", document);
        addInfo("Tháng: " + cbbMonth.getSelectionModel().getSelectedItem() + "\tNăm: 2018", document);
    }

    private void addHeader(String strheader, Document document) throws IOException {
        Paragraph header = new Paragraph(strheader);

        // Setting font of the text
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
        header.setFont(font);
        header.setFontSize(14);
        header.setTextAlignment(TextAlignment.LEFT);

        document.add(header);
    }

    private void addTable(Document document) throws SQLException {
        float [] pointColumnWidths = {200F, 200F, 200F, 200F, 200F, 200F, 200F};
        Table table = new Table(pointColumnWidths);

        table.addCell(new Cell().add("Mã BCHT").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Ngày lập").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Tháng").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Tên nhân viên lập").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Tổng nhập").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Tổng bán").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Tổng tồn").setTextAlignment(TextAlignment.CENTER).setFontSize(14));

        InventoriesReport selectedRow = tbvReport.getSelectionModel().getSelectedItem();

        table.addCell(new Cell().add(selectedRow.getReportID()).setTextAlignment(TextAlignment.LEFT).setFontSize(12));
        table.addCell(new Cell().add(selectedRow.getPublishDate().toString()).setTextAlignment(TextAlignment.LEFT));
        table.addCell(new Cell().add(String.valueOf(selectedRow.getReportMonth())).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        table.addCell(new Cell().add(String.valueOf(selectedRow.getEmployeeName())).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        table.addCell(new Cell().add(String.valueOf(selectedRow.getSumImport())).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        table.addCell(new Cell().add(String.valueOf(selectedRow.getSumSell())).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        table.addCell(new Cell().add(String.valueOf(selectedRow.getSumStock())).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));

        document.add(table);
    }

    private void addTableInventories(Document document) throws SQLException {
        float [] pointColumnWidths = {200F, 200F, 200F, 200F, 200F};
        Table table = new Table(pointColumnWidths);

        table.addCell(new Cell().add("Mã Chi Tiết Hàng Tồn").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Tên Mặt Hàng").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Lượng Nhập").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Lượng Bán").setTextAlignment(TextAlignment.CENTER).setFontSize(14));
        table.addCell(new Cell().add("Lượng Tồn").setTextAlignment(TextAlignment.CENTER).setFontSize(14));

        resultSet = dbConn.getData("SELECT CTBCHT.MaCTBC, MH.TenMH, SUM(CTPN.SoLuong) AS LuongNhapBĐ, SUM(CTHD.SoLuong) AS LuongBan, SUM(MH.SoLuong) AS LuongTon FROM CHITIETBAOCAOHANGTON CTBCHT JOIN MATHANG MH JOIN CHITIETPHIEUNHAP CTPN JOIN CHITIETHOADON CTHD ON CTBCHT.MaMH = MH.MaMH AND CTPN.MaMH = CTBCHT.MaMH AND CTHD.MaMH = CTBCHT.MaMH WHERE MaBCHT = '" + txtReportID.getText() + "'" + "GROUP BY TenMH;");

        while (resultSet.next()){
            table.addCell(new Cell().add(resultSet.getString("MaCTBC")).setTextAlignment(TextAlignment.LEFT).setFontSize(12));
            table.addCell(new Cell().add(resultSet.getString("TenMH")).setTextAlignment(TextAlignment.LEFT).setFontSize(12));
            table.addCell(new Cell().add(String.valueOf(resultSet.getLong("LuongNhapBĐ"))).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
            table.addCell(new Cell().add(String.valueOf(resultSet.getLong("LuongBan"))).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
            table.addCell(new Cell().add(String.valueOf(resultSet.getLong("LuongTon"))).setTextAlignment(TextAlignment.RIGHT).setFontSize(12));
        }

        document.add(table);
    }
}
