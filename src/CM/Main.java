package CM;

import CM.Functions.CustomerFunction;
import CM.Functions.DbConnection;
import CM.Models.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.function.Function;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Views/MainView.fxml"));
        primaryStage.setTitle("Component Management");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
     /*   CustomerFunction customerFunction = new CustomerFunction();
        Customer Long = new Customer("KH002","Dang Hoang Long","TP HCM","Long97@gmail.com","039999292");
        customerFunction.insertCustomer(Long);*/
        launch(args);
    }
}
