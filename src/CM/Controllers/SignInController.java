package CM.Controllers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignInController  implements Initializable {
    @FXML
    private JFXButton btn_SignIn;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private ImageView imgLoading;

    public void btnSignIn_MousePressed(MouseEvent mouseEvent) {
        // if ( txtUsername.getText()== "abc" && txtPassword.getText()=="123"){
        imgLoading.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(1.5));
        pauseTransition.setOnFinished(ev -> {
            completeLogin();
        });
        pauseTransition.play();
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
        imgLoading.setVisible(false);
    }

    @FXML
    public void handleButtonAction(ActionEvent actionEvent) {
        //completeLogin();
    }
}
