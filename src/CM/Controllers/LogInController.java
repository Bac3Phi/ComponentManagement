package CM.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class LogInController {

    @FXML
    public Button btnLogIn;
    public TextField txtEmail;
    public PasswordField txtPwd;
    public AnchorPane loginScreen;

    @FXML
    public void LogIn (ActionEvent event)throws Exception{
        Alert alert;
        try {
            String email = txtEmail.getText();
            String password = txtPwd.getText();
            if (email.equals("abc") && password.equals("123"))
            {
                Parent root = FXMLLoader.load(getClass().getResource("/CM/Views/MainView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root, 1280, 720);
                stage.setScene(scene);
                stage.setTitle("SMILE Application");
                stage.show();
            }
            else
            {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Email or Password invalid !!!");
                alert.show();
            }
            btnLogIn.setDefaultButton(true);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
