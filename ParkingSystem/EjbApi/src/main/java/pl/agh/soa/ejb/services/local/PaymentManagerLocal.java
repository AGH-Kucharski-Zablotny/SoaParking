package pl.agh.soa.ejb.services.local;

import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.PaymentManager;

import javax.ejb.Local;

@Local
public interface PaymentManagerLocal extends PaymentManager {

}
