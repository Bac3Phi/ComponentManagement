package CM.Controllers;

import CM.Functions.SmileNotification;
import CM.Functions.generateID;
import CM.Models.*;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import tray.notification.NotificationType;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingInternalController implements Initializable {
    public StackPane deptStackPane4;
    public StackPane deptStackPane3;
    public StackPane deptStackPane2;
    @FXML
    private StackPane deptStackPane;

    @FXML
    private Button btnDeleteStock;
    @FXML
    private TableView<Stock> tbvStock;

    @FXML
    private TableColumn<Stock, String> colStockID;

    @FXML
    private TableColumn<Stock, String> colStockName;

    @FXML
    private Button btnAddStock;

    @FXML
    private Button btnDeleteDepartment;

    @FXML
    private Button btnAddDepartment;

    @FXML
    private TableView<Department> tbvDepartment;

    @FXML
    private TableColumn<Department, String> colDepartmentID;

    @FXML
    private TableColumn<Department, String> colDepartmentName;

    @FXML
    private Button btnDeleteTypeOfGoods;

    @FXML
    private Button btnAddTypeOfGoods;

    @FXML
    private TableView<TypeOfGoods> tbvTypeOfGoods;

    @FXML
    private TableColumn<TypeOfGoods, String> colTypeOfGoodsID;

    @FXML
    private TableColumn<TypeOfGoods, String> colTypeOfGoodsName;

    @FXML
    private Button btnDeleteCompUnit;

    @FXML
    private Button btnAddCompUnit;

    @FXML
    private TableView<CompUnit> tbvCompUnit;

    @FXML
    private TableColumn<CompUnit, String> colCompUnitID;

    @FXML
    private TableColumn<CompUnit, String>colCompUnitName;


    DataProvider dbConn;
    ObservableList<Stock> dataStock;
    ObservableList<Department> dataDepartment;
    ObservableList<TypeOfGoods> dataTypeOfGoods;
    ObservableList<CompUnit> dataCompUnit;
    ResultSet resultSet;

    protected String stockID;
    protected String stockName;
    protected String departmentID;
    protected String departmentName;
    protected String typeofgoodsID;
    protected String typeofgoodsName;
    protected String compUnitID;
    protected String compUnitName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        dataStock = FXCollections.observableArrayList();
        dataCompUnit = FXCollections.observableArrayList();
        dataDepartment = FXCollections.observableArrayList();
        dataTypeOfGoods = FXCollections.observableArrayList();


        colStockID.setCellValueFactory(new PropertyValueFactory<>("AreaCode"));
        colStockName.setCellValueFactory(new PropertyValueFactory<>("AreaName"));
        colCompUnitID.setCellValueFactory(new PropertyValueFactory<>("UnitCode"));
        colCompUnitName.setCellValueFactory(new PropertyValueFactory<>("UnitName"));
        colDepartmentID.setCellValueFactory(new PropertyValueFactory<>("DepartmentID"));
        colDepartmentName.setCellValueFactory(new PropertyValueFactory<>("DepartmentName"));
        colTypeOfGoodsID.setCellValueFactory(new PropertyValueFactory<>("TypesOfCompID"));
        colTypeOfGoodsName.setCellValueFactory(new PropertyValueFactory<>("TypesOfCompName"));


        showAllData();
        tbvStock.setItems(dataStock);
        tbvDepartment.setItems(dataDepartment);
        tbvTypeOfGoods.setItems(dataTypeOfGoods);
        tbvCompUnit.setItems(dataCompUnit);

        if (!dataStock.isEmpty()){
            tbvStock.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Stock selectedRow = tbvStock.getSelectionModel().getSelectedItem();
                    stockID = selectedRow.getAreaCode();
                    stockName = selectedRow.getAreaCode();
                }
            });
        }
        if (!dataDepartment.isEmpty()){
            tbvDepartment.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Department selectedRow = tbvDepartment.getSelectionModel().getSelectedItem();
                    departmentID = selectedRow.getDepartmentID();
                    departmentName = selectedRow.getDepartmentName();
                }
            });
        }
        if (!dataTypeOfGoods.isEmpty()){
            tbvTypeOfGoods.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    TypeOfGoods selectedRow = tbvTypeOfGoods.getSelectionModel().getSelectedItem();
                    typeofgoodsID = selectedRow.getTypesOfCompID();
                    typeofgoodsName = selectedRow.getTypesOfCompName();
                }
            });
        }
        if (!dataCompUnit.isEmpty()){
            tbvCompUnit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    CompUnit selectedRow = tbvCompUnit.getSelectionModel().getSelectedItem();
                    compUnitID = selectedRow.getUnitCode();
                    compUnitName = selectedRow.getUnitName();
                }
            });
        }
    }

    private void showData(String tableName) throws SQLException {
        resultSet = dbConn.getData("SELECT * FROM "+tableName+"");




        switch (tableName){
            case "KHO":
                dataStock.removeAll(dataStock);
                while (resultSet.next()){
                    dataStock.add(new Stock(
                            resultSet.getString("Makhu"),
                            resultSet.getString("TenKhu")
                    ));
                }
            case "PHONGBAN":
                dataDepartment.removeAll(dataDepartment);
                while (resultSet.next()){
                    dataDepartment.add(new Department(
                            resultSet.getString("MaPhong"),
                            resultSet.getString("TenPhong")
                    ));
                }
            case "LOAIMATHANG":
                dataTypeOfGoods.removeAll(dataTypeOfGoods);
                while (resultSet.next()){
                    dataTypeOfGoods.add(new TypeOfGoods(
                            resultSet.getString("MaLoai"),
                            resultSet.getString("TenLoai")
                    ));
                }
            case "DONVITINH":
                dataCompUnit.removeAll(dataCompUnit);
                while (resultSet.next()){
                    dataCompUnit.add(new CompUnit(
                            resultSet.getString("MaDV"),
                            resultSet.getString("TenDV")
                    ));
                }
        }

        resultSet.close();
        dbConn.close();
    }

    JFXTextField txtStockName;

    public void setBtnAddStock(ActionEvent actionEvent) {
        txtStockName = new JFXTextField();
        txtStockName.setPromptText("Nhâp tên khu mới của Kho");
        txtStockName.setLabelFloat(false);
        txtStockName.setPrefSize(150, 50);
        txtStockName.setPadding(new Insets(10, 5, 10, 5));
        txtStockName.setStyle("-fx-font-size:13px; -fx-font-weight:bold;-fx-text-fill:#00B3A0");
        // Heading text
        Text t = new Text("Thêm Một Khu Mới");
        t.setStyle("-fx-font-size:14px;");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(t);
        dialogLayout.setBody(txtStockName);

        JFXDialog dialog = new JFXDialog(deptStackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        // close button
        JFXButton closeButton = new JFXButton("Hủy");
        closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");
        //Add button
        JFXButton addBtn = new JFXButton("Thêm");
        addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;"
                + "");
        closeButton.setOnAction((ActionEvent event1) -> {
            dialog.close();
        });
        addBtn.setOnAction((ActionEvent event1) -> {
            try {
                stockID = generateID.create("KHO","MaKhu","K"); //Gắn Mã Tự Sinh
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stockName = txtStockName.getText();
            insertData("KHO",stockID,stockName);
            dialog.close();
        });

        HBox box=new HBox();
        box.setSpacing(20);
        box.setPrefSize(200, 50);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(addBtn,closeButton);

        dialogLayout.setActions(box);

        dialog.show();
    }
    private void insertData(String tableName,String id,String name){
        String[] dataInsert = {id, name};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, tableName);
        if (isInserted>0){
            SmileNotification.creatingNotification("Thông Báo","Thêm Dữ Liệu Thành Công",NotificationType.SUCCESS);
        }else {
            SmileNotification.creatingNotification("Thông Báo","Thêm Dữ Liệu Thất Bại",NotificationType.ERROR);
        }
        showAllData();
    }

    private void showAllData() {
        try {
            showData("KHO");
            showData("PHONGBAN");
            showData("LOAIMATHANG");
            showData("DONVITINH");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteData(String tableName,String colName,String id){
        String[] dataDelete = {id};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, tableName, colName);
        if (isDeleted>0){
            SmileNotification.creatingNotification("Thông Báo","Xóa Dữ Liệu Thành Công",NotificationType.SUCCESS);
        }else SmileNotification.creatingNotification("Thông Báo","Xóa Dữ Liệu Thất Bại",NotificationType.ERROR);
        showAllData();
    }

    public void setBtnDeleteStock(ActionEvent actionEvent) {
        deleteData("KHO","MaKhu",stockID);
    }

    public void setBtnDeleteDepartment(ActionEvent actionEvent) {
        deleteData("PHONGBAN","MaPhong",departmentID);
    }


    public void setBtnDeleteTypeOfGoods(ActionEvent actionEvent) {
        deleteData("LOAIMATHANG","MaLoai",typeofgoodsID);
    }

    public void setBtnDeleteCompUnit(ActionEvent actionEvent) {
        deleteData("DonViTinh","MaDV",compUnitID);
    }
    JFXTextField txtDepartmentName;
    public void setBtnAddDepartment(ActionEvent actionEvent) {
        txtDepartmentName = new JFXTextField();
        txtDepartmentName.setPromptText("Nhâp tên Phòng ban mới");
        txtDepartmentName.setLabelFloat(false);
        txtDepartmentName.setPrefSize(150, 50);
        txtDepartmentName.setPadding(new Insets(10, 5, 10, 5));
        txtDepartmentName.setStyle("-fx-font-size:13px; -fx-font-weight:bold;-fx-text-fill:#00B3A0");
        // Heading text
        Text t = new Text("Thêm Một Phòng Ban Mới");
        t.setStyle("-fx-font-size:14px;");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(t);
        dialogLayout.setBody(txtDepartmentName);

        JFXDialog dialog = new JFXDialog(deptStackPane2, dialogLayout, JFXDialog.DialogTransition.CENTER);
        // close button
        JFXButton closeButton = new JFXButton("Hủy");
        closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");
        //Add button
        JFXButton addBtn = new JFXButton("Thêm");
        addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;"
                + "");
        closeButton.setOnAction((ActionEvent event1) -> {
            dialog.close();
        });
        addBtn.setOnAction((ActionEvent event1) -> {
            departmentID ="K003"; //Gắn Mã Tự Sinh
            departmentName = txtDepartmentName.getText();
            insertData("PHONGBAN",departmentID,departmentName);
            dialog.close();
        });

        HBox box=new HBox();
        box.setSpacing(20);
        box.setPrefSize(200, 50);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(addBtn,closeButton);

        dialogLayout.setActions(box);

        dialog.show();
    }
    JFXTextField txtTypeOfGoods;
    public void setBtnAddTypeOfGoods(ActionEvent actionEvent) {
        txtTypeOfGoods = new JFXTextField();
        txtTypeOfGoods.setPromptText("Nhâp tên Loại mặt hàng mới");
        txtTypeOfGoods.setLabelFloat(false);
        txtTypeOfGoods.setPrefSize(150, 50);
        txtTypeOfGoods.setPadding(new Insets(10, 5, 10, 5));
        txtTypeOfGoods.setStyle("-fx-font-size:13px; -fx-font-weight:bold;-fx-text-fill:#00B3A0");
        // Heading text
        Text t = new Text("Thêm Một Loai Mặt Hàng Mới");
        t.setStyle("-fx-font-size:14px;");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(t);
        dialogLayout.setBody(txtTypeOfGoods);

        JFXDialog dialog = new JFXDialog(deptStackPane3, dialogLayout, JFXDialog.DialogTransition.CENTER);
        // close button
        JFXButton closeButton = new JFXButton("Hủy");
        closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");
        //Add button
        JFXButton addBtn = new JFXButton("Thêm");
        addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;"
                + "");
        closeButton.setOnAction((ActionEvent event1) -> {
            dialog.close();
        });
        addBtn.setOnAction((ActionEvent event1) -> {
            typeofgoodsID ="K003"; //Gắn Mã Tự Sinh
            typeofgoodsName = txtTypeOfGoods.getText();
            insertData("LOAIMATHANG",typeofgoodsID,typeofgoodsName);
            dialog.close();
        });

        HBox box=new HBox();
        box.setSpacing(20);
        box.setPrefSize(200, 50);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(addBtn,closeButton);

        dialogLayout.setActions(box);

        dialog.show();

    }
    JFXTextField txtCompUnit;
    public void setBtnAddCompUnit(ActionEvent actionEvent) {
        txtCompUnit = new JFXTextField();
        txtCompUnit.setPromptText("Nhâp tên Đơn vị tính mới");
        txtCompUnit.setLabelFloat(false);
        txtCompUnit.setPrefSize(150, 50);
        txtCompUnit.setPadding(new Insets(10, 5, 10, 5));
        txtCompUnit.setStyle("-fx-font-size:13px; -fx-font-weight:bold;-fx-text-fill:#00B3A0");
        // Heading text
        Text t = new Text("Thêm Một Đơn Vị Tính Mới");
        t.setStyle("-fx-font-size:14px;");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(t);
        dialogLayout.setBody(txtCompUnit);

        JFXDialog dialog = new JFXDialog(deptStackPane4, dialogLayout, JFXDialog.DialogTransition.CENTER);
        // close button
        JFXButton closeButton = new JFXButton("Hủy");
        closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");
        //Add button
        JFXButton addBtn = new JFXButton("Thêm");
        addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;"
                + "");
        closeButton.setOnAction((ActionEvent event1) -> {
            dialog.close();
        });
        addBtn.setOnAction((ActionEvent event1) -> {
            compUnitID ="K003"; //Gắn Mã Tự Sinh
            compUnitName = txtCompUnit.getText();
            insertData("DONVITINH",compUnitID,compUnitName);
            dialog.close();
        });

        HBox box=new HBox();
        box.setSpacing(20);
        box.setPrefSize(200, 50);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(addBtn,closeButton);

        dialogLayout.setActions(box);

        dialog.show();
    }
}
