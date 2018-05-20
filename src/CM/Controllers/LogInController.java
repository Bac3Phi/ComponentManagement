package CM.Controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
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
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Alert alert;
    ObservableList<String> list = FXCollections.observableArrayList("Admin", "Employee", "Manager");

    @FXML
    public void LogIn (ActionEvent event)throws Exception{
        try {
            String email = txtEmail.getText();
            String password = txtPwd.getText();

            if (email.equals("abc") && password.equals("123"))
            {
                root = FXMLLoader.load(getClass().getResource("/CM/Views/CustomerView.fxml"));
                stage = new Stage();
                scene = new Scene(root, 1280, 720);
                stage.setTitle("SMILE Application");
                stage.setScene(scene);
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

    @FXML
    public void txtLogIn(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPwd.getText();
        txtPwd.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    try {
                        if (email.equals("abc") && password.equals("123"))
                        {
                            root = FXMLLoader.load(getClass().getResource("/CM/Views/EmployeesView.fxml"));
                            stage = new Stage();
                            scene = new Scene(root, 1280, 720);
                            stage.setTitle("SMILE Application");
                            stage.setScene(scene);
                            stage.show();
                        }
                        else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Email or Password invalid !!!");
                            alert.show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resource){
        cbPermission.setItems(list);
    }

}
