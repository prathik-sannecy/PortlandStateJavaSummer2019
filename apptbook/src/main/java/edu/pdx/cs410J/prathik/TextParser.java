package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AppointmentBookParser;

import java.io.File;

public class TextParser implements AppointmentBookParser<AppointmentBook> {

    String textFile = "";

    TextParser(){
        super();
    }

    public AppointmentBook parse(){
        return new AppointmentBook();

    }

}
