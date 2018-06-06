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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    public Label lbCurrentUserName;
    public VBox TabParent;
    public Button btnREFRESH;
    @FXML
    private JFXButton btnBill;
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
    private JFXButton btnSetting;

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
        Stage stage = (Stage) stackPane.getScene().getWindow();

        Platform.setImplicitExit(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/SignInView.fxml")); // DMMMMM dau ..
        Parent root2 = null;
        try {
            root2 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene secondScene = new Scene(root2);
        Stage secondStage = new Stage();
        secondStage.setResizable(false);
        Image image = new Image(String.valueOf(this.getClass().getResource("/Assets/images/Glogo.png")));
        secondStage.getIcons().add(image);
        secondStage.setScene(secondScene); // set the scene
        secondStage.setTitle("GAMEON: Đăng Nhập");
        secondStage.show();
        stage.close();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    private Label lblDash;
    private AnchorPane component, customer,provider,employee,bill,report,setting;

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
            component = FXMLLoader.load(getClass().getResource("/Views/ComponentView.fxml"));
            customer = FXMLLoader.load(getClass().getResource("/Views/CustomerView.fxml"));
            employee = FXMLLoader.load(getClass().getResource("/Views/EmployeesView.fxml"));
            provider = FXMLLoader.load(getClass().getResource("/Views/ProviderView.fxml"));
            setting = FXMLLoader.load(getClass().getResource("/Views/SettingView.fxml"));
            bill = FXMLLoader.load(getClass().getResource("/Views/BillView.fxml"));
            report = FXMLLoader.load(getClass().getResource("/Views/ReportView.fxml"));
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

        FadeTransition ft = new FadeTransition(Duration.millis(1000));
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
        try {
            setNode(customer);
        } catch (NullPointerException e) {}

    }

    @FXML
    void openEmployee(ActionEvent event) {
        setNode(employee);
    }

    @FXML
    void openProvider(ActionEvent event) {
        setNode(provider);
    }

    @FXML
    void openReport(ActionEvent event) {
        setNode(report);

    }
    @FXML
    void openSetting(ActionEvent event) {
        setNode(setting);
    }

    @FXML
    void openBill(ActionEvent actionEvent) {
        setNode(bill);
    }

    private  String username,displayname,authname;
    public void setInfo(String UserName,String DisplayName,String AuthName){
        this.username = UserName;
        this.displayname = DisplayName;
        this.authname = AuthName;
        lbCurrentUserName.setText(displayname);
        setPermisstion(authname);
    }

    private void setPermisstion(String authname){
         if (authname.equals("Kế Toán")){
             TabParent.getChildren().remove(6);
             TabParent.getChildren().remove(2);
             TabParent.getChildren().remove(1);

         }
        if (authname.equals("Kinh doanh")){
            TabParent.getChildren().remove(3);
            TabParent.getChildren().remove(2);
        }
        if (authname.equals("Thủ kho")){
            TabParent.getChildren().remove(6);
            TabParent.getChildren().remove(4);
            TabParent.getChildren().remove(3);
            TabParent.getChildren().remove(2);
        }
    }

    public void openCustom(ActionEvent actionEvent) {
    }

    public void setbtnRefresh(ActionEvent actionEvent) throws Exception {
       createPages();
    }
}
