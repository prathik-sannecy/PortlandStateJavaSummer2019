package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.io.IOException;
import java.util.Arrays;
import java.io.File;


/**
 * The main class for the CS410J appointment book Project
 */
public class Project2{

    /**
     * Main program that parses the command line, creates a
     * <code>Appointment Book</code> with an <code>Appointment</code>.
     */
    public static void main(String[] args) throws IOException {
        //Appointment appointment = new Appointment();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
        AppointmentBook appointmentbook = new AppointmentBook();
        Appointment appointment = new Appointment();

        boolean printFlag = false;
        boolean READMEFlag = false;
        boolean textFileFlag = false;
        int index_count = 0;
        String owner = "";
        String description = "";
        String beginTime = "";
        String endTime = "";
        String textFile = "";


        // No arguments
        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        // Check any of the optional flags
        READMEFlag = Arrays.asList(args).contains("-README");
        printFlag = Arrays.asList(args).contains("-print");
        textFileFlag = Arrays.asList(args).contains("-textFile");

        // If Readme, print readme and exit
        if (READMEFlag) {
            System.out.println("This program implements an appointment book. \n" +
                    "The user provides the details of an appointment, and this program can print it back in a user-friendly manner.\n\n" +
                    "args are (in this order):\n" +
                    "\towner: The person whose owns the appt book. Use double-quotes for multi-word\n" +
                    "\tdescription: A description of the appointment. Use double-quotes for multi-word\n" +
                    "\tbeginTime: When the appt begins (24-hour time)\n" +
                    "\tendTime: When the appt ends (24-hour time)\n" +
                    "options are (options may appear in any order):\n" +
                    "\t-print: Prints a description of the new appointment\n" +
                    "\t-README: Prints a README for this project and exits\n" +
                    "Date and time should be in the format: mm/dd/yyyy hh:mm");
            System.exit(0);
        }

        // If Print flag is there, increment the argument index count
        if (printFlag) {
            index_count += 1;
        }

        // If text textFile is there (along with the text textFile name), increment the argument index count +2
        if(textFileFlag){
            index_count += 1;
            // If text textFile name is missing, throw an error with corresponding message
            try {
                textFile = args[index_count++].replace("\"", "");
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
            System.err.println("Missing date for appointment begin time");
            System.err.println("Please use the -README flag to see use cases");
            System.exit(1);
        }
        try {
            beginTime = beginTime + " " + args[index_count++];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing time for appointment begin time");
            System.err.println("Please use the -README flag to see use cases");
            System.exit(1);
        }
        try {
            endTime = args[index_count++];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing date for appointment end time");
            System.err.println("Please use the -README flag to see use cases");
            System.exit(1);
        }
        try {
            endTime = endTime + " " + args[index_count];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing time for appointment end time");
            System.err.println("Please use the -README flag to see use cases");
            System.exit(1);
        }

        // If too many arguments, throw an error
        if (args.length > (index_count + 1)) {
            System.err.println("Too many arguments");
            System.err.println("Please use the -README flag to see use cases");
            System.exit(1);
        }

        // Create the appointment book with the appointment using the arguments provided
        appointmentbook.setOwnerName(owner);
        appointment.setDescription(description);
        appointment.setBeginTimeString(beginTime);
        appointment.setEndTimeString(endTime);
        appointmentbook.addAppointment(appointment);

        // If the optional print flag was set, print out the contents of the appointment book
        // and its appointments using the "toString" methods
        if (printFlag) {
            System.out.println(appointmentbook.toString());
            for (Appointment eachAppointment : appointmentbook.getAppointments()) {
                System.out.println(eachAppointment);
            }
        }

        if(textFileFlag){

        }

        System.exit(0);

    }

}