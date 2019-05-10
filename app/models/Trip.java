package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Trip extends Model {

    @Id
    @Constraints.Min(1)
    private Long id;

    @Constraints.Required
    private Date date;


    @Constraints.Required
    @ManyToOne
    private Destination start;

    @Constraints.Required
    @ManyToOne
    private Destination stop;

    @Constraints.Required
    @ManyToOne
    private Bus bus;

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Trip(@Constraints.Min(1) Long id, @Constraints.Required Date date, @Constraints.Required Destination start, @Constraints.Required Destination stop, @Constraints.Required Bus bus) {
        this.id = id;
        this.date = date;
        this.start = start;
        this.stop = stop;
        this.bus = bus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Destination getStart() {
        return start;
    }

    public void setStart(Destination start) {
        this.start = start;
    }

    public Destination getStop() {
        return stop;
    }

    public void setStop(Destination stop) {
        this.stop = stop;
    }

    public static final Finder<Long, Trip> find = new Finder<>(Trip.class);

}
