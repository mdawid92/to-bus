package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Destination;
import play.mvc.*;

import service.UserProvider;
import views.html.destination.list;

import javax.inject.Inject;
import java.util.List;

public class DestinationController extends Controller {
    private final UserProvider userProvider;


    @Inject
    public DestinationController(final UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result list() {
        List<Destination> all = Destination.find.all();
        return ok(list.render(this.userProvider, all));
    }
}
