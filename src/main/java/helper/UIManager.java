package helper;
import helper.model.Response;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIManager {
    public static final Object commonLock = new Object();

    public void awaitUserResponse() {
        synchronized (UIManager.commonLock) {
            try {
                UIManager.commonLock.wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupted waiting for response...");
            }
        }
    }
}
