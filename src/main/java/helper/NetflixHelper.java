package helper;

import helper.model.Response;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class NetflixHelper implements Runnable {
    private List<Response> responses = new ArrayList<Response>();
    private KieSession kieSession;
    private boolean initialized;

    public KieSession getKieSession() {
        return kieSession;
    }

    public void setup() {
        List<String> answers = new ArrayList<String>();
        answers.add("comedy");
        answers.add("drama");
        answers.add("both");
        responses.add(new Response("drama or comedy? (type 'both' if you like a little of both)", "init", answers));
        initialized = true;
    }

    @Override
    public void run() {
        if (!initialized) {
            setup();
        }
        try {
            sleep(1000);
            Logger.getLogger(NetflixHelper.class).setLevel(Level.OFF);
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

}
