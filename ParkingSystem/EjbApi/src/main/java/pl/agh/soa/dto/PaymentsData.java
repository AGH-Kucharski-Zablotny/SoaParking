package pl.agh.soa.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Payments")
@Access(AccessType.FIELD)
public class PaymentsData
{
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "DateBoughtTo", nullable = false)
    private Date dateBoughtTo;

    @OneToOne
    private ParksData parksData;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
