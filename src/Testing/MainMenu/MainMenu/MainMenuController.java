package Testing.MainMenu.MainMenu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends Application implements Initializable {
    @FXML
    private VBox overflowContainer;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnExit;

    @FXML
    private AnchorPane sideAnchor;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void logOff(ActionEvent event) {

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        JFXRippler fXRippler = new JFXRippler(lblDash);
//        JFXRippler fXRippler2 = new JFXRippler(lblMenu);
//        fXRippler2.setMaskType((JFXRippler.RipplerMask.RECT));
//        sideAnchor.getChildren().add(fXRippler);
//        toolBarRight.getChildren().add(fXRippler2);
//        openMenus();
//        createPages();
    }
    private void openMenus() {
//        JFXPopup popup = new JFXPopup();
//        popup.setContent(overflowContainer);
//        popup.setPopupContainer(stackPane);
//        popup.setSource(lblMenu);
//        lblMenu.setOnMouseClicked((MouseEvent e) -> {
//            popup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -10, 40);
//        });

    }
}
