package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Search extends Model {



    @Constraints.Required
    @ManyToOne
    private String start;

    @Constraints.Required
    @ManyToOne
    private String stop;



    public Search( @Constraints.Required String start, @Constraints.Required String stop) {

        this.start = start;
        this.stop = stop;

    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public static final Finder<String, Trip> find = new Finder<>(Trip.class);

}
