package pl.agh.soa.dto;

import javax.persistence.*;

@Entity
@Table(name = "Rates")
public class RatesData extends AbstractDTO {

    @Column(name = "Id")
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Amount")
    private Float amount;

    @Column(name = "Hours", unique = true)
    private Integer hours;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}
