package controllers;

import models.Destination;
        import org.junit.Test;

        import static org.junit.Assert.assertEquals;

public class sbtDestinationControllerTest {
    @Test
    public void getCity() throws Exception{

        Destination destination = new Destination();
        destination.setCity("Staszow");
        assertEquals("Staszow",destination.getCity());

    }
    @Test
    public void getName() throws Exception{

        Destination destination = new Destination();
        destination.setName("Dworzec");
        assertEquals("Dworzec",destination.getName());

    }

}
