package CM.Controllers;

import CM.Models.DataProvider;
import CM.Models.Providers;
import com.jfoenix.controls.JFXButton;
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
import java.util.ResourceBundle;

public class ProviderManagerController implements Initializable {
    Alert alert;
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
    ObservableList<Providers> data;
    ResultSet resultSet;

    @FXML
    void setBtnADD(ActionEvent event) {
//        if (progressPersonal.getProgress() < 0.9) {
//            return;
//        }
        insertData();
        if (btnADD.isPressed()) {
            refresh();
        }
    }

    @FXML
    void setBtnDELETE(ActionEvent event) {
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
    }

    @FXML
    void setBtnSEARCH(ActionEvent event) {
        //tabPaneEx.getSelectionModel().select(1);
    }

    @FXML
    void setBtnUPDATE(ActionEvent event) {
        updateData();
        if (btnUPDATE.isPressed()) {
            refresh();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        paneProviderManagement = new AnchorPane();
        data = FXCollections.observableArrayList();
        tbvProviders.setEditable(false);

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
        //updateProgress();
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
        txtProviderAddress.setText("");
        txtProviderID.setText("");
        txtProviderName.setText("");
        txtProviderEmail.setText("");
        txtProviderPhone.setText("");
    }

    //lay thong tin du lieu duoc
    public void getSelectedData() {
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
                alert = new Alert(Alert.AlertType.WARNING, "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        String[] dataInsert = {id, ten, diachi, email, sodt};
        int isInserted = dbConn.ExecuteSQLInsert(dataInsert, "NHACUNGCAP");
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
                alert = new Alert(Alert.AlertType.WARNING,
                        "Plese fill in all the blank!!!", ButtonType.OK);
                alert.show();
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

    //Delete dữ liệu
    public void deleteData() {
        if (txtProviderID.getText().isEmpty())
        {
            alert = new Alert(Alert.AlertType.WARNING,
                    "Plese fill in the blank!!!", ButtonType.OK);
            alert.show();
        }
        String[] dataDelete = {txtProviderID.getText()};
        int isDeleted = dbConn.ExecuteSQLDelete(dataDelete, "NHACUNGCAP", "MaNCC");
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
}
