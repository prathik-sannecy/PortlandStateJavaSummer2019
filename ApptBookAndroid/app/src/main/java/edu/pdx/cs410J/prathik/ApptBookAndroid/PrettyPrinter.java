package edu.pdx.cs410J.prathik.ApptBookAndroid;


import android.content.Context;
import android.widget.TextView;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * This class is represents a <code>PrettyPrinter</code>.
 */
public class PrettyPrinter {

    TextView displayText;

    /**
     * Creates a new <code>PrettyPrinter</code>
     *
     * @param displayText TextView where to print the Appointmentbook
     */
    PrettyPrinter(TextView displayText) {
        super();


        this.displayText = displayText;
    }


    /**
     * Prints the <code>AppointmentBook</code> in a user friendly format.
     *
     * @param appointmentBook AppointmentBook that gets dumped into the TextView widget
     */
    public void dump(AppointmentBook appointmentBook) {

        int appointmentCounter = 1;

        StringBuilder sb = new StringBuilder();

        for (Appointment appointment : appointmentBook.getAppointments()) {
            sb.append("Appointment" + appointmentCounter + " Duration " + appointmentDuration(appointment) + " minutes: " + appointment.toString() + "\n\n");
            appointmentCounter++;
        }


        this.displayText.setText(sb.toString());

    }

    /**
     * Finds the duration of an <code>Appointment</code> in minutes
     *
     * @param appointment Appointment to find the duration of
     */
    private String appointmentDuration(Appointment appointment) {
        long diffInMillies = appointment.getEndTime().getTime() - appointment.getBeginTime().getTime();
        long diffInMIn = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return Long.toString(diffInMIn);
    }
}
