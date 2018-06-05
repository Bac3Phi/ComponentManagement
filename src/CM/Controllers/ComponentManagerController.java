package CM.Controllers;

import CM.Functions.SmileNotification;
import CM.Models.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import tray.notification.NotificationType;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ComponentManagerController implements Initializable {
    Alert alert;
    @FXML
    private JFXTextField txtComponentID;

    @FXML
    private JFXTextField txtComponentName;

    @FXML
    private JFXTextField txtComponentConfig;

    @FXML
    private JFXTextField txtComponentMaker;

    @FXML
    private JFXTextField txtNumOfComp;
    @FXML
    private TableView<Components> tbvComponent;

    @FXML
    private TableColumn<Components, String> colComponentD, colComponentName, colComponentConfig, colTypeName, colComponentMaker, colAreaName,colUnit, colNumOfComp;
    @FXML
    public JFXComboBox<TypeOfGoods> cbTypeName;
    @FXML
    public JFXComboBox<CompUnit> cbUnit;
    @FXML
    public JFXComboBox<Stock> cbAreaName;
    @FXML
    private ProgressBar progressPersonal;

    @FXML
    private Label lblComplete;

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

    DataProvider dbConn;
    ObservableList<Components> data;
    ResultSet resultSet;
    @Override
    public void initialize(URL location, ResourceBundle resource) {
        btnDELETE.setDisable(true);
        btnUPDATE.setDisable(true);
        dbConn = new DataProvider();
        data = FXCollections.observableArrayList();
        tbvComponent.setEditable(false);

        colComponentD.setCellValueFactory(new PropertyValueFactory<>("ComponentID"));
        colComponentName.setCellValueFactory(new PropertyValueFactory<>("ComponentName"));
        colComponentConfig.setCellValueFactory(new PropertyValueFactory<>("CompInfo"));
        colTypeName.setCellValueFactory(new PropertyValueFactory<>("TypesOfComp"));
        colComponentMaker.setCellValueFactory(new PropertyValueFactory<>("CompMaker"));
        colAreaName.setCellValueFactory(new PropertyValueFactory<>("AreaName"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        colNumOfComp.setCellValueFactory(new PropertyValueFactory<>("NumOfComp"));

        try {
            setupCbTypeName();
            setupCbUnit();
            setupCbAreaName();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvComponent.setItems(data);
        tbvComponent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });
        updateProgress();
    }
    private static double progress1 = 0;
    private static double progress2 = 0;
    private static double progress3 = 0;
    private static double progress4 = 0;
    private static double progress5 = 0;
    private static double progress6 = 0;
    private static double progress7 = 0;
    private static double progress8 = 0;
    private void updateProgress() {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        //progressPersonal.setProgress(0.00);
        double sum_progress = progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8;
        txtComponentID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.125;

                } else {
                    progress1 = 0.0;

                }

               double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        txtComponentConfig.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress2 = 0.125;

                } else {
                    progress2 = 0.0;

                }

               double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        txtComponentMaker.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress3 = 0.125;

                } else {
                    progress3 = 0.0;

                }

               double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        txtComponentName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress4 = 0.125;

                } else {
                    progress4 = 0.0;

                }

               double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        txtNumOfComp.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress5 = 0.125;

                } else {
                    progress5 = 0.0;

                }

               double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        cbAreaName.valueProperty().addListener(new ChangeListener<Stock>() {
            @Override
            public void changed(ObservableValue<? extends Stock> observable, Stock oldValue, Stock newValue) {
                if (!cbAreaName.getSelectionModel().isSelected(0)) {
                    progress6 = 0.125;

                } else {
                    progress6 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });
        cbTypeName.valueProperty().addListener(new ChangeListener<TypeOfGoods>() {
            @Override
            public void changed(ObservableValue<? extends TypeOfGoods> observable, TypeOfGoods oldValue, TypeOfGoods newValue) {
                if (!cbTypeName.getSelectionModel().isSelected(0)) {
                    progress7 = 0.125;

                } else {
                    progress7 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });
        cbUnit.valueProperty().addListener(new ChangeListener<CompUnit>() {
            @Override
            public void changed(ObservableValue<? extends CompUnit> observable, CompUnit oldValue, CompUnit newValue) {
                if (!cbUnit.getSelectionModel().isSelected(0)) {
                    progress8 = 0.125;

                } else {
                    progress8 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 +progress6 +progress7 +progress8 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });
    }

    private void setupCbUnit() throws SQLException {
        ResultSet dataTypeOfComp = dbConn.getData("SELECT * FROM DONVITINH");
        ObservableList<CompUnit> units = FXCollections.observableArrayList();
        units.add(new CompUnit(null, "No selection"));

        while (dataTypeOfComp.next()){
            units.add(new CompUnit(dataTypeOfComp.getString("MaDV"), dataTypeOfComp.getString("TenDV")));
        }
        cbUnit.setItems(units);
        cbUnit.setConverter(new StringConverter<CompUnit>() {
            @Override
            public String toString(CompUnit object) {
                return object.getUnitName();
            }

            @Override
            public CompUnit fromString(String string) {
                return null;
            }
        });
        cbUnit.getSelectionModel().select(0);
    }

    private void setupCbAreaName() throws SQLException {
        ResultSet dataTypeOfComp = dbConn.getData("SELECT * FROM KHO");
        ObservableList<Stock> areas = FXCollections.observableArrayList();
        areas.add(new Stock(null, "No selection"));

        while (dataTypeOfComp.next()){
            areas.add(new Stock(dataTypeOfComp.getString("MaKhu"), dataTypeOfComp.getString("TenKhu")));
        }
        cbAreaName.setItems(areas);
        cbAreaName.setConverter(new StringConverter<Stock>() {

            @Override
            public String toString(Stock object) {
                return object.getAreaName();
            }

            @Override
            public Stock fromString(String string) {
                return null;
            }
        });
        cbAreaName.getSelectionModel().select(0);
    }

    private void setupCbTypeName() throws SQLException {
        ResultSet dataTypeOfComp = dbConn.getData("SELECT * FROM LOAIMATHANG");
        ObservableList<TypeOfGoods> types = FXCollections.observableArrayList();
        types.add(new TypeOfGoods(null, "No selection"));

        while (dataTypeOfComp.next()){
            types.add(new TypeOfGoods(dataTypeOfComp.getString("MaLoai"), dataTypeOfComp.getString("TenLoai")));
        }
        cbTypeName.setItems(types);
        cbTypeName.setConverter(new StringConverter<TypeOfGoods>() {
            @Override
            public String toString(TypeOfGoods object) {
                return object.getTypesOfCompName();
            }

            @Override
            public TypeOfGoods fromString(String string) {
                return null;
            }
        });
        cbTypeName.getSelectionModel().select(0);
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("select MaMH, TenMH, TenDV, HangSX, HinhMH, TenLoai, CauHinh, TenKhu, SoLuong\n" +
                "from MATHANG mh join LOAIMATHANG lmh on mh.MaLoai = lmh.MaLoai\n" +
                "\tjoin DONVITINH dv on mh.MaDV = dv.MaDV join KHO k on mh.MaKhu = k.Makhu");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Components(
                    resultSet.getString("MaMH"),
                    resultSet.getString("TenMH"),
                    resultSet.getString("TenDV"),
                    resultSet.getString("HangSX"),
                    resultSet.getString("HinhMH"),
                    resultSet.getString("TenLoai"),
                    resultSet.getString("CauHinh"),
                    resultSet.getString("TenKhu"),
                    resultSet.getString("SoLuong")
            ));
        }
        resultSet.close();
        //dbConn.close();
    }

    @FXML
    //Hàm refresh xóa text
    public void refresh() {
        btnDELETE.setDisable(true);
        btnUPDATE.setDisable(true);
        txtComponentID.setText("");
        txtComponentConfig.setText("");
        txtComponentName.setText("");
        txtComponentMaker.setText("");
        txtNumOfComp.setText("");

        cbAreaName.getSelectionModel().select(0);
        cbTypeName.getSelectionModel().select(0);
        cbUnit.getSelectionModel().select(0);
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        btnDELETE.setDisable(false);
        btnUPDATE.setDisable(false);
        Components selectedRow = tbvComponent.getSelectionModel().getSelectedItem();
        txtComponentID.setText(selectedRow.getComponentID());
        txtComponentConfig.setText(selectedRow.getCompInfo());
        txtComponentName.setText(selectedRow.getComponentName());
        txtComponentMaker.setText(selectedRow.getCompMaker());
        txtNumOfComp.setText(selectedRow.getNumOfComp());

        for (TypeOfGoods type:
                cbTypeName.getItems()) {
            if (type.getTypesOfCompName().matches(selectedRow.getTypesOfComp())){
                cbTypeName.getSelectionModel().select(type);
                break;
            }
        }

        for (Stock area:
                cbAreaName.getItems()) {
            if (area.getAreaName().matches(selectedRow.getAreaName())){
                cbAreaName.getSelectionModel().select(area);
                break;
            }
        }

        for (CompUnit unit:
                cbUnit.getItems()) {
            if (unit.getUnitName().matches(selectedRow.getUnit())){
                cbUnit.getSelectionModel().select(unit);
                break;
            }
        }
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "", cauhinh = "", loai = "", makhu = "", hangsx= "", donvi = "", hinh= "", soluong= "0";
        try {
            id = txtComponentID.getText();
            ten = txtComponentName.getText();
            cauhinh = txtComponentConfig.getText();
            loai = cbTypeName.getSelectionModel().getSelectedItem().getTypesOfCompID();
            makhu = cbAreaName.getSelectionModel().getSelectedItem().getAreaCode();
            hangsx = txtComponentMaker.getText();
            donvi = cbUnit.getSelectionModel().getSelectedItem().getUnitCode();
            if (txtComponentID.getText().isEmpty() || txtComponentName.getText().isEmpty()
                    || txtComponentConfig.getText().isEmpty() || txtComponentMaker.getText().isEmpty()
                    || cbTypeName.getSelectionModel().getSelectedIndex() == 0 || cbAreaName.getSelectionModel().getSelectedIndex() == 0
                    || cbUnit.getSelectionModel().getSelectedIndex() == 0)
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
            else {
                String[] dataInsert = {id, ten, hangsx, donvi, cauhinh, hinh, loai, makhu, soluong};
                int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "MATHANG");
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
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Update dữ liệu
    public void updateData() {
        String id = "", ten = "", cauhinh = "", loai = "", makhu = "", hangsx= "", donvi = "", hinh= "", soluong= "";
        try {
            id = txtComponentID.getText();
            ten = txtComponentName.getText();
            cauhinh = txtComponentConfig.getText();
            loai = cbTypeName.getSelectionModel().getSelectedItem().getTypesOfCompID();
            makhu = cbAreaName.getSelectionModel().getSelectedItem().getAreaCode();
            hangsx = txtComponentMaker.getText();
            donvi = cbUnit.getSelectionModel().getSelectedItem().getUnitCode();
            if (txtComponentID.getText().isEmpty() || txtComponentName.getText().isEmpty()
                    || txtComponentConfig.getText().isEmpty() || txtNumOfComp.getText().isEmpty()
                    || txtComponentMaker.getText().isEmpty() || cbTypeName == null
                    || cbAreaName == null || cbUnit == null)
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            }
            else {
                String[] dataUpdate = {id, ten, hangsx, donvi, cauhinh, hinh, loai, makhu};
                String[] colLabel = {"MaMH", "TenMH", "HangSX", "MaDV", "CauHinh", "HinhMH", "MaLoai", "MaKhu"};
                int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "MATHANG");
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
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    //Delete dữ liệu
    public void deleteData() {
        if (txtComponentID.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        else {
            String[] dataDelete = {txtComponentID.getText()};
            int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "MATHANG", "MaMH");
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
    public void setBtnSEARCH (ActionEvent event)throws Exception{

    }

    public void setBtnREFRESH(ActionEvent actionEvent) {
        refresh();
    }
}
