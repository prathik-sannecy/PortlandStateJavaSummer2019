package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;


public class AppointmentBook<T extends AbstractAppointment> extends AbstractAppointmentBook<T> {

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
    public void addAppointment(T appointment)  {
        AppointmentBook.add(appointment);

    }

    public void setOwnerName(String ownerName) {
        if ((ownerName == null) || (ownerName == ""))
            throw new UnsupportedOperationException("Please provide owner name");

        OwnerName = ownerName;
    }
}
