package models;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Destination extends Model {

    @Id
    @Constraints.Min(1)
    private Long id;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String city;

    @Constraints.Required
    private String address;

    public Destination(@Constraints.Min(1) Long id, @Constraints.Required String name, @Constraints.Required String city, @Constraints.Required String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static final Finder<Long, Destination> find = new Finder<>(Destination.class);

}
