package helper;

import helper.model.Response;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

public class NetflixHelper {
    private List<Response> responses = new ArrayList<Response>();
    public void setup() {
        responses.add(new Response("drama or comedy", "drama", new ArrayList<String>()));
        responses.add(new Response("drama or comedy", "comedy", new ArrayList<String>()));
    }

    public void run() {
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("ksession-rules");
            for (Response r: responses) {
                kieSession.insert(r);
            }
            kieSession.fireAllRules();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
