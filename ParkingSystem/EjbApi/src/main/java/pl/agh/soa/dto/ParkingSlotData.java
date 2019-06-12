package pl.agh.soa.dto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@Entity
@Table(name = "ParkingSlots")
@Access(AccessType.FIELD)
public class ParkingSlotData extends AbstractDTO
{
    public interface SlotStatus
    {
        String EMPTY = "E";
        String TAKEN = "T";
        String PARKED = "P";
        String OVERDUE = "O";
    }

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "Region", nullable = false)
    private int region;

    @Column(name = "DateRemoved")
    @XmlTransient
    private Date dateRemoved;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getRegion()
    {
        return region;
    }

    public void setRegion(int region)
    {
        this.region = region;
    }

    public Date getDateRemoved() {
        return dateRemoved;
    }

    public void setDateRemoved(Date dateRemoved) {
        this.dateRemoved = dateRemoved;
    }
}
