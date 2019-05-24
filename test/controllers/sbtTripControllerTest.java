package controllers;

import models.Bus;
import models.Search;
import models.Trip;
import models.Destination;
import org.junit.Test;


import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class sbtTripControllerTest {
    @Test
    public void getId() throws Exception{

        Trip trip = new Trip();
        trip.setId(12L);
        assertEquals(Long.valueOf(12L),trip.getId());

    }
    @Test
    public void getStart() throws Exception{

        Trip trip = new Trip();
        Destination destination = new Destination();
        destination.setAddress("zeromskiego");
        destination.setCity("Staszow");
        destination.setId(12L);
        destination.setName("Dworzec");
        trip.setStart(destination);
        assertEquals(destination,trip.getStart());
    }

    @Test
    public void testSearch() throws Exception{

        Destination start = new Destination(12L,"Dworzec" ,"zeromskiego","Staszow");
        Destination stop = new Destination(13L,"aaa" ,"aaa","aaa");
        Destination stop2 = new Destination(14L,"bbb" ,"bbb","bbb");
        Trip trip1 = new Trip(1L, new Date(), start, stop, new Bus());
        Trip trip2 = new Trip(2L, new Date(), start, stop2, new Bus());
        Search s = new Search("Dworzec", "aaa");

        assertEquals(TripController.getTrips(Arrays.asList(trip1,trip2), s).size(), 1);

    }
}
