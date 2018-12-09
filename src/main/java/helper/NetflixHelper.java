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

    @Override
    public void run() {
        try {
            sleep(1000);
            Logger.getLogger(NetflixHelper.class).setLevel(Level.OFF);
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();
            KieSession kieSession = kieContainer.newKieSession("ksession-rules");
            kieSession.fireAllRules();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
