package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.File;
import java.io.FileWriter;

/**
 * This class is represents a <code>TextDumper</code>.
 */
public class PrettyPrinter implements AppointmentBookDumper<AppointmentBook> {

    String textFile = "";

    /**
     * Creates a new <code>PrettyPrinter</code>
     *
     * @param textFile Textfile where to print the Appointmentbook. [-] means print to standard out
     */
    PrettyPrinter(String textFile) {
        super();

        if (!CheckValidFileName(textFile))
            throw new InvalidFileName("Please provide a valid file name");

        this.textFile = textFile;
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

        // Print to standard out if textfile is -
        if (this.textFile.equals("-")) {
            System.out.println(appointmentBook.toString());
            for (Appointment appointment : appointmentBook.getAppointments()) {
                System.out.println(appointment.toString());
            }
        }
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
                    for (Appointment appointment : appointmentBook.getAppointments())
                        fw.write(appointment.toString() + "\n");

                    fw.close();
                } catch (Exception e) {
                    throw new InvalidFileName("Please provide a valid file name using SetFile method");
                }
            }
        }
    }
