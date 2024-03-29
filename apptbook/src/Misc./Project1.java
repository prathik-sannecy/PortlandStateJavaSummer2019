package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointmentBook;


/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {

    /**
     * Main program that parses the command line, creates a
     * <code>Appointment Book</code> with an <code>Appointment</code>.
     */
    public static void main(String[] args) {
        boolean printFlag = false;
        boolean READMEFlag = false;
        int index_count = 0;
        String owner = "";
        String description = "";
        String beginTime = "";
        String endTime = "";

        // No arguments
        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        // Check any of the optional flags
        if (args.length == 1) {
            READMEFlag = (args[0].equals("-README"));
        } else {
            printFlag = (args[0].equals("-print")) || (args[1].equals("-print"));
            READMEFlag = (args[0].equals("-README")) || (args[1].equals("-README"));
        }

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

        // If Print flag is there, start collecting arguments from the following index
        if (printFlag) {
            index_count += 1;
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
        AppointmentBook appointmentbook = new AppointmentBook(owner);
        Appointment appointment = new Appointment(description, beginTime, endTime);
        appointmentbook.addAppointment(appointment);

        // If the optional print flag was set, print out the contents of the appointment book
        // and its appointments using the "toString" methods
        if (printFlag) {
            System.out.println(appointmentbook.toString());
            for (Appointment eachAppointment : appointmentbook.getAppointments()) {
                System.out.println(eachAppointment);
            }
        }

        System.exit(0);

    }

}