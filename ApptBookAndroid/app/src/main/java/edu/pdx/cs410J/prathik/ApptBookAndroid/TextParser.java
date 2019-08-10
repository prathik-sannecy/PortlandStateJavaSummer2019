package edu.pdx.cs410J.prathik.ApptBookAndroid;


import android.content.Context;

import java.io.File;
import java.util.Scanner;

public class TextParser {

    String textFile = "";

    /**
     * Creates a new <code>TextDumper</code>
     *
     * @param textFile Textfile where to dump the Appointmentbook
     */
    TextParser(String textFile){
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
     * Loads a (valid) file into an <code>AppointmentBook</code>
     */
    public AppointmentBook parse(Context context){
        if (!CheckValidFileName(this.textFile))
            throw new CorruptedFile("Please provide a valid file name using SetFile method");


        File file = new File(context.getApplicationContext().getFilesDir(), this.textFile);

        if(!file.isFile())
            return null;

        else if(file.length() == 0)
            throw new CorruptedFile("The provided appointment book text file is corrupted!");

        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[\\t\\n]");
            AppointmentBook appointmentBook = new AppointmentBook(scanner.next());
            while(scanner.hasNext()) {
                String description = scanner.next();
                String beginTime = scanner.next();
                String endTime = scanner.next();
                Appointment appointment = new Appointment(description, beginTime, endTime);
                appointmentBook.addAppointment(appointment);
            }
            scanner.close();
            return appointmentBook;
        } catch (Exception e){
            throw new CorruptedFile("The provided appointment book text file is corrupted!");
        }

    }

}
