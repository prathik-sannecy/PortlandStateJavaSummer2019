package edu.pdx.cs410J.prathik;

/**
 * This class is represents a <code>CorruptedFile</code>.
 */
public class CorruptedFile extends RuntimeException {
    /**
     * Throw a  <code>CorruptedFile</code> exception for a corrupted/illegally-formatted appointment book text file
     *
     * @param message
     *        Message to display when runtime error is thrown
     */
    CorruptedFile(String message){
        super(message);
    }
}

