package pl.agh.soa.dto;

import javax.persistence.*;

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
        String OVERDUED = "O";
    }

    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Status", nullable = false)
    private String status;

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
}
