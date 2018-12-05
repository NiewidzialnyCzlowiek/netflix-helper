package helper.model;

import java.util.List;

public class Response {
    private String question;
    private String answer;
    private List<String> answers;

    public Response(String question, String answer, List<String> answers) {
        this.question = question;
        this.answer = answer;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
