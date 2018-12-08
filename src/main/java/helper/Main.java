package helper;

import javafx.application.Application;
import javafx.stage.Stage;
import static java.lang.Thread.sleep;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        UIManager uiManager = new UIManager();
        uiManager.initializeUI(stage);
        NetflixHelper.setUiManager(uiManager);
        NetflixHelper.setup();
        NetflixHelper.run();
    }
}
