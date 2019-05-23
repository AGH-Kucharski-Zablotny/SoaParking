package pl.agh.soa.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ParkingLot")
@Access(AccessType.FIELD)
public class ParkingLotData implements Serializable {

    private static final long serialVersionUID = -8482198331111122768L;

    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
