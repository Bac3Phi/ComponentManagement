package CM.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

public class LogInController implements Initializable{

    @FXML
    public Button btnLogIn;
    public TextField txtEmail;
    public PasswordField txtPwd;
    public AnchorPane loginScreen;
    public ComboBox<String> cbPermission;
    ObservableList<String> list = FXCollections.observableArrayList("Admin", "Employee", "Manager");

    @FXML
    public void LogIn (ActionEvent event)throws Exception{
        Alert alert;
        try {
            String email = txtEmail.getText();
            String password = txtPwd.getText();

            if (email.equals("abc") && password.equals("123"))
            {
                Parent root = FXMLLoader.load(getClass().getResource("/CM/Views/CustomerView.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root, 1280, 720);
                stage.setScene(scene);
                stage.setTitle("SMILE Application");
                stage.show();
            }
            else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Email or Password invalid !!!");
                alert.show();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resource){
        cbPermission.setItems(list);
    }

}
