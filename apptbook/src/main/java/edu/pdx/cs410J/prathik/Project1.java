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
    //Appointment appointment = new Appointment();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    AppointmentBook appointmentbook = new AppointmentBook();
    Appointment appointment = new Appointment();

    boolean printFlag = false;
    boolean READMEFlag = false;
    int index_count = 0;
    String owner = "";
    String description = "";
    String beginTime = "";
    String endTime = "";

    if(args.length == 0) {
      System.err.println("Missing command line arguments");
      System.exit(1);
    }

    if(args.length == 1) {
      READMEFlag = (args[0].equals("-README"));
    } else {
      printFlag = (args[0].equals("-print")) || (args[1].equals("-print"));
      READMEFlag = (args[0].equals("-README")) || (args[1].equals("-README"));
    }


    if (READMEFlag){
      System.out.println("This program implements an appointment book. \n" +
              "The user provides the details of an appointment, and this program can print it back in a user-friendly manner.\n\n" +
              "args are (in this order):\n" +
              "\towner: The person whose owns the appt book\n" +
              "\tdescription: A description of the appointment\n" +
              "\tbeginTime: When the appt begins (24-hour time)\n" +
              "\tendTime: When the appt ends (24-hour time)\n" +
              "options are (options may appear in any order):\n" +
              "\t-print: Prints a description of the new appointment\n" +
              "\t-README: Prints a README for this project and exits\n" +
                      "Date and time should be in the format: mm/dd/yyyy hh:mm");
      System.exit(0);
    }


    if (printFlag) index_count += 1;

    try {
      owner = args[index_count++].replace("\"", "");
//      owner = args[index_count++];
//      if(owner.startsWith("\"")){
//        while(!args[index_count].endsWith("\""))
//          owner = owner + args[index_count++];
//      }
    } catch (ArrayIndexOutOfBoundsException e){
      System.err.println("Missing appointment owner");
      System.exit(1);
    }

    try {
      description = args[index_count++].replace("\"", "");
//      description = args[index_count++];
//      if(description.startsWith("\"")){
//        while(!args[index_count].endsWith("\""))
//          description = description + args[index_count++];
//      }
    } catch (ArrayIndexOutOfBoundsException e){
      System.err.println("Missing appointment description");
      System.exit(1);
    }

    try {
      beginTime = args[index_count++];
//      beginTime = beginTime + args[index_count++];
    } catch (ArrayIndexOutOfBoundsException e){
      System.err.println("Missing date for appointment begin time");
      System.err.println("Please use the -README flag to see use cases");
      System.exit(1);
    }

    try {
      beginTime = beginTime + " " + args[index_count++];
//      beginTime = beginTime + args[index_count++];
    } catch (ArrayIndexOutOfBoundsException e){
      System.err.println("Missing time for appointment begin time");
      System.err.println("Please use the -README flag to see use cases");
      System.exit(1);
    }

    try {
      endTime = args[index_count++];
//      endTime = endTime + args[index_count++];
    } catch (ArrayIndexOutOfBoundsException e){
      System.err.println("Missing date for appointment end time");
      System.err.println("Please use the -README flag to see use cases");
      System.exit(1);
    }

    try {
      endTime = endTime + " " + args[index_count];
//      endTime = endTime + args[index_count++];
    } catch (ArrayIndexOutOfBoundsException e){
      System.err.println("Missing time for appointment end time");
      System.err.println("Please use the -README flag to see use cases");
      System.exit(1);
    }

    if(args.length > (index_count + 1)){
      System.err.println("Too many arguments");
      System.err.println("Please use the -README flag to see use cases");
      System.exit(1);
    }

    appointmentbook.setOwnerName(owner);
    appointment.setDescription(description);
    appointment.setBeginTimeString(beginTime);
    appointment.setEndTimeString(endTime);
    appointmentbook.addAppointment(appointment);

    if(printFlag){
      System.out.println(appointmentbook.toString());
      for(Appointment eachAppointment : appointmentbook.getAppointments()){
        System.out.println(eachAppointment);
      }
    }

    System.exit(0);

  }

}