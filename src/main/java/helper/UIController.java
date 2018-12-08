package helper;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    @FXML
    TextField UserInput;
    @FXML
    Label SysMessage;

    private UIManager uiManager;
    private String answer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SysMessage.setText("drama or comedy? (type 'both' if you like a little of both)");
        answer = "";
        /*UserInput.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    synchronized (answer) {
                        answer = UserInput.getText();
                        answer.notify();
                        UserInput.clear();
                    }
                }
            }
        });*/
        System.out.println("UI Initialized");
    }

    @FXML
    public void userInputReadOnEnter(KeyEvent ke)
    {
        if (ke.getCode().equals(KeyCode.ENTER))
        {
            System.out.println("Pressed enter");
            //NetflixHelper.processAnswer(UserInput.getText());
            synchronized (answer) {
                answer = UserInput.getText();
                answer.notify();
            }
        }
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public String getAnswer() {
        synchronized (answer) {
            return answer;
        }
        //return answer;
    }
}
