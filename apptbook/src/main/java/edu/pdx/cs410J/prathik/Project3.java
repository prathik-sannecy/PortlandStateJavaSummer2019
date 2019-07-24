package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.Collection;
import java.util.Collections;


/**
 * The main class for the CS410J appointment book Project3
 */
public class Project3{

    /**
     * Prints a description of <code>Project2 program</code>
     */
    private static void README(){
        System.out.println("This program implements an appointment book. \n" +
                "The user provides the details of an appointment, and this program can print it back in a user-friendly manner.\n" +
                "Also allows to store/load all pre-existing appointments into an appointment book\n\n" +
                "args are (in this order):\n" +
                "\towner: The person whose owns the appt book. Use double-quotes for multi-word\n" +
                "\tdescription: A description of the appointment. Use double-quotes for multi-word\n" +
                "\tbeginTime: When the appt begins (24-hour time)\n" +
                "\tendTime: When the appt ends (24-hour time)\n" +
                "options are (options may appear in any order):\n" +
                "\t-print: Prints a description of the new appointment\n" +
                "\t-pretty: Pretty prints the appointment(book) to a file (or standard out if -)\n" +
                "\t-README: Prints a README for this project and exits\n" +
                "\t-textFile file: Where to read/write the appointment book\n" +
                "Date and time should be in the format: mm/dd/yyyy hh:mm");
        System.exit(0);
    }

    /**
     * Prints a description of the newly added <code>Appointment</code>
     */
    private static void print(Appointment appointment){
        System.out.println(appointment.toString());
    }

    /**
     * Loads a pre-existing (if it exists) <code>Appointmentbook</code> from <code>textFile</code>,
     * adds the new <code>Appointment</code> to it, and saves it back.
     *
     * Returns back the new appointment book with all the appointments
     *
     * @param textFile The file to load the appointmentbook, where to resave it
     * @param owner The owner of the appointmentbook
     * @param appointment The new appointment to add to the appointmentbook
     */
    private static AppointmentBook textFile(String textFile, String owner, Appointment appointment){
        AppointmentBook appointmentbook;

        TextParser textParser = new TextParser(textFile);
        appointmentbook = textParser.parse();

        // No owner means that the appointbook file was nonexisting - must create new one
        if(appointmentbook == null) {
            appointmentbook = new AppointmentBook(owner);
        }
        else if(!appointmentbook.getOwnerName().equals(owner)){
            System.err.println("Owner mismatch between new appointment and existing appointment book!");
            System.exit(1);
        }
        appointmentbook.addAppointment(appointment);

        TextDumper textDumper = new TextDumper(textFile);
        textDumper.dump(appointmentbook);

        return appointmentbook;
    }

    /**
     * Main program that parses the command line, creates and adds <code>Appointment</code> to
     * <code>Appointment Book</code> . Can also save and retrieve previous appointments from file specified
     */
    public static void main(String[] args) throws IOException {

        boolean printFlag = false;
        boolean READMEFlag = false;
        boolean textFileFlag = false;
        boolean prettyFileFlag = false;
        int index_count = 0;
        String owner = "";
        String description = "";
        String beginTime = "";
        String endTime = "";
        String textFile = "";
        String prettyFile = "";

        // No arguments
        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        // Check any of the optional flags
        READMEFlag = Arrays.asList(args).contains("-README");
        printFlag = Arrays.asList(args).contains("-print");
        textFileFlag = Arrays.asList(args).contains("-textFile");
        prettyFileFlag = Arrays.asList(args).contains("-pretty");

        // If Readme, print readme and exit
        if (READMEFlag) {
            README();
        }

        // If Print flag is there, increment the argument index count
        if (printFlag) {
            index_count += 1;
        }

        // If text textFile is there (along with the text textFile name), increment the argument index count +2
        if(textFileFlag){
            index_count += 2;
            // If text textFile name is missing, throw an error with corresponding message
            try {
                // name of text file immediately follows the -textFile option
                textFile = args[1+Arrays.asList(args).indexOf("-textFile")].replace("\"", "");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Missing text textFile name");
                System.exit(1);
            }
        }

        // If prettyFileFlag pretty is there (along with the text prettyFile name), increment the argument index count +2
        if(prettyFileFlag){
            index_count += 2;
            // If text prettyFile name is missing, throw an error with corresponding message
            try {
                // name of pretty file immediately follows the -pretty option
                prettyFile = args[1+Arrays.asList(args).indexOf("-pretty")].replace("\"", "");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Missing text textFile name");
                System.exit(1);
            }
        }

        // Retrieve all the arguments at each of the indices
        // Throw the corresponding error if an argument is missing
        try {
            owner = args[index_count++].replace("\"", "");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing appointment owner");
            System.exit(1);
        }
        try {
            description = args[index_count++].replace("\"", "");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing appointment description");
            System.exit(1);
        }
        try {
            beginTime = args[index_count++];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing date for appointment begin time\nPlease use the -README flag to see use cases");
            System.exit(1);
        }
        try {
            beginTime = beginTime + " " + args[index_count++];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing time for appointment begin time\nPlease use the -README flag to see use cases");
            System.exit(1);
        }
        try {
            beginTime = beginTime + " " + args[index_count++];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing time for appointment begin time\nPlease use the -README flag to see use cases");
            System.exit(1);
        }
        try {
            endTime = args[index_count++];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing date for appointment end time\nPlease use the -README flag to see use cases");
            System.exit(1);
        }
        try {
            endTime = endTime + " " + args[index_count++];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing time for appointment end time\nPlease use the -README flag to see use cases");
            System.exit(1);
        }
        try {
            endTime = endTime + " " + args[index_count];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing time for appointment end time\nPlease use the -README flag to see use cases");
            System.exit(1);
        }

        // If too many arguments, throw an error
        if (args.length > (index_count + 1)) {
            System.err.println("Too many arguments\nPlease use the -README flag to see use case");
            System.exit(1);
        }

        // Set up the newly created appointment
        Appointment appointment = new Appointment(description, beginTime, endTime);

        // Create appointmentBook object in case pretty flag is used
        AppointmentBook appointmentBook = null;

        //If optional textFile flag was set, read in/create new existing appointmentbook from the file.
        // Then add the appointment to it, and write the appointmentbook back to the file
        if(textFileFlag){
            appointmentBook = textFile(textFile, owner, appointment);
        }

        //If optional pretty flag was set, sort the appointmentBook and store it into the prettyFile
        if(prettyFileFlag){
            PrettyPrinter prettyPrinter = new PrettyPrinter(prettyFile);
            if(appointmentBook == null){
                appointmentBook = new AppointmentBook(owner);
                appointmentBook.addAppointment(appointment);

            }
            prettyPrinter.dump(appointmentBook);
        }

        // If the optional print flag was set, print out the contents of the new appointment using the "toString" methods
        if (printFlag) {
            print(appointment);
        }
        System.exit(0);
    }


}