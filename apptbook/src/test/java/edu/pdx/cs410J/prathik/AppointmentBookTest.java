package edu.pdx.cs410J.prathik;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link AppointmentBook} class.
 */

public class AppointmentBookTest {

    @Test
    public void forProject1CreateAppointmentBook() {
        AppointmentBook appointmentbook = new AppointmentBook();
    }

    // Can't get owner if it hasn't been set yet
    @Test(expected = UnsupportedOperationException.class)
    public void getOwnerNeedsToBeImplemented() {
        AppointmentBook appointmentbook = new AppointmentBook();
        appointmentbook.getOwnerName();
    }

    // Need to set a valid description
    @Test(expected = UnsupportedOperationException.class)
    public void forProject1CantSetNullOwner() {
        AppointmentBook appointmentbook = new AppointmentBook();
        appointmentbook.setOwnerName(null);
    }


    // After setting description, you get back correct description
    @Test
    public void forProject1GetOwnerReturnsSetOwner() {
        AppointmentBook appointmentbook = new AppointmentBook();
        String ownerName = "Bob";
        appointmentbook.setOwnerName(ownerName);
        assertThat(appointmentbook.getOwnerName(), is(ownerName));
    }

    // if owner has int, error



    // Empty date / time, description, etc

    // Begin Date must precede End Date

}
