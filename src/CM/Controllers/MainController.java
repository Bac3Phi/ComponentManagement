package CM.Controllers;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXToolbar;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController extends Application implements Initializable {
    @FXML
    private StackPane stackPane;

    @FXML
    private VBox overflowContainer;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnExit;

    @FXML
    private JFXToolbar toolBar;

    @FXML
    private HBox toolBarRight;

    @FXML
    private Label lblMenu;

    @FXML
    private AnchorPane sideAnchor;

    @FXML
    private JFXButton btnComponent;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnProvider;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnReport;

    @FXML
    private AnchorPane holderPane;
    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void logOff(ActionEvent event) {

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private Label lblDash;
    private AnchorPane component, customer,provider,employee,report;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXRippler fXRippler = new JFXRippler(lblDash);
        JFXRippler fXRippler2 = new JFXRippler(lblMenu);
        fXRippler2.setMaskType((JFXRippler.RipplerMask.RECT));
        sideAnchor.getChildren().add(fXRippler);
        toolBarRight.getChildren().add(fXRippler2);
        openMenus();
        createPages();
    }

    private void createPages() {
        try {
            component = FXMLLoader.load(getClass().getResource("../Views/ComponentView.fxml"));
            customer = FXMLLoader.load(getClass().getResource("../Views/CustomerView.fxml"));

            //set up default node on page load
            setNode(component);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void openMenus() {
        JFXPopup popup = new JFXPopup();
        popup.setPopupContent(overflowContainer);
//        popup.setPopupContainer(stackPane);
//        popup.setSource(lblMenu);
        lblMenu.setOnMouseClicked((MouseEvent e) -> {
            popup.show(lblMenu,JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -10, 40);

        });

    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    void openComponent(ActionEvent event) {
        setNode(component);
    }

    @FXML
    void openCustomer(ActionEvent event) {
        setNode(customer);
    }

    @FXML
    void openEmployee(ActionEvent event) {

    }

    @FXML
    void openProvider(ActionEvent event) {

    }

    @FXML
    void openReport(ActionEvent event) {

    }
}
