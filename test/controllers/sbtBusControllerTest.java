package controllers;

import models.Bus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class sbtBusControllerTest {
    @Test
    public void getName() throws Exception{

        Bus bus = new Bus();
        bus.setName("Solaris");
        assertEquals("Solaris",bus.getName());

    }
    @Test
    public void getSeats() throws Exception{

        Bus bus = new Bus();
        bus.setSeats(42);
        assertEquals(42,bus.getSeats());
    }
}
