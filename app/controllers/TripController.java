package controllers;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import models.Bus;
import models.Destination;
import models.Trip;
import models.Search;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import service.UserProvider;
import views.html.trip.editform;
import views.html.trip.form;
import views.html.trip.list;
import views.html.trip.search;
import views.html.userlist;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;


public class TripController extends Controller {
    private final UserProvider userProvider;
    private final FormFactory formFactory;


    @Inject
    public TripController(final UserProvider userProvider, final FormFactory formFactory) {
        this.userProvider = userProvider;
        this.formFactory = formFactory;
    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result list() {
        List<Trip> all = Trip.find.all();
        all.sort(Comparator.comparingLong(Trip::getId));


        return ok(list.render(this.userProvider, all));
    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result doAdd() {
        Form<Trip> addForm = formFactory.form(Trip.class).bindFromRequest();
        List<Destination> destinations = Destination.find.all();
        List<Bus> buses = Bus.find.all();


        if (addForm.hasErrors()) {
            return badRequest(form.render(this.userProvider, addForm, destinations, buses));
        } else {
            Trip trip = addForm.get();
            trip.save();

            return redirect(routes.TripController.list());
        }

    }
    @Restrict(@Group(Application.USER_ROLE))
    public Result search() {
        Form<Search> addForm = formFactory.form(Search.class).bindFromRequest();
        List<Destination> destinations = Destination.find.all();
        List<Bus> buses = Bus.find.all();

        return ok(search.render(this.userProvider, addForm));

    }
    @Restrict(@Group(Application.USER_ROLE))
    public Result doSearch() {
        Form<Search> addForm = formFactory.form(Search.class).bindFromRequest();
        List<Trip> trips = Trip.find.all();
        List<Trip> tripy = new ArrayList<Trip>();
        Search search = addForm.get();
        for (int i = 0; i<trips.size(); i++) {
            Trip tr = trips.get(i);
            Destination dt = tr.getStart();
            Destination td = tr.getStop();
            if (dt.getName().equals(search.getStart()) && td.getName().equals(search.getStop())){
                tripy.add(tr);
            }
        }
        return ok(userlist.render(this.userProvider, tripy));
        }

    @Restrict(@Group(Application.USER_ROLE))
    public Result add() {
        Form<Trip> addForm = formFactory.form(Trip.class).bindFromRequest();
        List<Destination> destinations = Destination.find.all();
        List<Bus> buses = Bus.find.all();

        return ok(form.render(this.userProvider, addForm, destinations, buses));

    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result delete(Long id) {
        Trip trip = Trip.find.byId(id);
        trip.delete();
        return redirect(routes.TripController.list());

    }


    @Restrict(@Group(Application.USER_ROLE))
    public Result doEdit(Long id) {
        Form<Trip> addForm = formFactory.form(Trip.class).bindFromRequest();
        List<Destination> destinations = Destination.find.all();
        List<Bus> buses = Bus.find.all();

        if (addForm.hasErrors()) {
            return badRequest(editform.render(this.userProvider, addForm, destinations, buses, id));
        } else {
            Trip bus = Trip.find.byId(id);

            Trip tripUpdated = addForm.get();
            bus.setDate(tripUpdated.getDate());
            bus.setStart(tripUpdated.getStart());
            bus.setStop(tripUpdated.getStop());
            bus.setBus(tripUpdated.getBus());
            bus.save();

            return redirect(routes.TripController.list());
        }

    }

    @Restrict(@Group(Application.USER_ROLE))
    public Result edit(Long id) {
        Trip trip = Trip.find.byId(id);
        List<Destination> destinations = Destination.find.all();
        List<Bus> buses = Bus.find.all();

        Form<Trip> addForm = formFactory.form(Trip.class).fill(trip);
        return ok(editform.render(this.userProvider, addForm, destinations, buses, id));

    }

}
