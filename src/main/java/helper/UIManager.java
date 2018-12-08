package helper;
import helper.model.Response;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UIManager {

    private UIController uiController;

    public void initializeUI(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UIController.fxml"));
        Parent root = loader.load();
        uiController = loader.getController();
        uiController.setUiManager(this);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public String getAnswer(String question) {
        uiController.SysMessage.setText(question);
        return uiController.getAnswer();
    }
}
