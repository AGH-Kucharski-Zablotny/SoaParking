package pl.agh.soa.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Parks")
@Access(AccessType.FIELD)
public class ParksData
{
    @Id
    @GeneratedValue
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "RegistrationPlate", nullable = false)
    private String registrationPlate;

    @Column(name = "DateParked", nullable = false)
    private Date dateParked;

    @OneToOne
    private ParkingSlotData parkingSlotData;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRegistrationPlate()
    {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate)
    {
        this.registrationPlate = registrationPlate;
    }

    public Date getDateParked()
    {
        return dateParked;
    }

    public void setDateParked(Date dateParked)
    {
        this.dateParked = dateParked;
    }

    public ParkingSlotData getParkingSlotData()
    {
        return parkingSlotData;
    }

    public void setParkingSlotData(ParkingSlotData parkingSlotData)
    {
        this.parkingSlotData = parkingSlotData;
    }
}
