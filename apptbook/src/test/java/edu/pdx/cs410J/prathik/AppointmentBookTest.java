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
        AppointmentBook appointmentbook = new AppointmentBook("Bob");
    }


    // Need to set a valid description
    @Test(expected = UnsupportedOperationException.class)
    public void forProject1CantSetNullOwner() {
        AppointmentBook appointmentbook = new AppointmentBook(null);
    }


    // After setting description, you get back correct description
    @Test
    public void forProject1GetOwnerReturnsSetOwner() {
        String ownerName = "Bob";
        AppointmentBook appointmentbook = new AppointmentBook(ownerName);
        assertThat(appointmentbook.getOwnerName(), is(ownerName));
    }



    // make sure intialized Appointment Book returns empty list
    @Test
    public void forProject1ReturnEmptyBook() {
        String ownerName = "Bob";
        AppointmentBook appointmentbook = new AppointmentBook(ownerName);
        assertThat(appointmentbook.getAppointments().isEmpty(), is(true));
    }

    // After adding an appointment, you get back the appointment
    @Test
    public void forProject1ReturnSameAppointmentAdded() {
        String ownerName = "Bob";
        AppointmentBook appointmentbook = new AppointmentBook(ownerName);
        Appointment appointment = new Appointment("Test", "7/15/2019 14:39", "7/17/2019 14:39");
        appointmentbook.addAppointment(appointment);
        assertThat(appointmentbook.getAppointments().size(), is(1));
        Appointment readAppointment = (Appointment) appointmentbook.getAppointments().get(0);
        assertThat(readAppointment.getBeginTimeString(), is(appointment.getBeginTimeString()));
        assertThat(readAppointment.getEndTimeString(), is(appointment.getEndTimeString()));
        assertThat(readAppointment.getDescription(), is(appointment.getDescription()));
    }

    // Adding a bad appointment (wrong format)
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1AddingBadAppointment() {
        String ownerName = "Bob";
        AppointmentBook appointmentbook = new AppointmentBook(ownerName);
        Appointment appointment = new Appointment("Test", "13/15/19 14:39", "7/17/2019 14:39");
        appointmentbook.addAppointment(appointment);
        assertThat(appointmentbook.getAppointments().size(), is(1));
    }



}
