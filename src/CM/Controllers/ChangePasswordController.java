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

    DataProvider dbConn;
    @FXML
    void setBtnCancel(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void setBtnConfirm(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPasswordOld.getText();
        String passwordNew = txtPasswordNew.getText();
        String passwordNewConfirm = txtPasswordNewConfirm.getText();


        if (SignInController.checkData(username,password)){
            if (!passwordNew.equals(passwordNewConfirm)){
                SmileNotification.creatingNotification("Thông Báo","Mật khẩu mới không khớp",NotificationType.INFORMATION);
                refresh();
                return;
            }
            if(password.equals(passwordNew)){{
                SmileNotification.creatingNotification("Thông Báo","Mật khẩu mới trùng với mật khẩu củ",NotificationType.INFORMATION);
                refresh();
                return;
            }}
            if(passwordNew.isEmpty() || passwordNewConfirm.isEmpty()){
                SmileNotification.creatingNotification("Thông Báo","Vui lòng xác nhận mật khẩu mới",NotificationType.INFORMATION);
                refresh();
                return ;
            }
            //true update
            updatePassword(username,passwordNew);
        }
        else refresh();



    }

    private void updatePassword(String username,String passwordNew) {

        String[] collable = {"Username", "Password"};
        String[] dataupdate = {username, passwordNew};
       int updatedRow= dbConn.ExecuteSQLUpdate(collable,dataupdate,"DANGNHAP");
        if (updatedRow!=0){
            SmileNotification.creatingNotification("Thông Báo","Cập nhật mật khẩu thành công",NotificationType.SUCCESS);
        }else SmileNotification.creatingNotification("Thông Báo","Cập nhật mật khẩu thất bại",NotificationType.ERROR);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();

    }
    public void refresh(){
        txtPasswordOld.setText("");
        txtPasswordNew.setText("");
        txtPasswordNewConfirm.setText("");
    }
}
