package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class is represents a <code>Appointment</code>.
 */
public class Appointment extends AbstractAppointment implements Comparable<Appointment> {
    private String BeginTimeString = null;
    private String EndTimeString = null;
    private String Description = null;
    private Date EndTime = null;
    private Date BeginTime = null;


    /**
     * Creates a new <code>Appointment</code>
     *
     * @param description description of the appointment
     * @param beginTime   Start time of the appointment (mm/dd/yyyy hh:mm AM/PM format)
     * @param endTime     End time of the appointment (mm/dd/yyyy hh:mm AM/PM format)
     */
    public Appointment(String description, String beginTime, String endTime) {
        super();
        this.setDescription(description);
        this.setBeginTimeString(beginTime);
        this.setEndTimeString(endTime);
        this.checkDateOrder();
    }

    /**
     * Returns a <code>Date</code> that describes the end time of this
     * <code>Appointment</code>.
     */
    public Date getEndTime() {
        return this.EndTime;
    }

    /**
     * Sets the end time of the <code>Appointment</code> in Date format
     */
    private void setEndTime(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        df.setLenient(false);
        try {
            this.EndTime = df.parse(date);
        } catch (Exception e) {
            System.out.println("Please enter end time date in correct format of mm/dd/yyyy hh:mm AM/PM");
        }
    }

    /**
     * Returns a <code>Date</code> that describes the end time of this
     * <code>Appointment</code>.
     */
    public Date getBeginTime() {
        return this.BeginTime;
    }

    /**
     * Sets the end time of the <code>Appointment</code> in Date format
     */
    private void setBeginTime(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        df.setLenient(false);
        try {
            this.BeginTime = df.parse(date);
        } catch (Exception e) {
            System.out.println("Please enter end time date in correct format of mm/dd/yyyy hh:mm AM/PM");
        }
    }

    /**
     * Returns a <code>String</code> that describes the end time of this
     * <code>Appointment</code>.
     */
    @Override
    public String getEndTimeString() {
        return this.EndTimeString;

    }

    /**
     * Sets the end time of the <code>Appointment</code>
     *
     * @param endTime The time at which the appointment will end (mm/dd/yyyy hh:mm AM/PM format)
     */
    private void setEndTimeString(String endTime) {
        boolean validDateTimeFormat = false;
        String format = null;
        ArrayList<String> formats = new ArrayList<>();
        formats.add("MM/dd/yyyy hh:mm a");
        formats.add("MM/dd/yyyy hh:mm a");
        formats.add("MM/d/yyyy hh:mm a");
        formats.add("MM/d/yyyy hh:mm a");

        // Figure out which of the valid formats the date is in
        for (String i : formats) {
            if (checkDateFormat(endTime, i)) {
                validDateTimeFormat = true;
                format = i;
                break;
            }
        }

        // Make sure year is 4 digits
        try {
            String year = endTime.split(" ")[0].split("/")[2];
            if (year.length() != 4) validDateTimeFormat = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            validDateTimeFormat = false;
        }

        if (!validDateTimeFormat)
            throw new WrongDateTimeFormat("Please enter end time date in correct format of mm/dd/yyyy hh:mm AM/PM");

        // Set the endTime in Date Format
        setEndTime(endTime, format);

        // Take the end time in date format, and save it in string format
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        this.EndTimeString = df.format(this.EndTime);
    }

    /**
     * Returns a <code>String</code> that describes the begin time of this
     * <code>Appointment</code>.
     */
    @Override
    public String getBeginTimeString() {
        return this.BeginTimeString;
    }

    /**
     * Sets the begin time of the <code>Appointment</code>
     *
     * @param beginTime The time at which the appointment will begin (mm/dd/yyyy hh:mm AM/PM format)
     */
    private void setBeginTimeString(String beginTime) {
        boolean validDateTimeFormat = false;
        String format = null;
        ArrayList<String> formats = new ArrayList<>();
        formats.add("MM/dd/yyyy hh:mm a");
        formats.add("MM/dd/yyyy hh:mm a");
        formats.add("MM/d/yyyy hh:mm a");
        formats.add("MM/d/yyyy hh:mm a");

        // Figure out which of the valid formats the date is in
        for (String i : formats) {
            if (checkDateFormat(beginTime, i)) {
                validDateTimeFormat = true;
                format = i;
                break;
            }
        }

        // Make sure year is 4 digits
        try {
            String year = beginTime.split(" ")[0].split("/")[2];
            if (year.length() != 4) validDateTimeFormat = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            validDateTimeFormat = false;
        }

        if (!validDateTimeFormat)
            throw new WrongDateTimeFormat("Please enter begin time date in correct format of mm/dd/yyyy hh:mm AM/PM!");

        // Set the endTime in Date Format
        setBeginTime(beginTime, format);

        // Take the begin time in date format, and save it in string format
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        this.BeginTimeString = df.format(this.BeginTime);
    }

    /**
     * Returns a <code>String</code> that describes this
     * <code>Appointment</code>.
     */
    @Override
    public String getDescription() {
        return this.Description;
    }

    /**
     * Sets the description of the <code>Appointment</code>
     *
     * @param description The description of the appointment
     */
    private void setDescription(String description) {
        if ((description == null) || (description == ""))
            throw new WrongDescription("Please enter a description");

        this.Description = description;
    }

    /**
     * Checks whether a dateTime is in the correct format (mm/dd/yyyy HH:mm)
     */
    private Boolean checkDateFormat(String date, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatter.setLenient(false);
            formatter.parse(date);

        } catch (ParseException e) {
            return false;
        } catch (NullPointerException e) {
            throw new WrongDateTimeFormat("Please enter begin time date in correct format of mm/dd/yyyy hh:mm AM/PM!");
        }

        return true;
    }

    /**
     * Checks how a given <code>Appointment</code> should be sorted against <code>this</code>
     *
     * @param appointment Appointment to compare against
     *                    <p>
     *                    Returns 1 if <code>this</code> is greater than appointment
     *                    Returns -1 if <code>this</code> is less than appointment
     *                    Returns 0 if <code>this</code> is equal to appointment
     */
    public int compareTo(Appointment appointment) {
        if (this.BeginTime.after(appointment.getBeginTime()))
            return 1;
        if (appointment.getBeginTime().after(this.BeginTime))
            return -1;
        if (this.EndTime.after(appointment.getEndTime()))
            return 1;
        if (appointment.getEndTime().after(this.EndTime))
            return -1;
        if (this.Description.compareTo(appointment.getDescription()) > 0)
            return 1;
        if (this.Description.compareTo(appointment.getDescription()) < 0)
            return -1;
        return 0;
    }

    /**
     * Checks whether appointment's end time is after appointment's begin time
     */
    private void checkDateOrder() {
        if (!(this.EndTime.after(this.BeginTime)))
            throw new UnsupportedOperationException("Please make sure appointment's end time is after its begin time");

    }
}