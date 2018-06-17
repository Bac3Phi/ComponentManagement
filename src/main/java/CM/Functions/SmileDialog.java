package CM.Functions;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.util.Optional;

public class SmileDialog {

    public static boolean create(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông Báo");
        alert.setHeaderText("Vui lòng xác nhận");
        alert.setContentText("Bạn có chắc chắn muốn thực hiện thao tác xóa?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            return true;
        }
        else{
            return false;
        }
    }

    /*static Label label = new Label();

    public static void create(StackPane deptStackPane,final StringProperty result){
        label.setText("Bạn có chắc chắn muốn Xóa?");
        Text t = new Text("Ò ó o o Kiểm Tra Lại nào");
        t.setStyle("-fx-font-size:14px;");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(t);
        dialogLayout.setBody(label);
        JFXDialog dialog = new JFXDialog(deptStackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        JFXButton closeButton = new JFXButton("Hủy");
        closeButton.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;");

        JFXButton addBtn = new JFXButton("Xóa");
        addBtn.setStyle("-fx-button-type: RAISED;-fx-background-color: rgb(77,102,204);-fx-font-size: 14px;-fx-text-fill: WHITE;"+ "");
        closeButton.setOnAction((ActionEvent event1) -> {
            dialog.close();
            result.set("CANCEL");

        });

        addBtn.setOnAction((ActionEvent event1) -> {
            dialog.close();
            result.set("OK");

        });
        HBox box=new HBox();
        box.setSpacing(20);
        box.setPrefSize(200, 50);
        box.setAlignment(Pos.CENTER_RIGHT);
        box.getChildren().addAll(addBtn,closeButton);

        dialogLayout.setActions(box);

        dialog.show();

    }*/
}
