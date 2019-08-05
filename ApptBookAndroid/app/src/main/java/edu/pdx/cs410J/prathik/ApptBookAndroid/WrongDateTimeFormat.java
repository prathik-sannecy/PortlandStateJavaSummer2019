package edu.pdx.cs410J.prathik.ApptBookAndroid;


/**
 * This class is represents a <code>WrongDateTimeFormat</code>.
 */
class WrongDateTimeFormat extends RuntimeException {
    /**
     * Throw a  <code>WrongDateTimeFormat</code> exception for an appointment
     *
     * @param message
     *        Message to display when runtime error is thrown
     */
    WrongDateTimeFormat(String message){
        super(message);
    }
}
