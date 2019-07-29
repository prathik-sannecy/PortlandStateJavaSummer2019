package edu.pdx.cs410J.prathik;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Appointment Book server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args) {
        boolean printFlag = false;
        boolean READMEFlag = false;
        boolean portFlag = false;
        boolean hostFlag = false;
        boolean searchFlag = false;
        int index_count = 0;
        String owner = "";
        String description = "";
        String beginTime = "";
        String endTime = "";
        String hostname = "";
        int port = 0;

        // No arguments
        if (args.length == 0) {
            System.err.println("Missing command line arguments");
            System.exit(1);
        }

        // Check any of the optional flags
        READMEFlag = Arrays.asList(args).contains("-README");
        printFlag = Arrays.asList(args).contains("-print");
        hostFlag = Arrays.asList(args).contains("-host");
        portFlag = Arrays.asList(args).contains("-port");
        searchFlag = Arrays.asList(args).contains("-search");

        // If Readme, print readme and exit
        if (READMEFlag) {
            README();
        }

        // If Print flag is there, increment the argument index count
        if (printFlag) {
            index_count += 1;
        }

        // If search flag is there, increment the argument index count
        if (searchFlag) {
            index_count += 1;
        }

        // If hostname flag is there (along with the hostname name), increment the argument index count +2
        if(hostFlag){
            index_count += 2;
            // If hostname name is missing, throw an error with corresponding message
            try {
                // hostname name immediately follows the -hostname option
                hostname = args[1+Arrays.asList(args).indexOf("-host")].replace("\"", "");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Missing hostname");
                System.exit(1);
            }
        }

        // If port flag is there (along with the port), increment the argument index count +2
        if(portFlag){
            index_count += 2;
            // If port is missing, throw an error with corresponding message
            try {
                // port immediately follows the -port option
                port = Integer.parseInt(args[1+Arrays.asList(args).indexOf("-port")].replace("\"", ""));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Missing hostname");
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
            if(!searchFlag) {
                description = args[index_count++].replace("\"", "");
            }
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



        AppointmentBookRestClient client = new AppointmentBookRestClient(hostname, port);

        String message;
        try {

            if(searchFlag){
                System.out.print(client.getAppointments(owner, beginTime, endTime));
            }
            else {
                // Set up the newly created appointment
                Appointment appointment = new Appointment(description, beginTime, endTime);

                // If the optional print flag was set, print out the contents of the new appointment using the "toString" methods
                if (printFlag) {
                    print(appointment);
                }
                message = client.addAppointment(owner, description, beginTime, endTime);
            }

        } catch ( IOException ex ) {
            System.err.println("While contacting server: " + ex);
            return;
        }
        System.exit(0);
    }

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

}