package helper;

import helper.model.Response;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class NetflixHelper {
    private static List<Response> responses = new ArrayList<Response>();
    private static UIManager uiManager;
    private static KieSession kieSession;

    public static void setup() {
        List<String> answers = new ArrayList<String>();
        answers.add("comedy");
        answers.add("drama");
        answers.add("both");
        responses.add(new Response("drama or comedy? (type 'both' if you like a little of both)", "init", answers));
    }

    public static void run() {
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            kieSession = kieContainer.newKieSession("ksession-rules");
            for (Response r: responses) {
                kieSession.insert(r);
            }
            kieSession.fireAllRules();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static Response getResponse(Response response) {
        String answer = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean correctAnswer = false;
        while(!correctAnswer){
            System.out.println(response.getQuestion());
            try {
                answer = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String expectedAnswer : response.getAnswers()) {
                if(answer.equals(expectedAnswer)) correctAnswer = true;
            }
        }
        response.setAnswer(answer);
        return response;
    }

/*    public static void processAnswer(String answer){
        System.out.println(answer);
        boolean correctAnswer = false;
        for (String expectedAnswer : currentQuestion.getAnswers()) {
            if(answer.equals(expectedAnswer)) correctAnswer = true;
        }
        if(correctAnswer) {
            currentQuestion.setAnswer(answer);
            NetflixHelper.getResponse(currentQuestion);
        }
        else
        {
            System.out.println("Wrong input");
        }

    }*/

    /*

    FOR USER INTERFACE

    public static Response getResponse(Response response) {
        GetAnswer answerThread = new GetAnswer();
        answerThread.uiManager = uiManager;
        answerThread.response = response;
        answerThread.start();

        response.setAnswer(answerThread.getAnswer());
        return response;

        /*boolean correctAnswer = false;
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
        return response;
    }*/

    public static void setUiManager(UIManager uiManager) {
        NetflixHelper.uiManager = uiManager;
    }

}
