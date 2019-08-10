package edu.pdx.cs410J.prathik.ApptBookAndroid;


import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import android.content.Context;

/**
 * This class is represents a <code>TextDumper</code>.
 */
public class TextDumper {

    String textFile = "";

    /**
     * Creates a new <code>TextDumper</code>
     *
     * @param textFile Textfile where to dump the Appointmentbook
     */
    TextDumper(String textFile) {
        super();

        if (!CheckValidFileName(textFile))
            throw new CorruptedFile("Please provide a valid file name");

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
    public void dump(Context context, AppointmentBook appointmentBook) {
        if (!CheckValidFileName(this.textFile))
            throw new CorruptedFile("Please provide a valid file name using SetFile method");



        File f = new File(context.getApplicationContext().getFilesDir(), this.textFile);
        // If textFiles exists, delete and then recreate it
        if (f.isFile()) {
            f.delete();
        }
        try {
            f.createNewFile();


            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(this.textFile, Context.MODE_PRIVATE));


            outputStreamWriter.write(appointmentBook.getOwnerName() + "\n");
            for (Appointment appointment : appointmentBook.getAppointments())
                outputStreamWriter.write(appointment.getDescription() + "\t" + appointment.getBeginTimeString() + "\t" + appointment.getEndTimeString() + "\n");

            outputStreamWriter.close();
        } catch (Exception e) {
            throw new CorruptedFile("Please provide a valid file name using SetFile method");
        }
    }
}
