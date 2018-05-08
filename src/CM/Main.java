package CM;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        //Scene login
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader();
            Parent parent = FXMLLoader.load(getClass().getResource("Views/LogIn.fxml"));
            Scene scene = new Scene(parent, 365, 259);
            window.setTitle("SMILE - Log In");
            window.setScene(scene);
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
