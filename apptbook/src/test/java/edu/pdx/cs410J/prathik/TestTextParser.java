package edu.pdx.cs410J.prathik;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link TextParser} class.
 */
public class TestTextParser {

    @Test
    public void TestCreatingTextParserClass() {
        TextParser textParser = new TextParser();
    }

    @Test
    public void TestSettingFileInTextDumperClass() {
        String fileName = "file";
        TextParser textParser = new TextParser();
        textParser.SetFile(fileName);
        assertThat(textParser.GetFile(), is(fileName));
    }



    // Can't set empty String
    @Test(expected = InvalidFileName.class)
    public void TestSettingNullFileName() {
        String fileName = "";
        TextParser textParser = new TextParser();
        textParser.SetFile(fileName);
    }

    @Test
    public void TestParsingNonexistingFileName() {
        String fileName = "NonExistingFile";
        TextParser textParser = new TextParser();
        textParser.SetFile(fileName);
        textParser.parse();
    }

    @Test
    public void TestParsingEmptyFile() {
        TextDumperTest.DeleteFile("EmptyFile");
        File f = new File("EmptyFile");
        try {
            f.createNewFile();
        } catch (Exception e) {
        }

        String fileName = "EmptyFile";
        TextParser textParser = new TextParser();
        textParser.SetFile(fileName);
        textParser.parse();
    }

    // Trying to parse  text file without setting file name first
    @Test(expected = InvalidFileName.class)
    public void TestDumpingIntoInvalidTextFile() {
        TextParser textParser = new TextParser();
        textParser.parse();
    }

    // Only owner, no appointments
    @Test
    public void TestCreatingNoAppointment() {
        String fileName = "file";
        String owner = "Bob";
        TextDumperTest.DeleteFile(fileName);
        File file = new File(fileName);


        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(fileName);
            fw.write(owner);
            fw.close();
        } catch (Exception e){

        }

        TextParser textParser = new TextParser();
        textParser.SetFile(fileName);
        AppointmentBook appointmentbook = textParser.parse();

        assertThat(appointmentbook.getAppointments().size(), equalTo(0));
        assertThat(owner, equalTo(appointmentbook.getOwnerName()));
    }

    // Single Appointment
    @Test
    public void TestCreatingSingleAppointment() {
        String fileName = "file";
        String owner = "Bob";
        String description = "eating burger";
        String beginTime = "03/17/1996 03:43";
        String endTime = "03/17/1997 03:44";

        TextDumperTest textDumperTest = new TextDumperTest();
        textDumperTest.TestDumpingSingleAppointment();

        TextParser textParser = new TextParser();
        textParser.SetFile(fileName);
        AppointmentBook appointmentbook = textParser.parse();

        assertThat(appointmentbook.getAppointments().size(), equalTo(1));
        assertThat(owner, equalTo(appointmentbook.getOwnerName()));
        assertThat(description, equalTo(appointmentbook.getAppointments().get(0).getDescription()));
        assertThat(beginTime, equalTo(appointmentbook.getAppointments().get(0).getBeginTimeString()));
        assertThat(endTime, equalTo(appointmentbook.getAppointments().get(0).getEndTimeString()));
    }

    // Multiple appointments
    @Test
    public void TestCreatingMultipleAppointment() {
        String fileName = "file";
        String owner = "Bob";
        String description1 = "eating burger";
        String beginTime1 = "03/17/1996 03:43";
        String endTime1 = "03/17/1997 03:44";

        String description2 = "taking nap";
        String beginTime2 = "01/6/1946 23:43";
        String endTime2 = "05/13/4447 03:48";

        TextDumperTest textDumperTest = new TextDumperTest();
        textDumperTest.TestDumpingMultipleAppointment();

        TextParser textParser = new TextParser();
        textParser.SetFile(fileName);
        AppointmentBook appointmentbook = textParser.parse();

        assertThat(appointmentbook.getAppointments().size(), equalTo(2));
        assertThat(owner, equalTo(appointmentbook.getOwnerName()));
        assertThat(description1, equalTo(appointmentbook.getAppointments().get(0).getDescription()));
        assertThat(beginTime1, equalTo(appointmentbook.getAppointments().get(0).getBeginTimeString()));
        assertThat(endTime1, equalTo(appointmentbook.getAppointments().get(0).getEndTimeString()));
        assertThat(description2, equalTo(appointmentbook.getAppointments().get(1).getDescription()));
        assertThat(beginTime2, equalTo(appointmentbook.getAppointments().get(1).getBeginTimeString()));
        assertThat(endTime2, equalTo(appointmentbook.getAppointments().get(1).getEndTimeString()));
    }



    // Incorrect Format of text file
    @Test(expected = CorruptedFile.class)
    public void TestIncorrectFormat() {
        String fileName = "file";
        String description = "eating";
        String owner = "Bob";
        TextDumperTest.DeleteFile(fileName);
        File file = new File(fileName);


        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(fileName);
            fw.write(owner + "\t" + description);
            fw.close();
        } catch (Exception e) {

        }

        TextParser textParser = new TextParser();
        textParser.SetFile(fileName);
        AppointmentBook appointmentbook = textParser.parse();

        assertThat(appointmentbook.getAppointments().size(), equalTo(0));
        assertThat(owner, equalTo(appointmentbook.getOwnerName()));
    }


}

