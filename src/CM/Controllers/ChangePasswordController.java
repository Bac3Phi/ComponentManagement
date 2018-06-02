package CM.Controllers;

import CM.Functions.SmileNotification;
import CM.Models.DataProvider;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tray.notification.NotificationType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePasswordController implements Initializable {

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPasswordOld;

    @FXML
    private JFXPasswordField txtPasswordNew;

    @FXML
    private JFXPasswordField txtPasswordNewConfirm;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnConfirm;

    @FXML
    void setBtnCancel(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void setBtnConfirm(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPasswordOld.getText();
        if (SignInController.checkData(username,password)){
            //true update
            //updatePassword();
        }



    }

    private void updatePassword(String username,String passwordNew) {
        String query = "UPDATE DangNhap SET password = '"+passwordNew+"' WHERE username = '"+username+"'";

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
