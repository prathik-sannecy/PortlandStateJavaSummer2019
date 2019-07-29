package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * This class is represents a <code>PrettyPrinter</code>.
 */
public class PrettyPrinter implements AppointmentBookDumper<AppointmentBook> {

    String textFile = "";
    private PrintWriter printWriter;

    /**
     * Creates a new <code>PrettyPrinter</code>
     *
     * @param textFile Textfile where to print the Appointmentbook. [-] means print to standard out
     */
    PrettyPrinter(String textFile, PrintWriter printWriter) {
        super();

        if (!CheckValidFileName(textFile))
            throw new InvalidFileName("Please provide a valid file name");

        this.textFile = textFile;
        this.printWriter = new PrintWriter(printWriter);
    }

    /**
     * Checks if input String is a valid file name
     *
     * @param textFile Textfile to see if valid name
     */
    private boolean CheckValidFileName(String textFile) {
        return !((textFile == null) || (textFile.equals("")));
    }

    /**
     * Prints the <code>AppointmentBook</code> in a user friendly format.
     *
     * @param appointmentBook AppointmentBook that gets dumped into user specified file
     */
    public void dump(AppointmentBook appointmentBook) {
        if (!CheckValidFileName(this.textFile))
            throw new InvalidFileName("Please provide a valid file name using SetFile method");

        int appointmentCounter = 1;
        // Print to standard out if textfile is -
        if (this.textFile.equals("-")) {

            try {
                this.printWriter.write(appointmentBook.toString() + "\n");
                this.printWriter.flush();
            } catch (Exception e){
                e.printStackTrace();
            }


            for (Appointment appointment : appointmentBook.getAppointments()) {
                try {
                    this.printWriter.write("Appointment" + appointmentCounter + " Duration " + appointmentDuration(appointment) + " minutes: " + appointment.toString() + "\n");
                    this.printWriter.flush();
                } catch (Exception e){
                    e.printStackTrace();
                }
                appointmentCounter++;
            }
        }
        // Otherwise write to file specified
        else{
            File f = new File(this.textFile);
            // If textFiles exists, delete and then recreate it
            if (f.isFile()) {
                f.delete();
            }
            try {
                f.createNewFile();
                FileWriter fw = new FileWriter(textFile);

                fw.write(appointmentBook.toString() + "\n");
                for (Appointment appointment : appointmentBook.getAppointments()) {
                    fw.write("Appointment" + appointmentCounter + " Duration " + appointmentDuration(appointment) + " minutes: " + appointment.toString() + "\n");
                    appointmentCounter++;
                }

                fw.close();
            } catch (Exception e) {
                throw new InvalidFileName("Please provide a valid file name using SetFile method");
            }
        }
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
