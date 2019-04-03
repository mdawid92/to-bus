package models;

import be.objectify.deadbolt.java.models.Role;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class SecurityRole extends Model implements Role {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    public Long id;

    public String roleName;

    public static final Finder<Long, SecurityRole> find = new Finder<>(SecurityRole.class);

    @Override
    public String getName() {
        return roleName;
    }

    public static SecurityRole findByRoleName(String roleName) {
        return find.query().where().eq("roleName", roleName).findOne();
    }
}