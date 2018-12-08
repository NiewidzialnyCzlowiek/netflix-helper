package helper;

import helper.model.Response;

public class GetAnswer extends Thread {
    private String answer;
    public UIManager uiManager;
    public Response response;

    @Override
    public void run() {
        boolean correctAnswer = false;
        while(!correctAnswer){
            String answer = uiManager.getAnswer(response.getQuestion());
            System.out.println("Getting answer");

            synchronized (answer) {
                while(answer == "") {
                    try {
                        answer.wait();
                    } catch (InterruptedException e) {
                        System.out.println("answer.wait error");
                    }
                }
            }
            for (String expectedAnswer : response.getAnswers()) {
                if(answer.equals(expectedAnswer)) correctAnswer = true;
            }
        }
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
