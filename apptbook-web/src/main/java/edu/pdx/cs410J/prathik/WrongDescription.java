package edu.pdx.cs410J.prathik;

/**
 * This class is represents a <code>WrongDescription</code>.
 */

class WrongDescription extends RuntimeException {
    /**
     * Throw a  <code>WrongDescription</code> exception for an appointment
     *
     * @param message
     *        Message to display when runtime error is thrown
     */

    WrongDescription(String message) {
        super((message));
    }
}
