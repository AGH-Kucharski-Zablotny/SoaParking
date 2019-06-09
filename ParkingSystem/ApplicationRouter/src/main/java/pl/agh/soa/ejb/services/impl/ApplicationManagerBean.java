package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.ejb.services.ApplicationManager;

import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Remote(ApplicationManager.class)
public class ApplicationManagerBean implements ApplicationManager {
    private Map<Application, String> urlsByApp = new HashMap<>();

    @Override
    public void registerApplication(Application applicationType, String urlWithPort) {
        if (urlsByApp.containsKey(applicationType)) {
            urlsByApp.replace(applicationType, urlWithPort);
        }
        else {
            urlsByApp.put(applicationType, urlWithPort);
        }
    }

    @Override
    public String getApplicationUrl(Application applicationType) {
        return urlsByApp.getOrDefault(applicationType, null);
    }

    @Override
    public void unregisterApplication(Application applicationType) {
        urlsByApp.remove(applicationType);
    }
}
