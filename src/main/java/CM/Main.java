package CM;

import CM.Functions.CustomerFunction;
import CM.Functions.DbConnection;
import CM.Models.Customer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import CM.Controllers.SignInController;

import java.io.IOException;
import java.util.function.Function;

public class Main extends Application {
    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        //Scene login
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("/Views/SignInView.fxml"));
            Scene scene = new Scene(parent, 800, 450);
            Image image = new Image(String.valueOf(this.getClass().getResource("/Assets/images/Glogo.png")));
            window.getIcons().add(image);
            window.setTitle("GAMEON: Đăng Nhập");
            window.setScene(scene);
            window.setResizable(false);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
     /*   CustomerFunction customerFunction = new CustomerFunction();
        Customer Long = new Customer("KH002","Dang Hoang Long","TP HCM","Long97@gmail.com","039999292");
        customerFunction.insertCustomer(Long);*/
        launch(args);
    }


}
