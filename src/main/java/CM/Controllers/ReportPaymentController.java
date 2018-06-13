package CM.Controllers;

import CM.Functions.SmileNotification;
import CM.Main;
import CM.Models.*;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontEncoding;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.jfoenix.controls.*;
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
import javafx.stage.DirectoryChooser;
import javafx.util.StringConverter;
import tray.notification.NotificationType;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ReportPaymentController implements Initializable {

    @FXML
    JFXTextField txtPaymentReportId, txtMonth, txtYear;

    @FXML
    JFXComboBox<Employees> cbEmployeeName;

    @FXML
    JFXDatePicker dpPublishDate;

    @FXML
    JFXRadioButton rdbtnMonth, rdbtnQuaterOfYear;

    @FXML
    TableView<ReceiptsAndPaymentsReport> tbvReport;

    @FXML
    TableColumn<ReceiptsAndPaymentsReport, String> colPaymentReportId, colEmployeeNameReport, colTotalRevenue, colTotalCost,
            colPublishDate, colReportType, colDate;

    @FXML
    TableView<RevenueDetail> tbvRevenueDetail;

    @FXML
    TableColumn<RevenueDetail, String> colRevenueDetailId, colCompNameRevenue, colTotalRevenueDetail;

    @FXML
    TableView<CostDetail> tbvCostDetail;

    @FXML
    TableColumn<CostDetail, String> colCostDetailId, colCompNameCost, colTotalCostDetail;

    @FXML
    JFXButton btnAdd, btnUpdate, btnDelete, btnRefresh, btnPrint;

    DataProvider dbConn;
    ObservableList<ReceiptsAndPaymentsReport> data;
    ObservableList<RevenueDetail> dataRevenueDetail;
    ObservableList<CostDetail> dataCostDetail;
    ResultSet resultSet;

    public static final String FONT
            = "./src/main/resources/Assets/font/times.ttf";
    public static final String FONT_BOLD
            = "./src/main/resources/Assets/font/timesbd.ttf";

    PdfFont font;
    PdfFont font_bold;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnPrint.setDisable(true);
        dbConn = new DataProvider();
        data = FXCollections.observableArrayList();
        dataRevenueDetail = FXCollections.observableArrayList();
        dataCostDetail = FXCollections.observableArrayList();
        tbvReport.setEditable(false);
        tbvRevenueDetail.setEditable(false);
        tbvCostDetail.setEditable(false);
        groupByRaidioButton();

        try {
            font = PdfFontFactory.createFont(FONT, PdfEncodings.IDENTITY_H);
            font_bold = PdfFontFactory.createFont(FONT_BOLD, PdfEncodings.IDENTITY_H);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setupTbvReport();
        setupTbvRevenueDetail();
        setupCostDetail();

        try {
            setupCbEmployeeName();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            showData();
        } catch (SQLException e) {}

        tbvReport.setItems(data);
        tbvReport.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });
    }

    private void groupByRaidioButton(){
        final ToggleGroup group = new ToggleGroup();
        rdbtnMonth.setToggleGroup(group);
        rdbtnMonth.setSelected(true);
        rdbtnQuaterOfYear.setToggleGroup(group);
    }

    private void showData() throws SQLException {
        resultSet = dbConn.getData("select MaBCTC, NgayLap, TongThu, TongChi, TenNV, TuyChon, ThoiGian\n" +
                "from BAOCAOTHUCHI BCTC join NHANVIEN NV on BCTC.MaNV = NV.MaNV");
        data.removeAll(data);
        while (resultSet.next()){
            String[] parts = resultSet.getString("ThoiGian").split("/");
            String str;
            if (resultSet.getString("TuyChon").matches(Constance.Month)){
                str = "Tháng " + parts[0] + " Năm " + parts[1];
            }
            else str = "Quý " + parts[0] + " Năm " + parts[1];
             data.add(new ReceiptsAndPaymentsReport(
                    resultSet.getString("MaBCTC"),
                    resultSet.getDate("NgayLap"),
                    resultSet.getInt("TongThu"),
                    resultSet.getInt("TongChi"),
                    resultSet.getString("TenNV"),
                    resultSet.getString("TuyChon"),
                    str
            ));
        }
        resultSet.close();
    }

    private void setupCbEmployeeName() throws SQLException {
        ResultSet dataEmployeeName = dbConn.getData("SELECT MaNV, TenNV FROM NHANVIEN");
        ObservableList<Employees> employees = FXCollections.observableArrayList();
        //employees.add(new Employees(null, "No selection", "",""));

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

    private void setupTbvReport() {
        colPaymentReportId.setCellValueFactory(new PropertyValueFactory<>("ReportID"));
        colEmployeeNameReport.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        colTotalRevenue.setCellValueFactory(new PropertyValueFactory<>("SumReceipts"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("SumPayments"));
        colPublishDate.setCellValueFactory(new PropertyValueFactory<>("PublishDate"));
        colReportType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }

    private void setupTbvRevenueDetail() {
        colRevenueDetailId.setCellValueFactory(new PropertyValueFactory<>("RevenueDetailId"));
        colCompNameRevenue.setCellValueFactory(new PropertyValueFactory<>("CompName"));
        colTotalRevenueDetail.setCellValueFactory(new PropertyValueFactory<>("TotalRevenueDetail"));
    }

    private void setupCostDetail() {
        colCostDetailId.setCellValueFactory(new PropertyValueFactory<>("CostDetailId"));
        colCompNameCost.setCellValueFactory(new PropertyValueFactory<>("CompName"));
        colTotalCostDetail.setCellValueFactory(new PropertyValueFactory<>("TotalCostDetail"));
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        ReceiptsAndPaymentsReport selectedRow = tbvReport.getSelectionModel().getSelectedItem();
        if (selectedRow != null){
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
            btnPrint.setDisable(false);
            txtPaymentReportId.setText(selectedRow.getReportID());
            String[] parts = selectedRow.getDate().split(" ");
            txtMonth.setText(parts[1]);
            txtYear.setText(parts[3]);

            dpPublishDate.setValue(LocalDate.parse(selectedRow.getPublishDate().toString()));

            if (selectedRow.getType().matches(Constance.Month))
            {
                rdbtnMonth.setSelected(true);
            }
            else rdbtnQuaterOfYear.setSelected(true);

            for (Employees employees:
                    cbEmployeeName.getItems()) {
                if (employees.getEmployeeName().matches(selectedRow.getEmployeeName())){
                    cbEmployeeName.getSelectionModel().select(employees);
                    break;
                }
            }

            try {
                loadTbvRevenueDetail(selectedRow.getReportID());
                loadTbvCostDetail(selectedRow.getReportID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadTbvCostDetail(String reportID) throws SQLException {
        resultSet = dbConn.getData("select MaCTC, TenMH, TongNhap\n" +
                "from CHITIETCHI CTC join MATHANG MH on CTC.MaMH = MH.MaMH\n" +
                "where MaBCTC = '" + reportID + "'");
        dataCostDetail.removeAll(dataCostDetail);
        while (resultSet.next()){
            dataCostDetail.add(new CostDetail(
                    resultSet.getString("MaCTC"),
                    "",
                    resultSet.getString("TenMH"),
                    resultSet.getString("TongNhap")
            ));
        }
        resultSet.close();

        tbvCostDetail.setItems(dataCostDetail);
    }

    private void loadTbvRevenueDetail(String reportID) throws SQLException {
        resultSet = dbConn.getData("select MaCTT, TenMH, TongBan\n" +
                "from CHITIETTHU CTT join MATHANG MH on CTT.MaMH = MH.MaMH\n" +
                "where MaBCTC = '" + reportID + "'");
        dataRevenueDetail.removeAll(dataRevenueDetail);
        while (resultSet.next()){
            dataRevenueDetail.add(new RevenueDetail(
                    resultSet.getString("MaCTT"),
                    "",
                    resultSet.getString("TenMH"),
                    resultSet.getString("TongBan")
            ));
        }
        resultSet.close();

        tbvRevenueDetail.setItems(dataRevenueDetail);
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ngaylap ="", tongthu = "0", tongchi = "0", manv = "", loai ="", thoigian = "";
        try {
            id = txtPaymentReportId.getText();
            ngaylap = dpPublishDate.getValue().toString();
            manv = cbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeID();
            if (rdbtnMonth.isSelected()){
                loai = Constance.Month;
            }
            else loai = Constance.Quarter_Of_Year;
            thoigian = txtMonth.getText() + "/" + txtYear.getText();

            if (txtPaymentReportId.getText().isEmpty() || txtMonth.getText().isEmpty()
                    || txtYear.getText().isEmpty()
                    || dpPublishDate.getValue().toString().isEmpty()
                    || cbEmployeeName.getSelectionModel().getSelectedItem().equals(null))
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
            else if (rdbtnMonth.isSelected() && (Integer.parseInt(txtMonth.getText()) > 12 ||
                    Integer.parseInt(txtMonth.getText()) < 0)){
                SmileNotification.creatingNotification("Thông báo","Tháng Không Hợp Lệ",NotificationType.WARNING);
            }
            else if (rdbtnQuaterOfYear.isSelected() && (Integer.parseInt(txtMonth.getText()) > 4 ||
                    Integer.parseInt(txtMonth.getText()) < 0)){
                SmileNotification.creatingNotification("Thông báo","Quý Không Hợp Lệ",NotificationType.WARNING);
            }
            else {
                String[] dataInsert = {id, ngaylap, tongthu, tongchi, manv, loai, thoigian};
                int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "BAOCAOTHUCHI");
                if (isInserted > 0) {
                    SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
                }
                else
                {
                    SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
                }
                try {
                    insertRevenueDetail(loai,txtMonth.getText(),txtYear.getText());
                    insertCostDetail(loai,txtMonth.getText(),txtYear.getText());
                    updateRevenueAndCost(id, ngaylap, manv, loai, thoigian);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                refresh();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    private void insertRevenueDetail(String loai, String month, String year) throws SQLException {
        if (loai.matches(Constance.Month)){
            resultSet = dbConn.getData("select MaMH, SUM(TienThanhToan) Tong\n" +
                    "from CHITIETHOADON CTHD join HOADON HD on CTHD.MaHD = HD.MaHD\n" +
                    "where YEAR(HD.NgayLap) = " + year + " AND MONTH(HD.NgayLap) = " + month + "\n" +
                    "group by MaMH");
        }
        else {
            int quarter = Integer.parseInt(month);
            int start = quarter*2 + quarter -2;
            int end = start + 2;
            resultSet = dbConn.getData("select MaMH, SUM(TienThanhToan) Tong\n" +
                    "from CHITIETHOADON CTHD join HOADON HD on CTHD.MaHD = HD.MaHD\n" +
                    "where YEAR(HD.NgayLap) = " + year + " AND MONTH(HD.NgayLap) > " + start + " AND MONTH(HD.NgayLap) <= " + end + "\n" +
                    "group by MaMH");
        }
        int i = 0;
        while (resultSet.next()){
            i++;
            insertDataRevenue(resultSet, i);
        }
        resultSet.close();
    }

    private void insertDataRevenue(ResultSet resultSet, int i) {
        String id = "", mabctc ="", mamh = "", tongban = "0";
        try {
            id = txtPaymentReportId.getText() + "T" + i;
            mabctc = txtPaymentReportId.getText();
            try {
                mamh = resultSet.getString("MaMH");
                tongban = resultSet.getString("Tong");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            String[] dataInsert = {id, mabctc, mamh, tongban};
            int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "CHITIETTHU");
            if (isInserted > 0) {
                SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
            }
            else {
                SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    private void insertCostDetail(String loai, String month, String year) throws SQLException {
        if (loai.matches(Constance.Month)){
            resultSet = dbConn.getData("select MaMH, SUM(TongTien) Tong\n" +
                    "from CHITIETPHIEUNHAP CTPN join PHIEUNHAPHANG PN on CTPN.MaPN = PN.MaPN\n" +
                    "where YEAR(PN.NgayLapPhieu) = " + year + " AND MONTH(PN.NgayLapPhieu) = " + month + "\n" +
                    "group by MaMH");
        }
        else {
            int quarter = Integer.parseInt(month);
            int start = quarter*2 + quarter -2;
            int end = start + 2;
            resultSet = dbConn.getData("select MaMH, SUM(TongTien) Tong\n" +
                    "from CHITIETPHIEUNHAP CTPN join PHIEUNHAPHANG PN on CTPN.MaPN = PN.MaPN\n" +
                    "where YEAR(PN.NgayLapPhieu) = " + year + " AND MONTH(PN.NgayLapPhieu) > " + start + " AND MONTH(PN.NgayLapPhieu) <= " + end + "\n" +
                    "group by MaMH");
        }
        int i = 0;
        while (resultSet.next()){
            i++;
            insertDataCost(resultSet, i);
        }
        resultSet.close();
    }

    private void insertDataCost(ResultSet resultSet, int i) {
        String id = "", mabctc ="", mamh = "", tongnhap = "0";
        try {
            id = txtPaymentReportId.getText() + "C" + i;
            mabctc = txtPaymentReportId.getText();
            try {
                mamh = resultSet.getString("MaMH");
                tongnhap = resultSet.getString("Tong");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            String[] dataInsert = {id, mabctc, mamh, tongnhap};
            int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "CHITIETCHI");
            if (isInserted > 0) {
                SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thành công!",NotificationType.SUCCESS);
            }
            else {
                SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu thất bại",NotificationType.ERROR);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    private void updateRevenueAndCost(String id, String ngaylap, String manv, String loai, String thoigian) throws SQLException {
        String tongThu = "", tongChi = "";
        resultSet = dbConn.getData("select SUM(TongBan) Tong\n" +
                "from CHITIETTHU where MaBCTC = '" + id + "'");
        while (resultSet.next()){
            tongThu = resultSet.getString("Tong");
        }
        resultSet.close();

        resultSet = dbConn.getData("select SUM(TongNhap) Tong\n" +
                "from CHITIETCHI where MaBCTC = '" + id + "'");
        while (resultSet.next()){
            tongChi = resultSet.getString("Tong");
        }
        resultSet.close();

        String[] dataUpdate = {id, ngaylap, tongThu, tongChi, manv, loai, thoigian};
        String[] colLabel = {"MaBCTC", "NgayLap", "TongThu", "TongChi", "MaNV", "TuyChon", "ThoiGian"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "BAOCAOTHUCHI");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
        }
    }

    //Update dữ liệu
    public void updateData() {
        String id = "", ngaylap ="", tongthu = "0", tongchi = "0", manv = "", loai ="", thoigian = "";
        try {
            id = txtPaymentReportId.getText();
            ngaylap = dpPublishDate.getValue().toString();
            manv = cbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeID();
            if (rdbtnMonth.isSelected()){
                loai = Constance.Month;
            }
            else loai = Constance.Quarter_Of_Year;
            thoigian = txtMonth.getText() + "/" + txtYear.getText();

            if (txtPaymentReportId.getText().isEmpty() || txtMonth.getText().isEmpty()
                    || txtYear.getText().isEmpty()
                    || dpPublishDate.getValue().toString().isEmpty()
                    || cbEmployeeName.getSelectionModel().getSelectedItem().equals(null))
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
            else if (rdbtnMonth.isSelected() && (Integer.parseInt(txtMonth.getText()) > 12 ||
                    Integer.parseInt(txtMonth.getText()) < 0)){
                SmileNotification.creatingNotification("Thông báo","Tháng Không Hợp Lệ",NotificationType.WARNING);
            }
            else if (rdbtnQuaterOfYear.isSelected() && (Integer.parseInt(txtMonth.getText()) > 4 ||
                    Integer.parseInt(txtMonth.getText()) < 0)){
                SmileNotification.creatingNotification("Thông báo","Quý Không Hợp Lệ",NotificationType.WARNING);
            }
            else {
                String[] dataUpdate = {id, ngaylap, tongthu, tongchi, manv, loai, thoigian};
                String[] colLabel = {"MaBCTC", "NgayLap", "TongThu", "TongChi", "MaNV", "TuyChon", "ThoiGian"};
                int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "BAOCAOTHUCHI");
                if (isUpdated > 0) {
                    SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công!",NotificationType.SUCCESS);
                }
                else
                {
                    SmileNotification.creatingNotification("Thông báo","Cập nhật không thành công ",NotificationType.ERROR);
                }
                refresh();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Delete dữ liệu
    public void deleteData() {
        if (txtPaymentReportId.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        else {
            String[] dataDelete = {txtPaymentReportId.getText()};
            int isDeletedRevenue = dbConn.ExecuteSQLDelete(dataDelete, "CHITIETTHU", "MaBCTC");
            int isDeletedCost = dbConn.ExecuteSQLDelete(dataDelete, "CHITIETCHI", "MaBCTC");
            int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "BAOCAOTHUCHI", "MaBCTC");
            if (isDeleted > 0 && isDeletedRevenue > 0 && isDeletedCost>0) {
                SmileNotification.creatingNotification("Thông báo","Xóa dữ liệu thành công",NotificationType.SUCCESS);
            }
            else
            {
                SmileNotification.creatingNotification("Thông Báo","Xóa dữ liệu thất bại",NotificationType.INFORMATION);
            }
            refresh();
        }
    }

    public void refresh(){
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        btnPrint.setDisable(true);
        txtPaymentReportId.setText("");
        cbEmployeeName.setValue(null);
        dpPublishDate.setValue(null);
        rdbtnMonth.setSelected(true);
        txtMonth.setText("");
        txtYear.setText("");

        try {
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tbvRevenueDetail.getItems().clear();
        tbvCostDetail.getItems().clear();
    }

    public void setButtonADD(ActionEvent actionEvent) {
        insertData();
    }

    public void setButtonUPDATE(ActionEvent actionEvent) {
        updateData();
    }

    public void setButtonDELETE(ActionEvent actionEvent) {
        deleteData();
    }

    public void setButtonREFRESH(ActionEvent actionEvent) {
        refresh();
    }

    public void setButtonPRINT(ActionEvent actionEvent) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Báo Cáo Thu Chi");
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(Main.window);

        try {
            PdfWriter writer;
            if (rdbtnMonth.isSelected()){
                writer = new PdfWriter(selectedDirectory.getAbsolutePath() + "/BaoCaoThuChi_Thang" + txtMonth.getText() + "_" + txtYear.getText() + ".pdf");
            }
            else{
                writer = new PdfWriter(selectedDirectory.getAbsolutePath() + "/BaoCaoThuChi_Quy" + txtMonth.getText() + "_" + txtYear.getText() + ".pdf");
            }

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

    private void addContent(Document document) throws IOException, SQLException {
        addHeader("2. Báo Cáo", document);
        addInfo("2.1 Thông Tin Báo Cáo", document);
        addTable(document);
        
        addInfo("2.2 Báo Cáo Chi Tiết Thu", document);
        addTableRecetpts(document);

        addInfo("2.3 Báo Cáo Chi Tiết Chi", document);
        addTablePayments(document);
    }

    private void addTableRecetpts(Document document) throws SQLException, IOException {
        float [] pointColumnWidths = {200F, 200F, 200F};
        Table table = new Table(pointColumnWidths);

        table.addCell(new Cell().add("Mã Chi Tiết Thu").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Tên Mặt Hàng").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Tổng Bán").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));

        resultSet = dbConn.getData("select MaCTT, TenMH, TongBan\n" +
                "from CHITIETTHU CTT join MATHANG MH on CTT.MaMH = MH.MaMH\n" +
                "where MaBCTC = '" + txtPaymentReportId.getText() + "'");

        while (resultSet.next()){
            table.addCell(new Cell().add(resultSet.getString("MaCTT")).setTextAlignment(TextAlignment.LEFT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(resultSet.getString("TenMH")).setTextAlignment(TextAlignment.LEFT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(String.valueOf(resultSet.getLong("TongBan"))).setTextAlignment(TextAlignment.RIGHT).setFontSize(12).setFont(font));
        }

        document.add(table);
    }

    private void addTablePayments(Document document) throws SQLException {
        float [] pointColumnWidths = {200F, 200F, 200F};
        Table table = new Table(pointColumnWidths);

        table.addCell(new Cell().add("Ma Chi Tiet Chi").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Ten Mat Hang").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Tong Nhap").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));

        resultSet = dbConn.getData("select MaCTC, TenMH, TongNhap\n" +
                "from CHITIETCHI CTC join MATHANG MH on CTC.MaMH = MH.MaMH\n" +
                "where MaBCTC = '" + txtPaymentReportId.getText() + "'");

        while (resultSet.next()){
            table.addCell(new Cell().add(resultSet.getString("MaCTC")).setTextAlignment(TextAlignment.LEFT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(resultSet.getString("TenMH")).setTextAlignment(TextAlignment.LEFT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(String.valueOf(resultSet.getLong("TongNhap"))).setTextAlignment(TextAlignment.RIGHT).setFontSize(12).setFont(font));
        }

        document.add(table);
    }

    private void addTable(Document document) throws SQLException {
        float [] pointColumnWidths = {200F, 200F, 200F, 200F, 200F, 200F, 200F};
        Table table = new Table(pointColumnWidths);

        table.addCell(new Cell().add("Mã BCTC").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Nhân Viên").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Tổng Thu").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Tổng Chi").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Ngày Lập").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Loại Báo Cáo").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));
        table.addCell(new Cell().add("Thời Gian").setTextAlignment(TextAlignment.CENTER).setFontSize(14).setFont(font_bold));

        ReceiptsAndPaymentsReport selectedRow = tbvReport.getSelectionModel().getSelectedItem();

            table.addCell(new Cell().add(selectedRow.getReportID()).setTextAlignment(TextAlignment.LEFT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(selectedRow.getEmployeeName()).setTextAlignment(TextAlignment.LEFT).setFont(font));
            table.addCell(new Cell().add(String.valueOf(selectedRow.getSumReceipts())).setTextAlignment(TextAlignment.RIGHT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(String.valueOf(selectedRow.getSumPayments())).setTextAlignment(TextAlignment.RIGHT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(String.valueOf(selectedRow.getPublishDate())).setTextAlignment(TextAlignment.RIGHT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(selectedRow.getType()).setTextAlignment(TextAlignment.RIGHT).setFontSize(12).setFont(font));
            table.addCell(new Cell().add(selectedRow.getDate()).setTextAlignment(TextAlignment.RIGHT).setFontSize(12).setFont(font));

        document.add(table);
    }

    private void addInfo(Document document) throws IOException {
        addHeader("1. Thông Tin", document);
        addInfo("Mã Báo Cáo: " + txtPaymentReportId.getText(), document);
        addInfo("Tên Nhân Viên: " + cbEmployeeName.getSelectionModel().getSelectedItem().getEmployeeName(), document);
        addInfo("Ngày Lập: " + dpPublishDate.getValue(), document);

        if (rdbtnMonth.isSelected()){
            addInfo("Loại Báo Cáo: Tháng", document);
            addInfo("Tháng: " + txtMonth.getText() + "\tNăm: " + txtYear.getText(), document);
        }
        else {
            addInfo("Loại Báo Cáo: Quý", document);
            addInfo("Quý: " + txtMonth.getText() + "\tNăm: " + txtYear.getText(), document);
        }
    }

    private void addInfo(String strinfo, Document document) throws IOException {
        Paragraph info = new Paragraph(strinfo);

        // Setting font of the text
        info.setFont(font);
        info.setFontSize(12);
        info.setTextAlignment(TextAlignment.LEFT);
        info.setPaddingLeft(25);

        document.add(info);
    }

    private void addHeader(String strheader, Document document) throws IOException {
        Paragraph header = new Paragraph(strheader);

        // Setting font of the text
        header.setFont(font_bold);
        header.setFontSize(14);
        header.setTextAlignment(TextAlignment.LEFT);

        document.add(header);
    }

    private void addDate(Document document) throws IOException {
        Paragraph date;
        if (rdbtnMonth.isSelected()){
            date = new Paragraph("Tháng: " + txtMonth.getText() + "/" + txtYear.getText());
        }
        else date = new Paragraph("Quý: " + txtMonth.getText() + "/" + txtYear.getText());

        // Setting font of the text
        date.setFont(font);
        date.setFontSize(12);
        date.setTextAlignment(TextAlignment.CENTER);

        document.add(date);
    }

    private void addTitlePage(Document document) throws IOException {
        Paragraph title = new Paragraph("Báo Cáo Thu Chi");

        // Setting font of the text
        title.setFont(font_bold);
        title.setFontSize(22);
        title.setTextAlignment(TextAlignment.CENTER);

        document.add(title);
    }
}
