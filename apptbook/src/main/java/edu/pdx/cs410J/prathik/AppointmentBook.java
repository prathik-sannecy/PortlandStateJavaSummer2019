package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class AppointmentBook<T extends AbstractAppointment> extends AbstractAppointmentBook {

    private String OwnerName = null;
    private ArrayList<T> AppointmentBook = new ArrayList<>();



    @Override
    public String getOwnerName(){
        if(OwnerName != null)
            return OwnerName;
        throw new UnsupportedOperationException("Please provide owner name");
    }

    @Override
    public ArrayList<T> getAppointments(){
        return AppointmentBook;

    }

    @Override
    public void addAppointment(AbstractAppointment abstractAppointment) {

    }

    public void setOwnerName(String ownerName) {
        if ((ownerName == null) || (ownerName == ""))
            throw new UnsupportedOperationException("Please provide owner name");

        OwnerName = ownerName;
    }
}
