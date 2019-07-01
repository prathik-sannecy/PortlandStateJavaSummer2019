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


  // After you set the begin time, if get the time you get the correct time
  @Test
  public void forProject1GetBeginTimeReturnsSetBeginTime() {
    Appointment appointment = new Appointment();
    String beginTime = "7/15/2019 14:39";
    appointment.setBeginTimeString(beginTime);
    assertThat(appointment.getBeginTimeString(), is(beginTime));
  }


  // If you set the hour > 24, you get an error message
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckHourLessThanTwentyFive() {
    Appointment appointment = new Appointment();
    String beginTime = "7/15/2019 25:39";
    appointment.setBeginTimeString(beginTime);
  }

  // If you set the hour < 0, you get an error message
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckHourMoreThanZero() {
    Appointment appointment = new Appointment();
    String beginTime = "7/15/2019 -1:39";
    appointment.setBeginTimeString(beginTime);
  }

  // If you set the minutes > 60, you get an error message
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckMinuteLessThanSixty() {
    Appointment appointment = new Appointment();
    String beginTime = "7/15/2019 1:70";
    appointment.setBeginTimeString(beginTime);
  }

  // If you set the minutes < 0, you get an error message
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckMinuteGreaterZero() {
    Appointment appointment = new Appointment();
    String beginTime = "7/15/2019 1:-1";
    appointment.setBeginTimeString(beginTime);
  }

  // Check minimum date / time
  @Test
  public void forProject1CheckValidDateLowerBound() {
    Appointment appointment = new Appointment();
    String beginTime = "1/1/0001 00:00";
    appointment.setBeginTimeString(beginTime);
  }

  // Check maximum date / time
  @Test
  public void forProject1CheckValidDateUpperBound() {
    Appointment appointment = new Appointment();
    String beginTime = "12/31/0001 23:59";
    appointment.setBeginTimeString(beginTime);
  }

  // Check year format uses 4 digits, not 2
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckFourDigitYearFormat() {
    Appointment appointment = new Appointment();
    String beginTime = "7/15/19 1:-1";
    appointment.setBeginTimeString(beginTime);
  }

  // If month < 1, throw an Error
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckMonthGreaterThanZero() {
    Appointment appointment = new Appointment();
    String beginTime = "/15/2019 1:70";
    appointment.setBeginTimeString(beginTime);
  }

  // If you set the month > 12, you get an error message
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckMonthLessThanThirteen() {
    Appointment appointment = new Appointment();
    String beginTime = "13/15/2019 1:-1";
    appointment.setBeginTimeString(beginTime);
  }

  // If day < 1, throw an Error
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckDayGreaterThanZero() {
    Appointment appointment = new Appointment();
    String beginTime = "07/00/2019 1:70";
    appointment.setBeginTimeString(beginTime);
  }

  // If you set the day > month's range, you get an error message
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckDayLessThanMonthsRange() {
    Appointment appointment = new Appointment();
    String beginTime = "02/30/2019 1:-1";
    appointment.setBeginTimeString(beginTime);
  }

  // If you use incorrect separators for date Time format, throw error
  @Test(expected = WrongDateTimeFormat.class)
  public void forProject1CheckDateTimeFormat() {
    Appointment appointment = new Appointment();
    String beginTime = "02-30-2019 1:10";
    appointment.setBeginTimeString(beginTime);
  }


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
