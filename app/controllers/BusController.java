package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Bus;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import service.UserProvider;
import views.html.bus.editform;
import views.html.bus.form;
import views.html.bus.list;

import javax.inject.Inject;
import java.util.List;

public class BusController extends Controller {
    private final UserProvider userProvider;
    private final FormFactory formFactory;


    @Inject
    public BusController(final UserProvider userProvider, final FormFactory formFactory) {
        this.userProvider = userProvider;
        this.formFactory = formFactory;
    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result list() {
        List<Bus> all = Bus.find.all();
        return ok(list.render(this.userProvider, all));
    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result doAdd() {
        Form<Bus> addForm = formFactory.form(Bus.class).bindFromRequest();
        if (addForm.hasErrors()) {
            return badRequest(form.render(this.userProvider, addForm));
        } else {
            Bus destination = addForm.get();
            destination.save();

            return redirect(routes.BusController.list());
        }

    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result add() {
        Form<Bus> addForm = formFactory.form(Bus.class).bindFromRequest();
        return ok(form.render(this.userProvider, addForm));

    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result delete(Long id) {
        Bus destination = Bus.find.byId(id);
        destination.delete();
        return redirect(routes.BusController.list());

    }


    @Restrict(@Group(Application.USER_ROLE))
    public Result doEdit(Long id) {
        Form<Bus> addForm = formFactory.form(Bus.class).bindFromRequest();
        if (addForm.hasErrors()) {
            return badRequest(editform.render(this.userProvider, addForm, id));
        } else {
            Bus bus = Bus.find.byId(id);

            Bus busUpdated = addForm.get();
            bus.setName(busUpdated.getName());
            bus.setSeats(busUpdated.getSeats());
            bus.save();

            return redirect(routes.BusController.list());
        }

    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result edit(Long id) {
        Bus bus = Bus.find.byId(id);

        Form<Bus> addForm = formFactory.form(Bus.class).fill(bus);
        return ok(editform.render(this.userProvider, addForm, id));

    }

}
