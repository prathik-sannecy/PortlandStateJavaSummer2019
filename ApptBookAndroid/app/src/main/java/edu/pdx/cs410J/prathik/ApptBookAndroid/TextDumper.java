package edu.pdx.cs410J.prathik.ApptBookAndroid;

import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.File;
import java.io.FileWriter;

/**
 * This class is represents a <code>TextDumper</code>.
 */
public class TextDumper implements AppointmentBookDumper<AppointmentBook> {

    String textFile = "";

    /**
     * Creates a new <code>TextDumper</code>
     *
     * @param textFile Textfile where to dump the Appointmentbook
     */
    TextDumper(String textFile) {
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
    private boolean CheckValidFileName(String textFile){
        return !((textFile == null) || (textFile.equals("")));
    }

    /**
     * Dumps an <code>AppointmentBook</code> into a tab separated file
     *
     * @param appointmentBook AppointmentBook that gets dumped into user specified file
     */
    public void dump(AppointmentBook appointmentBook) {
        if (!CheckValidFileName(this.textFile))
            throw new InvalidFileName("Please provide a valid file name using SetFile method");
        
        File f = new File(this.textFile);
        // If textFiles exists, delete and then recreate it
        if (f.isFile()) {
            f.delete();
        }
        try {
            f.createNewFile();


            FileWriter fw = new FileWriter(textFile);

            fw.write(appointmentBook.getOwnerName() + "\n");
            for (Appointment appointment : appointmentBook.getAppointments())
                fw.write(appointment.getDescription() + "\t" + appointment.getBeginTimeString() + "\t" + appointment.getEndTimeString() + "\n");

            fw.close();
        } catch (Exception e) {
            throw new InvalidFileName("Please provide a valid file name using SetFile method");
        }
    }
}
