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
        String beginTime = "1/1/0001 12:00 AM";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298 03:32 PM");
    }

    // Check maximum date / time
    @Test
    public void forProject1CheckValidDateUpperBound() {
        String beginTime = "12/31/0001 12:59 PM";
        Appointment appointment = new Appointment("dummy", beginTime, "07/07/1298  03:32 PM");
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
        Appointment appointment = new Appointment(description, "07/07/1298 03:32 AM", "07/08/1298 03:32 AM");
        assertThat(appointment.getDescription(), is(description));
    }


    // After you set the begin and end time, get back the correct dates in getBeginTimeString and getEndTimeString
    @Test
    public void forProject3ReformatedBeginDate() {
        String beginTime = "7/15/0019 2:39 PM";
        String endTime = "3/1/1894 1:1 AM";
        Appointment appointment = new Appointment("dummy", beginTime, endTime);
        try {
            assertThat(appointment.getBeginTimeString(), is("07/15/0019 02:39 PM"));
            assertThat(appointment.getEndTimeString(), is("03/01/1894 01:01 AM"));
        } catch (Exception e){

        }
    }

    // Appointment 2 after appointment 1
    @Test
    public void forProject3App1BeginTimePrecedesApp2BeginTime() {
        String beginTime1 = "7/15/0019 2:39 PM";
        String endTime1 = "3/1/1894 1:10 AM";
        Appointment appointment1 = new Appointment("dummy", beginTime1, endTime1);
        String beginTime2 = "7/15/0019 2:40 PM";
        String endTime2 = "3/1/1894 1:1 AM";
        Appointment appointment2 = new Appointment("dummy", beginTime2, endTime2);
        assertThat(appointment1.compareTo(appointment2), is(-1));
    }

    // Appointment 2 after appointment 1
    @Test
    public void forProject3App1BeginTimeFollowsApp2BeginTime() {
        String beginTime1 = "7/15/0019 2:39 PM";
        String endTime1 = "3/1/1894 1:10 AM";
        Appointment appointment1 = new Appointment("dummy", beginTime1, endTime1);
        String beginTime2 = "7/15/0019 2:38 PM";
        String endTime2 = "3/1/1894 1:1 AM";
        Appointment appointment2 = new Appointment("dummy", beginTime2, endTime2);
        assertThat(appointment1.compareTo(appointment2), is(1));
    }


    // Appointment 2 after appointment 1
    @Test
    public void forProject3App1EndTimePrecedesApp2EndTime() {
        String beginTime1 = "7/15/0019 2:39 PM";
        String endTime1 = "3/1/1894 1:10 AM";
        Appointment appointment1 = new Appointment("dummy", beginTime1, endTime1);
        String beginTime2 = "7/15/0019 2:39 PM";
        String endTime2 = "3/1/1894 1:11 AM";
        Appointment appointment2 = new Appointment("dummy", beginTime2, endTime2);
        assertThat(appointment1.compareTo(appointment2), is(-1));
    }

    // Appointment 2 after appointment 1
    @Test
    public void forProject3App1EndTimeFollowsApp2EndTime() {
        String beginTime1 = "7/15/0019 2:39 PM";
        String endTime1 = "3/1/1894 1:10 AM";
        Appointment appointment1 = new Appointment("dummy", beginTime1, endTime1);
        String beginTime2 = "7/15/0019 2:39 PM";
        String endTime2 = "3/1/1894 1:1 AM";
        Appointment appointment2 = new Appointment("dummy", beginTime2, endTime2);
        assertThat(appointment1.compareTo(appointment2), is(1));
    }


    // Appointment 2 after appointment 1
    @Test
    public void forProject3App1DescriptionPrecedesApp2Description() {
        String beginTime1 = "7/15/0019 2:39 PM";
        String endTime1 = "3/1/1894 1:10 AM";
        Appointment appointment1 = new Appointment("Dummy", beginTime1, endTime1);
        String beginTime2 = "7/15/0019 2:39 PM";
        String endTime2 = "3/1/1894 1:10 AM";
        Appointment appointment2 = new Appointment("dummy", beginTime2, endTime2);
        assertThat(appointment1.compareTo(appointment2), is(-1));
    }

    // Appointment 2 after appointment 1
    @Test
    public void forProject3App1DescriptionFollowsApp2Description() {
        String beginTime1 = "7/15/0019 2:39 PM";
        String endTime1 = "3/1/1894 1:10 AM";
        Appointment appointment1 = new Appointment("dummy", beginTime1, endTime1);
        String beginTime2 = "7/15/0019 2:39 PM";
        String endTime2 = "3/1/1894 1:10 AM";
        Appointment appointment2 = new Appointment("Dummy", beginTime2, endTime2);
        assertThat(appointment1.compareTo(appointment2), is(1));
    }

    // Appointment 2 same as Appointment 1
    @Test
    public void forProject3App1EqualsApp2() {
        String beginTime1 = "7/15/0019 2:39 PM";
        String endTime1 = "3/1/1894 1:10 AM";
        Appointment appointment1 = new Appointment("dummy", beginTime1, endTime1);
        String beginTime2 = "7/15/0019 2:39 PM";
        String endTime2 = "3/1/1894 1:10 AM";
        Appointment appointment2 = new Appointment("dummy", beginTime2, endTime2);
        assertThat(appointment1.compareTo(appointment2), is(0));
    }


    // After you set the begin and end time, get back the correct dates in getBeginTimeString and getEndTimeString
    @Test(expected = UnsupportedOperationException.class)
    public void forProject3BeginTimeMustPrecedeEndTime() {
        String beginTime = "7/15/2019 2:39 AM";
        String endTime = "3/1/1894 1:1 PM";
        Appointment appointment = new Appointment("dummy", beginTime, endTime);
    }




}
