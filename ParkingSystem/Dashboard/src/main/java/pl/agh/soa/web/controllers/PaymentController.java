package pl.agh.soa.web.controllers;

import pl.agh.soa.dto.PaymentsData;
import pl.agh.soa.dto.RatesData;
import pl.agh.soa.ejb.services.remote.PaymentManagerRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named("PaymentController")
@SessionScoped
public class PaymentController implements Serializable {
    @EJB(lookup = "java:global/EjbPaymentImpl-1.0/PaymentManagerBean!pl.agh.soa.ejb.services.remote.PaymentManagerRemote")
    private PaymentManagerRemote paymentManager;

    private boolean editable;

    private RatesData newRate;

    private List<RatesData> rates;

    public List<RatesData> getRates() {
        return rates;
    }

    public void setRates(List<RatesData> rates) {
        this.rates = rates;
    }

    public boolean isEditable() {
        return editable;
    }

    @PostConstruct
    public void init() {
        this.rates = paymentManager.getRates().stream()
                .sorted(Comparator.comparing(RatesData::getHours))
                .collect(Collectors.toList());
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        if (editable) {
            rates.add(new RatesData());
        }
    }

    public Date getPaymentForPark(Integer parkId) {
        if (parkId == null || parkId == 0) {
            return null;
        }
        return paymentManager.getDateParkedTo(parkId);
    }

    public void updateRates() {
        rates.forEach(r -> {
            if (r.getHours() != null) {
                paymentManager.setRate(r.getAmount(), r.getHours());
            }
        });
        this.rates = paymentManager.getRates().stream()
                .sorted(Comparator.comparing(RatesData::getHours))
                .collect(Collectors.toList());
        setEditable(false);
    }
}
