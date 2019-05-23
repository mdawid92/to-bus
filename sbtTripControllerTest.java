package controllers;

import models.Trip;
import models.Destination;
import org.junit.Test;



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
}
