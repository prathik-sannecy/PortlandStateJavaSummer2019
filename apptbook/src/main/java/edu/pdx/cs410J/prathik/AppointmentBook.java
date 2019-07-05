package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;


public class AppointmentBook extends AbstractAppointmentBook<Appointment> {

    private String OwnerName = null;
    private ArrayList<Appointment> AppointmentBook = new ArrayList<>();



    public AppointmentBook(){
        super();
        //new AppointmentBook().toString();
    }

    @Override
    public String getOwnerName(){
        return OwnerName;
    }

    @Override
    public ArrayList<Appointment> getAppointments(){
        return AppointmentBook;

    }

    @Override
    public void addAppointment(Appointment appointment)  {
        AppointmentBook.add(appointment);

    }

    public void setOwnerName(String ownerName) {
        if ((ownerName == null) || (ownerName.equals("")))
            throw new UnsupportedOperationException("Please provide owner name");

        OwnerName = ownerName;
    }


}
