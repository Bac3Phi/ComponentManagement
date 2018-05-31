package CM.Controllers;

import CM.Functions.SmileNotification;
import CM.Models.Customer;
import CM.Models.DataProvider;
import com.jfoenix.controls.JFXButton;
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
import tray.notification.NotificationType;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import static CM.Controllers.CustomerController.tabPaneEx;

public class CustomerManagerController implements Initializable {
    Alert alert;
    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtCustomerAddress;

    @FXML
    private JFXTextField txtCustomerEmail;

    @FXML
    private JFXTextField txtCustomerPhone;

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
    public TableView<Customer> tbvCustomer;
    public TableColumn<Customer, String> colCustomerID, colCustomerName, colCustomerAddress, colCustomerEmail, colCustomerPhone;
    public AnchorPane paneCustomerManagement;
    @FXML
    private ProgressBar progressPersonal;
    private static double progress1 = 0;
    private static double progress2 = 0;
    private static double progress3 = 0;
    private static double progress4 = 0;
    private static double progress5 = 0;
    @FXML
    private Label lblComplete;

    DataProvider dbConn;
    ObservableList<Customer> data;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        btnDELETE.setDisable(true);
        btnUPDATE.setDisable(true);
        dbConn = new DataProvider();
        paneCustomerManagement = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvCustomer.setEditable(false);

        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("CustomerAddress"));
        colCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("CustomerEmail"));
        colCustomerPhone.setCellValueFactory(new PropertyValueFactory<>("CustomerPhoneNo"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvCustomer.setItems(data);
        tbvCustomer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                getSelectedData();
            }
        });
        updateProgress();
    }

    private void updateProgress() {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        //progressPersonal.setProgress(0.00);
        double sum_progress = progress1 + progress2 + progress3 + progress4 + progress5 ;
        txtCustomerID.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress1 = 0.2;

                } else {
                    progress1 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        txtCustomerName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress2 = 0.2;

                } else {
                    progress2 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        txtCustomerAddress.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress3 = 0.2;

                } else {
                    progress3 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        txtCustomerEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress4 = 0.2;

                } else {
                    progress4 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });

        txtCustomerPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.isEmpty()) {
                    progress5 = 0.2;

                } else {
                    progress5 = 0.0;

                }

                double sum = ( progress1 + progress2 + progress3 + progress4 + progress5 );
                progressPersonal.setProgress(sum);
                lblComplete.setText(decimalFormat.format(sum * 100) + "% hoàn thành");
            }
        });
    }

    @FXML
    //Đổ dữ liệu vào bảng
    public void showData() throws SQLException, IOException{
        resultSet = dbConn.getData("SELECT * FROM KHACHHANG");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Customer(
                    resultSet.getString("MaKH"),
                    resultSet.getString("TenKH"),
                    resultSet.getString("DiaChi"),
                    resultSet.getString("Email"),
                    resultSet.getString("SoDT")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    @FXML
    //Hàm refresh xóa text
    public void refresh() {
        btnDELETE.setDisable(true);
        btnUPDATE.setDisable(true);
        txtCustomerPhone.setText("");
        txtCustomerID.setText("");
        txtCustomerName.setText("");
        txtCustomerEmail.setText("");
        txtCustomerAddress.setText("");
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        btnDELETE.setDisable(false);
        btnUPDATE.setDisable(false);

        Customer selectedRow = tbvCustomer.getSelectionModel().getSelectedItem();
        txtCustomerID.setText(selectedRow.getCustomerID());
        txtCustomerName.setText(selectedRow.getCustomerName());
        txtCustomerAddress.setText(selectedRow.getCustomerAddress());
        txtCustomerEmail.setText(selectedRow.getCustomerEmail());
        txtCustomerPhone.setText(selectedRow.getCustomerPhoneNo());
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "", email = "", diachi = "", sodt = "";
        try {
            id = txtCustomerID.getText();
            ten = txtCustomerName.getText();
            diachi = txtCustomerAddress.getText();
            email = txtCustomerEmail.getText();
            sodt = txtCustomerPhone.getText();
            if (txtCustomerID.getText().isEmpty() || txtCustomerName.getText().isEmpty()
                    || txtCustomerAddress.getText().isEmpty() || txtCustomerEmail.getText().isEmpty()
                    || txtCustomerPhone.getText().isEmpty())
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, ten, diachi, email, sodt};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "KHACHHANG");
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

    //Update dữ liệu
    public void updateData() {
        String id = "", ten = "", email = "", diachi = "", sodt = "";
        try {
            id = txtCustomerID.getText();
            ten = txtCustomerName.getText();
            diachi = txtCustomerAddress.getText();
            email = txtCustomerEmail.getText();
            sodt = txtCustomerPhone.getText();
            if (txtCustomerID.getText().isEmpty() || txtCustomerName.getText().isEmpty()
                    || txtCustomerAddress.getText().isEmpty() || txtCustomerEmail.getText().isEmpty()
                    || txtCustomerPhone.getText().isEmpty())
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, ten, diachi, email, sodt};
        String[] colLabel = {"MaKH", "TenKH", "DiaChi", "Email", "SoDT"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "KHACHHANG");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật dữ liệu thành công",NotificationType.SUCCESS);
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

    //Delete dữ liệu
    public void deleteData() {
        if (txtCustomerID.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        String[] dataDelete = {txtCustomerID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "KHACHHANG", "MaKH");
        if (isDeleted > 0) {
            SmileNotification.creatingNotification("Thông báo","Xóa dữ liệu thành công",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Xóa dữ liệu thất bại",NotificationType.ERROR);
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
        if (progressPersonal.getProgress() < 0.9) {
            SmileNotification.creatingNotification("Thông Báo","Vui lòng hoàn thành 100%",NotificationType.INFORMATION);
            return;
        }
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
        tabPaneEx.getSelectionModel().select(1);
    }

    public void setBtnRefresh(ActionEvent actionEvent) {
        refresh();
    }
}
