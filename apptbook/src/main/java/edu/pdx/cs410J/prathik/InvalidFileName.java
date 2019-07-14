package edu.pdx.cs410J.prathik;

/**
 * This class is represents a <code>InvalidFileName</code>.
 */
public class InvalidFileName extends RuntimeException {
    /**
     * Throw a  <code>InvalidFileName</code> exception for a file to parse
     *
     * @param message
     *        Message to display when runtime error is thrown
     */
    InvalidFileName(String message){
        super(message);
    }
}


