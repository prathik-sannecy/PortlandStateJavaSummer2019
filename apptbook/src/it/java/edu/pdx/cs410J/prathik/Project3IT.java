package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Scanner;

/**
 * Integration tests for the {@link Project3} main class.
 */
public class Project3IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
    }

    private void DeleteFile(String fileName){
        File f = new File(fileName);
        if(f.isFile()){
            f.delete();
        }
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    /**
     * Test that invoking the main method with -README first flag
     */
    @Test
    public void testREADMEFirstFlag() {
        MainMethodResult result = invokeMain("-README");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("implements an appointment book"));

    }

    /**
     * Test that invoking the main method with -README first flag, dummy data after
     */
    @Test
    public void testREADMEFirstFlagWithDummyAfter() {
        MainMethodResult result = invokeMain("-README", "Dummy", "Data");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("implements an appointment book"));

    }

    /**
     * Test that invoking the main method with -README second flag
     */
    @Test
    public void testREADMESecondFlag() {
        MainMethodResult result = invokeMain("-print", "-README");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("implements an appointment book"));

    }

    /**
     * Tests that gives valid command line format gives no error
     */
    @Test
    public void testInvalidCommandLineArguments() {
        MainMethodResult result = invokeMain("\"Bob Swan Jr.\"", "\"Eating\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
    }

    /**
     * Tests that gives -print statement still gives correct missing argument
     */
    @Test
    public void testPrintOptionWithMissingArgument() {
        MainMethodResult result = invokeMain("-print", "\"Bob Swan Jr.\"");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing appointment description"));

    }

    /**
     * Tests that tells the correct missing argument
     */
    @Test
    public void testMissingArguments() {
        MainMethodResult result = invokeMain( "\"Bob Swan Jr.\"");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing appointment description"));

    }

    /**
     * Tests for too many argugments
     */
    @Test
    public void testTooManyArguments() {
        MainMethodResult result = invokeMain( "\"Bob Swan Jr.\"", "\"Eating\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM", "dummy");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Too many arguments"));

    }

    /**
     * Print option provides appointment in correct format
     */
    @Test
    public void testPrintOption() {
        MainMethodResult result = invokeMain("-print", "\"Bob Swan Jr.\"", "\"Eating\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Eating from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
    }

    /**
     * Using the textfile option doesn't break
    */
    @Test
    public void testTextFileOptionDoesNotBreak() {
        String fileName = "file";
        DeleteFile(fileName);
        MainMethodResult result = invokeMain("-textFile", fileName, "\"Bob Swan Jr.\"", "\"Eating\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
    }

    /**
     * Using the textfile option doesn't break with print option
    */
    @Test
    public void testTextFileOptionDoesNotBreakOtherOptions() {
        String fileName = "file";
        DeleteFile(fileName);
        MainMethodResult result = invokeMain("-textFile", fileName, "-print", "\"Bob Swan Jr.\"", "\"Eating\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Eating from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
    }

    /**
     * Using the textfile option doesn't break with print option regardless of order
    */
    @Test
    public void testTextFileOptionOrderDoesNotMatter() {
        String fileName = "file";
        DeleteFile(fileName);
        MainMethodResult result = invokeMain("-print", "-textFile", fileName, "\"Bob Swan Jr.\"", "\"Eating\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Eating from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
    }


    /**
     * README option still works with textfile option
    */
    @Test
    public void testTextFileOptionDoesNotBreakReadme() {
        String fileName = "file";
        DeleteFile(fileName);
        MainMethodResult result = invokeMain("-textFile", fileName, "-README", "\"Bob Swan Jr.\"", "\"Eating\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("implements an appointment book"));
    }


    /**
     * texfile option creates new textFile
    */
    @Test
    public void testTextFileOptionCreatesFile() {
        String fileName = "file";
        DeleteFile(fileName);
        MainMethodResult result = invokeMain("-textFile", fileName, "\"Bob Swan Jr.\"", "\"Eating\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(new File(fileName).isFile(), equalTo(true));
        // File contains appointment
    }


    /**
     * texfile option adds appointment to new appointment book
    */
    @Test
    public void testTextFileOptionCreatesNewAppointmentToAppointmentBook() {
        String fileName = "file";
        DeleteFile(fileName);
        String owner = "Bob";
        String description = "eating burger";
        String beginTime = "03/17/1996 03:43 AM";
        String endTime = "03/17/1997 03:44 PM";

        MainMethodResult result = invokeMain("-textFile", fileName, owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));

        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[\\t\\n]");
            String scannerOwner = scanner.next();
            String descriptionString = scanner.next();
            String beginTimeString = scanner.next();
            String endTimeString = scanner.next();
            assertThat(owner, equalTo(scannerOwner));
            assertThat(description, equalTo(descriptionString));
            assertThat(beginTime, equalTo(beginTimeString));
            assertThat(endTime, equalTo(endTimeString));
            assertThat(scanner.hasNext(), equalTo(false));
            scanner.close();
        } catch (Exception e){

        }
    }

    /**
     * texfile option adds appointment to existing appointment book
     */
    @Test
    public void testTextFileOptionCreatesAdditionalAppointmentToExistingAppointmentBook() {
        String fileName = "file";
        DeleteFile(fileName);
        String owner = "Bob";
        String description1 = "eating burger";
        String beginTime1 = "01/06/1946 11:43 AM";
        String endTime1 = "05/13/4447 03:48 AM";

        String description2 = "taking nap";
        String beginTime2 = "03/17/1996 03:43 AM";
        String endTime2 = "03/17/1997 03:44 PM";

        MainMethodResult result = invokeMain("-textFile", fileName, owner, "\"eating burger\"",  "01/6/1946" ,"11:43", "AM", "05/13/4447", "03:48", "AM");
        assertThat(result.getExitCode(), equalTo(0));

        result = invokeMain("-textFile", fileName, owner, "\"taking nap\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));

        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[\\t\\n]");
            String scannerOwner = scanner.next();
            String descriptionString1 = scanner.next();
            String beginTimeString1 = scanner.next();
            String endTimeString1 = scanner.next();
            String descriptionString2 = scanner.next();
            String beginTimeString2 = scanner.next();
            String endTimeString2 = scanner.next();
            assertThat(owner, equalTo(scannerOwner));
            assertThat(description1, equalTo(descriptionString1));
            assertThat(beginTime1, equalTo(beginTimeString1));
            assertThat(endTime1, equalTo(endTimeString1));
            assertThat(description2, equalTo(descriptionString2));
            assertThat(beginTime2, equalTo(beginTimeString2));
            assertThat(endTime2, equalTo(endTimeString2));
            assertThat(scanner.hasNext(), equalTo(false));
            scanner.close();
        } catch (Exception e){

        }

    }

    /**
     * The appointmentBook should self-sort
     */
    @Test
    public void testAppointmentBookSorted() {
        String fileName = "file";
        DeleteFile(fileName);
        String owner = "Bob";
        String description1 = "eating burger";
        String beginTime1 = "03/17/1996 03:43 AM";
        String endTime1 = "03/17/1997 03:44 PM";

        String description2 = "taking nap";
        String beginTime2 = "01/06/1946 11:43 AM";
        String endTime2 = "05/13/4447 03:48 AM";

        MainMethodResult result = invokeMain("-textFile", fileName, owner, "\"eating burger\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));

        result = invokeMain("-textFile", fileName, owner, "\"taking nap\"", "01/6/1946" ,"11:43", "AM", "05/13/4447", "03:48", "AM");
        assertThat(result.getExitCode(), equalTo(0));

        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[\\t\\n]");
            String scannerOwner = scanner.next();
            String descriptionString2 = scanner.next();
            String beginTimeString2 = scanner.next();
            String endTimeString2 = scanner.next();
            String descriptionString1 = scanner.next();
            String beginTimeString1 = scanner.next();
            String endTimeString1 = scanner.next();
            assertThat(owner, equalTo(scannerOwner));
            assertThat(description1, equalTo(descriptionString1));
            assertThat(beginTime1, equalTo(beginTimeString1));
            assertThat(endTime1, equalTo(endTimeString1));
            assertThat(description2, equalTo(descriptionString2));
            assertThat(beginTime2, equalTo(beginTimeString2));
            assertThat(endTime2, equalTo(endTimeString2));
            assertThat(scanner.hasNext(), equalTo(false));
            scanner.close();
        } catch (Exception e){

        }

    }

    // Owner Mismatch
    @Test
    public void testTextFileOptionOwnerMismatchCreatesError() {
        String fileName = "file";
        DeleteFile(fileName);
        String owner1 = "Bob";
        String owner2 = "John";

        MainMethodResult result = invokeMain("-textFile", fileName, owner1, "\"eating burger\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));

        result = invokeMain("-textFile", fileName, owner2, "\"taking nap\"", "01/6/1946" ,"11:43", "PM", "05/13/4447", "03:48", "PM");
        assertThat(result.getExitCode(), equalTo(1));

        assertThat(result.getTextWrittenToStandardError(), containsString("Owner mismatch"));

    }

    // Different file directories
    @Test
    public void testTextFileOptionFileInAnotherFolder() {
        String fileName1 = "file";
        String fileName2 = "../file";
        DeleteFile(fileName1);
        String owner = "Bob";
        String description1 = "eating burger";
        String beginTime1 = "03/17/1996 03:43";
        String endTime1 = "03/17/1997 03:44";

        String description2 = "taking nap";
        String beginTime2 = "01/6/1946 23:43";
        String endTime2 = "05/13/4447 03:48";

        MainMethodResult result = invokeMain("-textFile", fileName1, owner, "\"eating burger\"", "03/17/1996" ,"03:43", "AM",  "03/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));

        try {
            Files.move(Paths.get(fileName1), Paths.get(fileName2));
        } catch (Exception e){

        }
        result = invokeMain("-textFile", fileName2, owner, "\"taking nap\"", "01/6/1946" ,"11:43", "PM", "05/13/4447", "03:48", "PM");
        assertThat(result.getExitCode(), equalTo(0));

        File file = new File(fileName1);
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("[\\t\\n]");
            String scannerOwner = scanner.next();
            String descriptionString1 = scanner.next();
            String beginTimeString1 = scanner.next();
            String endTimeString1 = scanner.next();
            String descriptionString2 = scanner.next();
            String beginTimeString2 = scanner.next();
            String endTimeString2 = scanner.next();
            assertThat(owner, equalTo(scannerOwner));
            assertThat(description1, equalTo(descriptionString1));
            assertThat(beginTime1, equalTo(beginTimeString1));
            assertThat(endTime1, equalTo(endTimeString1));
            assertThat(description2, equalTo(descriptionString2));
            assertThat(beginTime2, equalTo(beginTimeString2));
            assertThat(endTime2, equalTo(endTimeString2));
            assertThat(scanner.hasNext(), equalTo(false));
            scanner.close();
        } catch (Exception e){

        }

        DeleteFile(fileName2);
    }



    /**
     * pretty option adds appointment to new appointment book, prints to stardard out
     */
    @Test
    public void prettyFileOptionCreatesNewAppointmentToAppointmentBookStandardOut() {
        String fileName = "-";
        DeleteFile(fileName);
        String owner = "Bobs";

        MainMethodResult result = invokeMain("-pretty", fileName, owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("eating burger from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
    }

    /**
     * pretty option adds appointment to new appointment book, prints to file
     */
    @Test
    public void prettyFileOptionCreatesNewAppointmentToAppointmentBookPrintsFile() {
        String fileName = "pretty";
        DeleteFile(fileName);
        String owner = "Bob";

        MainMethodResult result = invokeMain("-pretty", fileName, owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1996", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));

        try {
            Scanner scanner = new Scanner(new File(fileName));
            assertThat(scanner.nextLine(), containsString("Bob"));
            assertThat(scanner.nextLine(), containsString("eating burger from 03/17/1996 03:43 AM until 03/17/1996 03:44 PM"));
            scanner.close();
        } catch (Exception e) {

        }

    }

    /**
     * pretty option adds appointment to existing appointment book, prints to stardard out
     */
    @Test
    public void prettyFileOptionAddsAppointmentToAppointmentBookStandardOut() {
        String fileName = "-";
        String textFile = "textFile";
        DeleteFile(fileName);
        DeleteFile(textFile);
        String owner = "Bobs";

        MainMethodResult result = invokeMain("-textFile", textFile, owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        result = invokeMain("-textFile", textFile, "-pretty", fileName, owner, "\"always sleeping\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("always sleeping from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("eating burger from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
    }

    /**
     * pretty option adds appointment to existing appointment book, prints to file
     */
    @Test
    public void prettyFileOptionAddsAppointmentToAppointmentBookPrintsFile() {
        String fileName = "pretty";
        String textFile = "textFile";
        DeleteFile(fileName);
        DeleteFile(textFile);
        String owner = "Bobs";

        MainMethodResult result = invokeMain("-textFile", textFile, owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        result = invokeMain("-textFile", textFile, "-pretty", fileName, owner, "\"always sleeping\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));

        try {
            Scanner scanner = new Scanner(new File(fileName));
            assertThat(scanner.nextLine(), containsString("Bob"));
            assertThat(scanner.nextLine(), containsString("always sleeping from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
            assertThat(scanner.nextLine(), containsString("eating burger from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
            scanner.close();
        } catch (Exception e) {

        }

    }

    // Pretty option with print option
    @Test
    public void prettyFileOptionWithPrintOption() {
        String fileName = "-";
        DeleteFile(fileName);
        String owner = "Bobs";

        MainMethodResult result = invokeMain("-pretty", fileName,  "-print", owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        String textout = result.getTextWrittenToStandardOut();
        try {
            assertThat(result.getTextWrittenToStandardOut(), containsString("Bob"));
            assertThat(result.getTextWrittenToStandardOut(), containsString("Appointment1 Duration 526321 minutes: eating burger from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM\neating burger from 03/17/1996 03:43 AM until 03/17/1997 03:44 PM"));
        } catch (Exception e) {

        }

    }



    // Pretty Option with no text file generates error
    @Test
    public void prettyFileOptionWithNoOption() {
        String fileName = "-";
        DeleteFile(fileName);
        String owner = "Bobs";

        MainMethodResult result = invokeMain("-pretty", "-print", owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(1));

    }

    // Pretty Option with no text file generates error
    @Test
    public void prettyFileOptionWithNoOption2() {
        String fileName = "-";
        DeleteFile(fileName);
        String owner = "Bobs";

        MainMethodResult result = invokeMain("-print", "-pretty",  owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(1));

    }

    // Pretty Option with README works
    @Test
    public void prettyFileOptionWithREADMEOption1() {
        String fileName = "-";
        DeleteFile(fileName);
        String owner = "Bobs";

        MainMethodResult result = invokeMain("-README", "-pretty",  owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("implements an appointment book"));

    }

    // Pretty Option with README works (README is second flag)
    @Test
    public void prettyFileOptionWithREADMEOption2() {
        String fileName = "-";
        DeleteFile(fileName);
        String owner = "Bobs";

        MainMethodResult result = invokeMain("-pretty", "-README", owner, "\"eating burger\"", "03/17/1996" ,"3:43", "AM",  "3/17/1997", "03:44", "PM");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("implements an appointment book"));

    }


}