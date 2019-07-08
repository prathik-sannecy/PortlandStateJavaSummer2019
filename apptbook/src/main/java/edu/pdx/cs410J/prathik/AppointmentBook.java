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
        return this.OwnerName;
    }

    @Override
    public ArrayList<Appointment> getAppointments(){
        return this.AppointmentBook;

    }

    @Override
    public void addAppointment(Appointment appointment)  {
        this.AppointmentBook.add(appointment);

    }

    public void setOwnerName(String ownerName) {
        if ((ownerName == null) || (ownerName.equals("")))
            throw new UnsupportedOperationException("Please provide owner name");

        this.OwnerName = ownerName;
    }


}
