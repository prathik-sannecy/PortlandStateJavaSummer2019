package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.File;
import java.io.FileWriter;

public class TextDumper implements AppointmentBookDumper<AppointmentBook> {

    String textFile = "";
    TextDumper(){
        super();
    }

    public void SetFile(String fileName){
        if ((fileName == null) || (fileName.equals("")))
            throw new InvalidFileName("Please provide a valid file name");
        this.textFile = fileName;
    }

    public String GetFile(){
        return this.textFile;
    }

    public void dump(AppointmentBook appointmentBook){
        File f = new File(this.textFile);
        // If textFiles exists, delete and then recreate it
        if(f.isFile()) {
            f.delete();
        }
        try {
            f.createNewFile();


        FileWriter fw = new FileWriter(textFile);

        fw.write(appointmentBook.getOwnerName() + "\n");
        for(Appointment appointment : appointmentBook.getAppointments())
            fw.write(appointment.getDescription() + " " + appointment.getBeginTimeString() + " " + appointment.getEndTimeString() + "\n");

        fw.close();
        } catch (Exception e){
            throw new InvalidFileName("Please provide a valid file name");
        }
    }
}
