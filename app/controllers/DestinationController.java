package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Destination;
import play.data.Form;

import play.data.FormFactory;
import play.mvc.*;

import service.UserProvider;
import views.html.destination.*;

import javax.inject.Inject;
import java.util.List;

public class DestinationController extends Controller {
    private final UserProvider userProvider;
    private final FormFactory formFactory;


    @Inject
    public DestinationController(final UserProvider userProvider, final FormFactory formFactory) {
        this.userProvider = userProvider;
        this.formFactory = formFactory;
    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result list() {
        List<Destination> all = Destination.find.all();
        return ok(list.render(this.userProvider, all));
    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result doAdd() {
        Form<Destination> addForm = formFactory.form(Destination.class).bindFromRequest();
        if (addForm.hasErrors()) {
            return badRequest(form.render(this.userProvider, addForm));
        } else {
            Destination destination = addForm.get();
            destination.save();

            return redirect(routes.DestinationController.list());
        }

    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result add() {
        Form<Destination> addForm = formFactory.form(Destination.class).bindFromRequest();
        return ok(form.render(this.userProvider, addForm));

    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result delete(Long id) {
        Destination destination = Destination.find.byId(id);
        destination.delete();
        return redirect(routes.DestinationController.list());

    }


    @Restrict(@Group(Application.USER_ROLE))
    public Result doEdit(Long id) {
        Form<Destination> addForm = formFactory.form(Destination.class).bindFromRequest();
        if (addForm.hasErrors()) {
            return badRequest(editform.render(this.userProvider, addForm, id));
        } else {
            Destination destination = Destination.find.byId(id);

            Destination destinationUpdated = addForm.get();
            destination.setAddress(destinationUpdated.getAddress());
            destination.setName(destinationUpdated.getName());
            destination.setCity(destinationUpdated.getCity());
            destination.save();

            return redirect(routes.DestinationController.list());
        }

    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result edit(Long id) {
        Destination destination = Destination.find.byId(id);

        Form<Destination> addForm = formFactory.form(Destination.class).fill(destination);
        return ok(editform.render(this.userProvider, addForm, id));

    }

}
