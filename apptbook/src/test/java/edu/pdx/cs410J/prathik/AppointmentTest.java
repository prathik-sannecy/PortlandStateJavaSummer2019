package edu.pdx.cs410J.prathik;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

  @Test(expected = UnsupportedOperationException.class)
  public void getBeginTimeStringNeedsToBeImplemented() {
    Appointment appointment = new Appointment();
    appointment.getBeginTimeString();
  }

  @Test
  public void initiallyAllAppointmentsHaveTheSameDescription() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getDescription(), containsString("not implemented"));
  }

  @Test
  public void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

  @Test
  public void forProject1() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }


  // After you set the time, if get the time you get the correct time

  // If you set the time outside of 0 - 24, you get an error message

  // If you pass a string in to the date / time, you get an error message

  // owner can be null

  // if you set owner to int , you get error message

  // Invalid option gives a list of valid options

  // Description can be none

  // Description can not be int

  // After setting description, you get back correct description

  // After setting owner, you get back correct owner

  // Only valid dates are supported

  // Wrong number of command line arguments

  // Empty date / time, description, etc


}
