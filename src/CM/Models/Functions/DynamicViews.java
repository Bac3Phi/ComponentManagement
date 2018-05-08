package CM.Models.Functions;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DynamicViews {
    private DynamicViews(){}
    public static void loadBorderCenter(BorderPane borderPane, String resource) throws IOException {
        Parent view = FXMLLoader.load(new DynamicViews().getClass().getResource("/CM/Views/"+resource+".fxml"));
        borderPane.setCenter(view);
    }
}
