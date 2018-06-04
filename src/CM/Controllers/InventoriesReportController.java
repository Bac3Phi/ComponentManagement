package CM.Controllers;

import CM.Models.DataProvider;
import CM.Models.InventoriesReport;
import CM.Models.InventoriesReportInfo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Date;
import java.util.ResourceBundle;

public class InventoriesReportController implements Initializable {
    Alert alert;
    @FXML
    private JFXTextField txtReportID, txtReportInfoID;

    @FXML
    private JFXTextField txtEmployeeID;

    @FXML
    private JFXRadioButton rdbtnMonth;

    @FXML
    private JFXRadioButton rdbtnQuarter;

    @FXML
    public TableView<InventoriesReport> tbvReport;

    @FXML
    private TableColumn<InventoriesReport, String> colReportID, colComponentName;

    @FXML
    private TableColumn<InventoriesReport, Date> colReportDate, colMonth;

    @FXML
    private TableColumn<InventoriesReport, Integer> colSumImport, colSumSell, colSumRemain;

    @FXML
    public TableView<InventoriesReportInfo> tbvReportInfo;

    @FXML
    private TableColumn<InventoriesReportInfo, String> colReportInfoID, colEmployeeID;

    @FXML
    private TableColumn<InventoriesReportInfo, Integer> colImport, colSell, colRemain;

    @FXML
    private DatePicker dtDate;

    @FXML
    private JFXTextField txtSumImport, txtSumSell, txtSumStock;

    @FXML
    private JFXComboBox<Date> cbbMonth, cbbQuarter, cbbYear;

    @FXML
    private ProgressBar progressPersonal;

    @FXML
    private Label lblComplete;

    @FXML
    private JFXButton btnPUBLISH;

    @FXML
    private JFXButton btnSHOW;

    @FXML
    private JFXButton btnEXPORT;

    @FXML
    private JFXButton btnUPDATE;

    @FXML
    private JFXButton btnREFRESH;

    public AnchorPane paneReport;

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

        colComponentName.setCellValueFactory(new PropertyValueFactory<>("ComponentID"));
        colReportInfoID.setCellValueFactory(new PropertyValueFactory<>("ReportInfoID"));
        colImport.setCellValueFactory(new PropertyValueFactory<>("Import"));
        colSell.setCellValueFactory(new PropertyValueFactory<>("Selling"));
        colRemain.setCellValueFactory(new PropertyValueFactory<>("Stock"));

//        try {
//            showData();
//        }
//        catch (IOException io){}
//        catch (SQLException e) {}

        tbvReport.setItems(data);
        tbvReportInfo.setItems(datainfo);
        tbvReport.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //getSelectedData();
            }
        });
    }
}
