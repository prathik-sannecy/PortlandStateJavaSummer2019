package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import java.io.File;
import java.util.Scanner;

/**
 * Integration tests for the {@link Project2} main class.
 */
public class Project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project2} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
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
        MainMethodResult result = invokeMain("\"Bob Swan Jr.\"", "\"Eating\"", "7/15/2019", "14:39", "7/16/2019", "14:39");
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
        MainMethodResult result = invokeMain( "\"Bob Swan Jr.\"", "\"Eating\"", "7/15/2019" ,"14:39", "7/16/2019", "14:39", "dummy");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Too many arguments"));

    }

    /**
     * Print option provides appointment in correct format
     */
    @Test
    public void testPrintOption() {
        MainMethodResult result = invokeMain("-print", "\"Bob Swan Jr.\"", "\"Eating\"", "7/15/2019" ,"14:39", "7/16/2019", "14:39");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Bob Swan Jr.'s appointment book with 1 appointments"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Eating from 7/15/2019 14:39 until 7/16/2019 14:39"));
    }

    /**
     * Using the textfile option doesn't break
    */
    @Test
    public void testTextFileOptionDoesNotBreak() {
        String fileName = "file";
        DeleteFile(fileName);
        MainMethodResult result = invokeMain("-textFile", fileName, "\"Bob Swan Jr.\"", "\"Eating\"", "7/15/2019" ,"14:39", "7/16/2019", "14:39");
        assertThat(result.getExitCode(), equalTo(0));
    }

    /**
     * Using the textfile option doesn't break with print option
    */
    @Test
    public void testTextFileOptionDoesNotBreakOtherOptions() {
        String fileName = "file";
        DeleteFile(fileName);
        MainMethodResult result = invokeMain("-textFile", fileName, "-print", "\"Bob Swan Jr.\"", "\"Eating\"", "7/15/2019" ,"14:39", "7/16/2019", "14:39");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Bob Swan Jr.'s appointment book with 1 appointments"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Eating from 7/15/2019 14:39 until 7/16/2019 14:39"));
    }


    /**
     * README option still works with textfile option
    */
    @Test
    public void testTextFileOptionDoesNotBreakReadme() {
        String fileName = "file";
        DeleteFile(fileName);
        MainMethodResult result = invokeMain("-textFile", fileName, "-README", "\"Bob Swan Jr.\"", "\"Eating\"", "7/15/2019" ,"14:39", "7/16/2019", "14:39");
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
        MainMethodResult result = invokeMain("-textFile", fileName, "\"Bob Swan Jr.\"", "\"Eating\"", "7/15/2019" ,"14:39", "7/16/2019", "14:39");
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(new File(fileName).isFile(), equalTo(true));
    }

    /**
     * texfile option puts new appointment into textfile
    */
    @Test
    public void testTextFileOptionCreatesAppointment() {
        String fileName = "file";
        DeleteFile(fileName);
        String appointment = "\"Eating\" 7/15/2019 14:39 7/16/2019 14:39";
        MainMethodResult result = invokeMain("-print", "-textFile", fileName,  "\"Bob Swan Jr.\"", "\"Eating\"", "7/15/2019" ,"14:39", "7/16/2019", "14:39");
        assertThat(result.getExitCode(), equalTo(0));

        boolean containsAppointment = false;

        final Scanner scanner = new Scanner(fileName);
        while (scanner.hasNextLine()) {
            final String lineFromFile = scanner.nextLine();
            if(lineFromFile.contains(appointment)) {
                // a match!
                containsAppointment = true;
                break;
            }
        }

        assertThat(containsAppointment, equalTo(true));
    }





}