package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is represents a <code>Appointment</code>.
 */
public class Appointment extends AbstractAppointment {
    private String BeginTimeString = null;
    private String EndTimeString = null;
    private String Description = null;

    /**
     * Creates a new <code>Appointment</code>
     *
     * @param description description of the appointment
     * @param beginTime Start time of the appointment (mm/dd/yyyy hh:mm format)
     * @param endTime End time of the appointment (mm/dd/yyyy hh:mm format)
     */
    public Appointment(String description, String beginTime, String endTime) {
        super();
        this.setDescription(description);
        this.setBeginTimeString(beginTime);
        this.setEndTimeString(endTime);
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
     * @param endTime The time at which the appointment will end (mm/dd/yyyy hh:mm format)
     */
    private void setEndTimeString(String endTime) {
        boolean validDateTimeFormat = false;
        if (checkDateFormat(endTime, "mm/dd/yyyy HH:mm")) validDateTimeFormat = true;
        if (checkDateFormat(endTime, "m/dd/yyyy HH:mm")) validDateTimeFormat = true;
        if (checkDateFormat(endTime, "mm/d/yyyy HH:mm")) validDateTimeFormat = true;
        if (checkDateFormat(endTime, "m/d/yyyy HH:mm")) validDateTimeFormat = true;

        // Make sure year is 4 digits
        try {
            String year = endTime.split(" ")[0].split("/")[2];
            if (year.length() != 4) validDateTimeFormat = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            validDateTimeFormat = false;
        }

        if (!validDateTimeFormat)
            throw new WrongDateTimeFormat("Please enter end time date in correct format of mm/dd/yyyy hh:mm");

        this.EndTimeString = endTime;
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
     * @param beginTime The time at which the appointment will begin (mm/dd/yyyy hh:mm format)
     */
    private void setBeginTimeString(String beginTime) {
        boolean validDateTimeFormat = false;
        if (checkDateFormat(beginTime, "mm/dd/yyyy HH:mm")) validDateTimeFormat = true;
        if (checkDateFormat(beginTime, "m/dd/yyyy HH:mm")) validDateTimeFormat = true;
        if (checkDateFormat(beginTime, "mm/d/yyyy HH:mm")) validDateTimeFormat = true;
        if (checkDateFormat(beginTime, "m/d/yyyy HH:mm")) validDateTimeFormat = true;

        // Make sure year is 4 digits
        try {
            String year = beginTime.split(" ")[0].split("/")[2];
            if (year.length() != 4) validDateTimeFormat = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            validDateTimeFormat = false;
        }

        if (!validDateTimeFormat)
            throw new WrongDateTimeFormat("Please enter begin time date in correct format of mm/dd/yyyy hh:mm!");

        this.BeginTimeString = beginTime;
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
            Date parsedDate = formatter.parse(date);

        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    private Boolean checkDateOrder(String date1, String date2, String format) {
        return _checkDateOrder(date1, date2, "mm/dd/yyyy HH:mm");
    }


    private Boolean _checkDateOrder(String date1, String date2, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date parsedDate1 = formatter.parse(date1);
            Date parsedDate2 = formatter.parse(date2);
            if (parsedDate2.after(parsedDate1))
                return true;

            return false;
        } catch (ParseException e) {
            throw new UnsupportedOperationException("Please enter dates in correct format of mm/dd/yyyy hh:mm");
        }
    }
}
