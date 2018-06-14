package CM.Controllers;

import CM.Functions.GenerateID;
import CM.Functions.SmileNotification;
import CM.Models.DataProvider;
import CM.Models.Providers;
import com.jfoenix.controls.JFXAlert;
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

import javax.management.Notification;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ProviderManagerController implements Initializable {
    @FXML
    private JFXTextField txtProviderID;

    @FXML
    private JFXTextField txtProviderName;

    @FXML
    private JFXTextField txtProviderAddress;

    @FXML
    private JFXTextField txtProviderEmail;

    @FXML
    private JFXTextField txtProviderPhone;

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

    @FXML
    public TableView<Providers> tbvProviders;
    public TableColumn<Providers, String> colProvidersID, colProvidersName,
            colProvidersAddress, colProvidersEmail, colProvidersPhone;
    public AnchorPane paneProviderManagement;

    DataProvider dbConn;
    public static ObservableList<Providers> data;
    ResultSet resultSet;


    @FXML
    void setBtnADD(ActionEvent event) {
        if (progressPersonal.getProgress() < 0.9) {
            SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            return;
       }
        insertData();
        if (btnADD.isPressed()) {
            refresh();
        }
    }

    @FXML
    void setBtnDELETE(ActionEvent event) {
        if (progressPersonal.getProgress() < 0.9) {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            return;
        }
        deleteData();
        if (btnDELETE.isPressed()) {
            refresh();
        }
    }

    @FXML
    void setBtnPRINT(ActionEvent event) {

    }

    @FXML
    void setBtnREFRESH(ActionEvent event) {
        refresh();
        try {
            showData();
        } catch (SQLException e) {}
        catch (IOException ex) {}
    }

    @FXML
    void setBtnSEARCH(ActionEvent event) {
        //tabPaneEx.getSelectionModel().select(1);
    }

    @FXML
    void setBtnUPDATE(ActionEvent event) {
        if (progressPersonal.getProgress() < 0.9) {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
            return;
        }
        updateData();
        if (btnUPDATE.isPressed()) {
            refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnDELETE.setDisable(true);
        btnUPDATE.setDisable(true);

        dbConn = new DataProvider();
        paneProviderManagement = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvProviders.setEditable(false);
        txtProviderID.setEditable(false);
        txtProviderID.setText(GenerateID.create("NHACUNGCAP","MaNCC","NCC"));

        colProvidersID.setCellValueFactory(new PropertyValueFactory<>("ProvidersID"));
        colProvidersName.setCellValueFactory(new PropertyValueFactory<>("ProvidersName"));
        colProvidersAddress.setCellValueFactory(new PropertyValueFactory<>("ProvidersAddress"));
        colProvidersEmail.setCellValueFactory(new PropertyValueFactory<>("ProvidersEmail"));
        colProvidersPhone.setCellValueFactory(new PropertyValueFactory<>("ProvidersPhone"));

        try {
            showData();
        }
        catch (IOException io){}
        catch (SQLException e) {}

        tbvProviders.setItems(data);
        tbvProviders.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
    private void updateProgress() {

        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        //progressPersonal.setProgress(0.00);
        double sum_progress = progress1 + progress2 + progress3 + progress4 + progress5 ;
        txtProviderID.textProperty().addListener(new ChangeListener<String>() {
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
        progress1=0.2;
        txtProviderName.textProperty().addListener(new ChangeListener<String>() {
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

        txtProviderAddress.textProperty().addListener(new ChangeListener<String>() {
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

        txtProviderEmail.textProperty().addListener(new ChangeListener<String>() {
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

        txtProviderPhone.textProperty().addListener(new ChangeListener<String>() {
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
        resultSet = dbConn.getData("SELECT * FROM NHACUNGCAP");
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new Providers(
                    resultSet.getString("MaNCC"),
                    resultSet.getString("TenNCC"),
                    resultSet.getString("DiaChiNCC"),
                    resultSet.getString("EmailNCC"),
                    resultSet.getString("SoDTNCC")
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
        txtProviderAddress.setText("");
        txtProviderID.setText("");
        txtProviderName.setText("");
        txtProviderEmail.setText("");
        txtProviderPhone.setText("");
        txtProviderID.setText(GenerateID.create("NHACUNGCAP","MaNCC","NCC"));
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
        btnDELETE.setDisable(false);
        btnUPDATE.setDisable(false);

        Providers selectedRow = tbvProviders.getSelectionModel().getSelectedItem();

        txtProviderID.setText(selectedRow.getProvidersID());
        txtProviderName.setText(selectedRow.getProvidersName());
        txtProviderAddress.setText(selectedRow.getProvidersAddress());
        txtProviderEmail.setText(selectedRow.getProvidersEmail());
        txtProviderPhone.setText(selectedRow.getProvidersPhone());
    }

    @FXML
    //Thêm dữ liệu vào bảng
    public void insertData() {
        String id = "", ten = "", email = "", diachi = "", sodt = "";
        try {
            id = txtProviderID.getText();
            ten = txtProviderName.getText();
            diachi = txtProviderAddress.getText();
            email = txtProviderEmail.getText();
            sodt = txtProviderPhone.getText();
            if (txtProviderID.getText().isEmpty() || txtProviderName.getText().isEmpty()
                    || txtProviderAddress.getText().isEmpty() || txtProviderEmail.getText().isEmpty()
                    || txtProviderPhone.getText().isEmpty())
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, ten, diachi, email, sodt};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "NHACUNGCAP");
        if (isInserted > 0) {
            SmileNotification.creatingNotification("Thông báo","Thêm Dữ Liệu Thành Công",NotificationType.SUCCESS);
        }
        else
        {
            SmileNotification.creatingNotification("Thông báo","Thêm dữ liệu không thành công",NotificationType.ERROR);
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
            id = txtProviderID.getText();
            ten = txtProviderName.getText();
            diachi = txtProviderAddress.getText();
            email = txtProviderEmail.getText();
            sodt = txtProviderPhone.getText();
            if (txtProviderID.getText().isEmpty() || txtProviderName.getText().isEmpty()
                    || txtProviderAddress.getText().isEmpty() || txtProviderEmail.getText().isEmpty()
                    || txtProviderPhone.getText().isEmpty())
            {
                SmileNotification.creatingNotification("Thông báo","Vui lòng hoàn thành 100%",NotificationType.WARNING);
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataUpdate = {id, ten, diachi, email, sodt};
        String[] colLabel = {"MaNCC", "TenNCC", "DiaChiNCC", "EmailNCC", "SoDTNCC"};
        int isUpdated = dbConn.ExecuteSQLUpdate(colLabel, dataUpdate, "NHACUNGCAP");
        if (isUpdated > 0) {
            SmileNotification.creatingNotification("Thông báo","Cập nhật thành công",NotificationType.SUCCESS);
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
        if (txtProviderID.getText().isEmpty())
        {
            SmileNotification.creatingNotification("Thông báo","Vui lòng chọn dữ liệu",NotificationType.WARNING);
        }
        String[] dataDelete = {txtProviderID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "NHACUNGCAP", "MaNCC");
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

}
