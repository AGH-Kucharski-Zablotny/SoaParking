package pl.agh.soa.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payments")
@Access(AccessType.FIELD)
public class PaymentsData extends AbstractDTO
{
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "PaymentDate", nullable = false)
    private Date paymentDate;

    @Column(name = "DateBoughtTo", nullable = false)
    private Date dateBoughtTo;

    @ManyToOne
    private ParksData parksData;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public Date getDateBoughtTo()
    {
        return dateBoughtTo;
    }

    public void setDateBoughtTo(Date dateBoughtTo)
    {
        this.dateBoughtTo = dateBoughtTo;
    }

    public ParksData getParksData()
    {
        return parksData;
    }

    public void setParksData(ParksData parksData)
    {
        this.parksData = parksData;
    }
}
