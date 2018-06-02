package CM.Controllers;
import CM.Functions.SmileNotification;
import CM.Models.*;
import com.jfoenix.controls.*;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.AccessibleRole;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.rmi.runtime.Log;
import tray.notification.NotificationType;

public class SignInController  implements Initializable {
    public Hyperlink linkChangePassword;
    public StackPane deptStackPane;
    @FXML
    private JFXButton btn_SignIn;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private ImageView imgLoading;
    static DataProvider dbConn;
    static ObservableList<LogIn> data;
    static ObservableList<Authority> datainfo;
    static ResultSet resultSet;
    PauseTransition pauseTransition = new PauseTransition();
    public void btnSignIn_MousePressed(MouseEvent mouseEvent) {
        // if ( txtUsername.getText()== "abc" && txtPassword.getText()=="123"){
        imgLoading.setVisible(true);

        pauseTransition.setDuration(Duration.seconds(1.5));
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        if (checkData(username,password)){
            pauseTransition.setOnFinished(ev -> {
                completeLogin();
            });
            pauseTransition.play();
        }
        else{
            return;
        }

        //   }

    }
    private void completeLogin(){
        btn_SignIn.getScene().getWindow().hide();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/MainView.fxml")); // DMMMMM dau ..
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Image image = new Image(String.valueOf(this.getClass().getResource("../Assets/images/Glogo.png")));
            stage.getIcons().add(image);
            stage.setTitle("GAMEON : Quản Lý Linh Kiện");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dbConn = new DataProvider();
        data = FXCollections.observableArrayList();
        datainfo = FXCollections.observableArrayList();
        imgLoading.setVisible(false);
    }

    @FXML
    public void handleButtonAction(ActionEvent actionEvent) {
        //completeLogin();
    }

    public static boolean checkData(String username, String password){
        if (username.isEmpty()){
            SmileNotification.creatingNotification("Thông Báo","Vui lòng nhập tài khoản",NotificationType.ERROR);
            return false;
        }
        if (password.isEmpty()){
            SmileNotification.creatingNotification("Thông Báo","Vui lòng nhập mật khẩu",NotificationType.ERROR);
            return false;
        }
        try {

            getData(username);
            if (data.size() ==0 ){
                SmileNotification.creatingNotification("Thông Báo","Sai mật khẩu hoặc mật khẩu",NotificationType.ERROR);
                return false;
            }
            getDataInfo(String.valueOf(data.get(0).getAuthID()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (password.equals(String.valueOf(data.get(0).getPassword()))){
            return true;
        }
        else {
            SmileNotification.creatingNotification("Thông Báo","Sai mật khẩu hoặc mật khẩu",NotificationType.ERROR);
            return false;
        }

    }

    public static void getData(String username) throws SQLException {
        String query ="SELECT * FROM DANGNHAP WHERE username = '" + username  +"'";
        resultSet = dbConn.getData(query);
        data.removeAll(data);
        while (resultSet.next()){
            data.add(new LogIn(
                    resultSet.getString("MaDN"),
                    resultSet.getString("TenHienThi"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("MaPQ")
            ));
        }
        resultSet.close();
        dbConn.close();
    }
    public static void getDataInfo(String authID) throws SQLException {
        String query ="SELECT * FROM PHANQUYEN WHERE MAPQ = '" + authID  +"'";
        resultSet = dbConn.getData(query);
        datainfo.removeAll(datainfo);
        while (resultSet.next()){
            datainfo.add(new Authority(
                    resultSet.getString("MaPQ"),
                    resultSet.getString("TenPQ")
            ));
        }
        resultSet.close();
        dbConn.close();
    }

    void checkPermisstion(){

    }
    public void setLinkChangePassword(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Views/ChangePasswordView.fxml")); // DMMMMM dau ..
        Parent root2 = null;
        try {
            root2 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene secondScene = new Scene(root2);
        Stage secondStage = new Stage();
        secondStage.setResizable(false);
        secondStage.setScene(secondScene); // set the scene
        secondStage.setTitle("Thay Đổi Mật Khẩu");
        secondStage.show();
         // close the first stage (Window)
    }
}
