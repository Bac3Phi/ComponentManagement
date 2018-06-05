package CM.Controllers;

import CM.Models.DataProvider;
import CM.Models.Providers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProviderSearchController implements Initializable {
    @FXML
    private ProgressBar progressPersonal;

    @FXML
    private Label lblComplete;

    @FXML
    private JFXButton btnPRINT;

    @FXML
    private JFXButton btnSEARCH;

    @FXML
    private JFXButton btnGETINFO;

    @FXML
    private JFXButton btnREFRESH;

    @FXML
    private JFXTextArea txtSEARCH;

    @FXML
    private JFXRadioButton rdbtnProviderID;

    @FXML
    private JFXRadioButton rdbtnProviderName;

    @FXML
    private JFXRadioButton rdbtnProviderAddress;

    @FXML
    private JFXRadioButton rdbtnProviderEmail;

    @FXML
    private JFXRadioButton rdbtnProviderPhone;

    @FXML
    private TableView<Providers> tbvSEARCH;

    @FXML
    private TableColumn<Providers, String> colProvidersID, colProvidersName, colProvidersAddress,
                                            colProvidersEmail, colProvidersPhone;

    ProviderManagerController pointer;
    DataProvider dbConn;
    ObservableList<Providers> list;
    ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        list = FXCollections.observableArrayList();
        tbvSEARCH.setItems(list);
        groupByRaidioButton();
        pointer = new ProviderManagerController();

        colProvidersID.setCellValueFactory(new PropertyValueFactory<>("ProvidersID"));
        colProvidersName.setCellValueFactory(new PropertyValueFactory<>("ProvidersName"));
        colProvidersAddress.setCellValueFactory(new PropertyValueFactory<>("ProvidersAddress"));
        colProvidersEmail.setCellValueFactory(new PropertyValueFactory<>("ProvidersEmail"));
        colProvidersPhone.setCellValueFactory(new PropertyValueFactory<>("ProvidersPhone"));

        tbvSEARCH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGETINFO.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Providers selectedRow = tbvSEARCH.getSelectionModel().getSelectedItem();
                        try {
                            pointer.data.removeAll(pointer.data);
                            pointer.data.add(selectedRow);
                            pointer.tbvProviders.setItems(pointer.data);
                        }
                        catch (NullPointerException e) {}
                    }
                });
            }
        });
    }

    public void searchData (String field, String str) throws SQLException {
        String query = "SELECT * FROM NHACUNGCAP WHERE " + field + " LIKE '%" + str +"%';";
        resultSet = dbConn.getData(query);
        list.removeAll(list);
        while (resultSet.next()){
            list.add(new Providers(
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

    private void groupByRaidioButton() {
        final ToggleGroup group = new ToggleGroup();
        rdbtnProviderID.setToggleGroup(group);
        rdbtnProviderID.setSelected(true);
        rdbtnProviderName.setToggleGroup(group);
        rdbtnProviderEmail.setToggleGroup(group);
        rdbtnProviderAddress.setToggleGroup(group);
        rdbtnProviderPhone.setToggleGroup(group);
    }

    public void setBtnPRINT(javafx.event.ActionEvent actionEvent) {
    }

    public void setBtnSEARCH(javafx.event.ActionEvent actionEvent) {
        String[] str = {"MaNCC", "TenNCC", "DiaChiNCC", "EmailNCC", "SoDTNCC"};
        try {
            if (rdbtnProviderID.isSelected())
                searchData(str[0], txtSEARCH.getText());
            else if (rdbtnProviderName.isSelected())
                searchData(str[1], txtSEARCH.getText());
            else if (rdbtnProviderAddress.isSelected())
                searchData(str[2], txtSEARCH.getText());
            else if (rdbtnProviderEmail.isSelected())
                searchData(str[3], txtSEARCH.getText());
            else if (rdbtnProviderPhone.isSelected())
                searchData(str[4], txtSEARCH.getText());
        } catch (SQLException e) {}
    }

    public void setBtnREFRESH(javafx.event.ActionEvent actionEvent) {
        txtSEARCH.setText(null);
        list.removeAll(list);
        rdbtnProviderID.setSelected(true);
        rdbtnProviderPhone.setSelected(false);
        rdbtnProviderAddress.setSelected(false);
        rdbtnProviderName.setSelected(false);
        rdbtnProviderEmail.setSelected(false);
    }
}
