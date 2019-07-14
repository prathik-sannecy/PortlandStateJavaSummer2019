package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AppointmentBookParser;

import java.io.File;
import java.util.Scanner;

public class TextParser implements AppointmentBookParser<AppointmentBook> {

    String textFile = "";

    TextParser(String textFile){
        super();

        if (!CheckValidFileName(textFile))
            throw new InvalidFileName("Please provide a valid file name");

        this.textFile = textFile;
    }

    private boolean CheckValidFileName(String textFile){
        return !((textFile == null) || (textFile.equals("")));
    }


    public AppointmentBook parse(){
        if (!CheckValidFileName(this.textFile))
            throw new InvalidFileName("Please provide a valid file name using SetFile method");


        File file = new File(this.textFile);
        AppointmentBook appointmentBook = new AppointmentBook();

        if((!file.isFile()) || (file.length() == 0))
            return appointmentBook;

        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[\\t\\n]");
            appointmentBook.setOwnerName(scanner.next());
            while(scanner.hasNext()) {
                Appointment appointment = new Appointment();
                appointment.setDescription(scanner.next());
                appointment.setBeginTimeString(scanner.next());
                appointment.setEndTimeString(scanner.next());
                appointmentBook.addAppointment(appointment);
            }
            scanner.close();
        } catch (Exception e){
            throw new CorruptedFile("The provided appointment book text file is corrupted!");
        }
        return appointmentBook;

    }

}
