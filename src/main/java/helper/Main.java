package helper;

import helper.controller.MainPageController;
import helper.model.Response;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final MainPageController mainPageController = new MainPageController();
    private static Thread netflixHelperThread;


    private static Response currentResponse;

    public static Response getCurrentResponse() {
        return currentResponse;
    }
    public static void setCurrentResponse(Response currentResponse) {
        Main.currentResponse = currentResponse;
    }

    public static void runNetflixHelper() {
        if (netflixHelperThread != null) {
            stopNetflixHelper();
            netflixHelperThread = null;
        }
        NetflixHelper netflixHelper = new NetflixHelper();
        netflixHelperThread = new Thread(netflixHelper);
        netflixHelperThread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainPage.fxml"));
        loader.setController(mainPageController);
        Scene scene = new Scene(loader.load(), 1000, 800);
        stage.setTitle("Netflix Helper");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Main.stopNetflixHelper());
        stage.show();
    }

    private static void stopNetflixHelper() {
        if(netflixHelperThread == null)
            return;
        try {
            netflixHelperThread.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(netflixHelperThread.isAlive()) {
            netflixHelperThread.interrupt();
        }
    }

    public static Response getUserResponse(Response response) {
        Main.setCurrentResponse(response);
        Platform.runLater(() -> mainPageController.newQuestion());
        UIManager uiManager = new UIManager();
        uiManager.awaitUserResponse();
        return currentResponse;
    }

    public static void showAdvice(String advice) {
        Platform.runLater(() -> mainPageController.showAdvice(advice));
    }
}
