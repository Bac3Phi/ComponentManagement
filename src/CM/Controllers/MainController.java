package CM.Controllers;

        import CM.Models.Functions.DynamicViews;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.layout.BorderPane;

        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class MainController implements Initializable{

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void show_customer(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        DynamicViews.loadBorderCenter(borderPane,"CustomerView");
    }

    public void show_Employees(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        DynamicViews.loadBorderCenter(borderPane,"EmployeesView");
    }
}
