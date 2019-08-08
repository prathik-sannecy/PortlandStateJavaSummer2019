package edu.pdx.cs410J.prathik.ApptBookAndroid;


import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is represents a <code>AppointmentBook</code>.
 */
public class AppointmentBook{

    private String OwnerName = null;
    private ArrayList<Appointment> AppointmentBook = new ArrayList<>();

    /**
     * Creates a new <code>AppointmentBook</code>
     *
     * @param ownerName The owner of the appointment book
     */
    public AppointmentBook(String ownerName){
        super();
        this.setOwnerName(ownerName);
    }

    /**
     * Returns a <code>String</code> that tells the owner of the <code>AppointmentBook</code>
     */
    public String getOwnerName(){
        return this.OwnerName;
    }

    /**
     * Returns a  <code>ArrayList</code> of <code>Appointment</code> in the <code>AppointmentBook</code>
     */
    public ArrayList<Appointment> getAppointments(){
        return this.AppointmentBook;

    }

    /**
     * Adds an <code>Appointment</code> to the <code>AppointmentBook</code>
     *
     * @param appointment The appointment to add
     */
    public void addAppointment(Appointment appointment)  {
        this.AppointmentBook.add(appointment);
        Collections.sort(AppointmentBook);

    }

    /**
     * Sets the owner of the <code>AppointmentBook</code>
     *
     * @param ownerName The owner of the AppointmentBook
     */
    private void setOwnerName(String ownerName) {
        if ((ownerName == null) || (ownerName.equals("")))
            throw new UnsupportedOperationException("Please provide owner name");

        this.OwnerName = ownerName;
    }


    public final String toString() {
        String var10000 = this.getOwnerName();
        return var10000 + "'s appointment book with " + this.getAppointments().size() + " appointments";
    }

}
