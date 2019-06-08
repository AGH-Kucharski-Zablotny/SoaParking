package pl.agh.soa.tasks;

import pl.agh.soa.dao.PaymentsDAO;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.dto.PaymentsData;
import pl.agh.soa.ejb.services.impl.PaymentManagerBean;

import java.util.Date;
import java.util.TimerTask;

public class PaymentCheckTask extends TimerTask {

    private ParksData parksData;
    private PaymentManagerBean paymentManagerBean;

    public PaymentCheckTask(ParksData parksData, PaymentManagerBean paymentManagerBean) {
        this.parksData = parksData;
        this.paymentManagerBean = paymentManagerBean;
    }

    @Override
    public void run() {
        PaymentsData paymentForPark = PaymentsDAO.getInstance().getLatestPaymentForPark(parksData.getId());
        if (paymentForPark == null) {
            paymentManagerBean.sendParkNotPayed(parksData);
        }
    }
}
