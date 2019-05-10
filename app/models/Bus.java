package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bus extends Model {

    @Id
    @Constraints.Min(1)
    private Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private int seats;



    public Bus(@Constraints.Min(1) Long id, @Constraints.Required String name, @Constraints.Required int seats) {
        this.id = id;
        this.name = name;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public static final Finder<Long, Bus> find = new Finder<>(Bus.class);

}
