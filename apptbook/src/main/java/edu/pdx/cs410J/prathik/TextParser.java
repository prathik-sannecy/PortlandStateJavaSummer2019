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
