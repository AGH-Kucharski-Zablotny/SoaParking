package pl.agh.soa.ejb.services;

import java.io.Serializable;

public interface ApplicationManager {
    enum Application {
        ACCOUNT_MANAGER,
        PARKS_MANAGER,
        PAYMENT_MANAGER,
        SLOT_MANAGER
    }

    void registerApplication(Application applicationType, String urlWithPort);
    String getApplicationUrl(Application applicationType);
    void unregisterApplication(Application applicationType);
}
