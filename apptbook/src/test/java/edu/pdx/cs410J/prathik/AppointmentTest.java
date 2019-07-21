package edu.pdx.cs410J.prathik;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

    // If you set the hour > 24, you get an error message
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckHourLessThanTwentyFive() {
        String beginTime = "7/15/2019 25:39";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // If you set the hour < 0, you get an error message
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckHourMoreThanZero() {
        String beginTime = "7/15/2019 -1:39";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // If you set the minutes > 60, you get an error message
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckMinuteLessThanSixty() {
        String beginTime = "7/15/2019 1:70";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // If you set the minutes < 0, you get an error message
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckMinuteGreaterZero() {
        String beginTime = "7/15/2019 1:-1";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // Check minimum date / time
    @Test
    public void forProject1CheckValidDateLowerBound() {
        String beginTime = "1/1/0001 00:00";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // Check maximum date / time
    @Test
    public void forProject1CheckValidDateUpperBound() {
        String beginTime = "12/31/0001 23:59";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298  03:32");
    }

    // Check year format uses 4 digits, not 2
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckFourDigitYearFormat() {
        String beginTime = "7/15/19 01:01";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // If month < 1, throw an Error
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckMonthGreaterThanZero() {
        String beginTime = "7/15/2019 1:70";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // If you set the month > 12, you get an error message
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckMonthLessThanThirteen() {
        String beginTime = "13/15/2019 1:1";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // If day < 1, throw an Error
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckDayGreaterThanZero() {
        String beginTime = "07/00/2019 1:20";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // If you set the day > month's range, you get an error message
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckDayLessThanMonthsRange() {
        String beginTime = "02/30/2019 1:1";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }

    // If you use incorrect separators for date Time format, throw error
    @Test(expected = WrongDateTimeFormat.class)
    public void forProject1CheckDateTimeFormat() {
        String beginTime = "02-30-2019 1:10";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32");
    }


    // Need to set a valid description
    @Test(expected = WrongDescription.class)
    public void forProject1CantSetNullDescription() {
        String description = null;
        Appointment appointment = new Appointment(description, "07/07/1298 03:32", "07/07/1298 03:32");

    }


    // After setting description, you get back correct description
    @Test
    public void forProject1GetDescriptionReturnsSetDescription() {
        String description = "Eat Candy";
        Appointment appointment = new Appointment(description, "07/07/1298 03:32", "07/08/1298 03:32");
        assertThat(appointment.getDescription(), is(description));
    }


    // After you set the begin and end time, get back the correct dates in getBeginTimeString and getEndTimeString
    @Test
    public void forProject3ReformatedBeginDate() {
        String beginTime = "7/15/0019 14:39";
        String endTime = "3/1/1894 1:1";
        Appointment appointment = new Appointment("dummy", beginTime, endTime);
        try {
            assertThat(appointment.getBeginTimeString(), is("7/15/19, 2:39 PM"));
            assertThat(appointment.getEndTimeString(), is("3/1/94, 1:01 AM"));
        } catch (Exception e){

        }
    }

    // After you set the begin and end time, get back the correct dates in getBeginTimeString and getEndTimeString
    @Test(expected = UnsupportedOperationException.class)
    public void forProject3BeginTimeMustPrecedeEndTime() {
        String beginTime = "7/15/2019 14:39";
        String endTime = "3/1/1894 1:1";
        Appointment appointment = new Appointment("dummy", beginTime, endTime);
    }







}
