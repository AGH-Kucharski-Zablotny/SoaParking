package pl.agh.soa.web.controllers;

import pl.agh.soa.dto.PaymentsData;
import pl.agh.soa.ejb.services.remote.PaymentManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named("PaymentController")
@SessionScoped
public class PaymentController implements Serializable {
    @EJB(lookup = "java:global/EjbPaymentImpl-1.0/PaymentManagerBean!pl.agh.soa.ejb.services.remote.PaymentManagerRemote")
    private PaymentManagerRemote paymentManager;

    public Date getPaymentForPark(Integer parkId) {
        if (parkId == null || parkId == 0) {
            return null;
        }
        return paymentManager.getDateParkedTo(parkId);
    }
}
