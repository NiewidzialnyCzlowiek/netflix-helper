package helper.controller;

import helper.Main;
import helper.UIManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class MainPageController {
    public Label questionLabel;
    public ChoiceBox<String> availableAnswersChoiceBox;
    private ObservableList<String> availableAnswers = FXCollections.observableArrayList();

    public void startButton_onAction(ActionEvent actionEvent) {
        availableAnswersChoiceBox.setItems(availableAnswers);
        Main.runNetflixHelper();
    }

    public void newQuestion() {
        questionLabel.setText(Main.getCurrentResponse().getQuestion());
        availableAnswers.setAll(Main.getCurrentResponse().getAnswers());
        availableAnswersChoiceBox.getSelectionModel().selectFirst();
    }

    public void submitButton_onAction(ActionEvent actionEvent) {
        if(availableAnswers.isEmpty())
            return;
        synchronized (UIManager.commonLock) {
            Main.getCurrentResponse().setAnswer(availableAnswersChoiceBox.getSelectionModel().getSelectedItem());
            UIManager.commonLock.notify();
        }
    }

    public void showAdvice(String advice) {
        availableAnswers.clear();
        questionLabel.setText(advice);
    }
}
