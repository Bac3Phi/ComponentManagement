package CM.Functions;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



public  class SmileNotification {
    public static void creatingNotification(String title,String message,NotificationType notificationType) {
        TrayNotification tray = new TrayNotification();
        tray.setNotificationType(notificationType);
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.millis(1000));
        tray.showAndWait();
    }
}
