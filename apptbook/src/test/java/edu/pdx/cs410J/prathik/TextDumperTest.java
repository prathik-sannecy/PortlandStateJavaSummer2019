package edu.pdx.cs410J.prathik;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link TextDumper} class.
 */
public class TextDumperTest {
    private void DeleteFile(String fileName){
        File f = new File(fileName);
        if(f.isFile()){
            f.delete();
        }
    }


    @Test
    public void TestCreatingTextDumperClass() {
        TextDumper textDumper = new TextDumper();
    }

    @Test
    public void TestSettingFileInTextDumperClass() {
        String fileName = "file";
        TextDumper textDumper = new TextDumper();
        textDumper.SetFile(fileName);
        assertThat(textDumper.GetFile(), is(fileName));
    }

    // Can't set empty String
    @Test(expected = InvalidFileName.class)
    public void TestSettingNullFileName() {
        String fileName = "";
        TextDumper textDumper = new TextDumper();
        textDumper.SetFile(fileName);
    }

    // File gets created if it doesn't exist
    @Test
    public void TestCreatingFile() {
        String fileName = "file";
        DeleteFile(fileName);
        TextDumper textDumper = new TextDumper();
        textDumper.SetFile(fileName);
        textDumper.dump(new AppointmentBook());
        assertThat(new File(fileName).isFile(), equalTo(true));
    }

    // Trying to dump into text file without setting file name first
    @Test(expected = InvalidFileName.class)
    public void TestDumpingIntoInvalidTextFile() {
        String fileName = "file";
        DeleteFile(fileName);
        TextDumper textDumper = new TextDumper();
        textDumper.dump(new AppointmentBook());
    }

    // Adding 1 entry to the dump text file
    @Test
    public void TestDumpingSingleAppointment() {
        String fileName = "file";
        AppointmentBook appointmentbook = new AppointmentBook();
        Appointment appointment = new Appointment();
        String owner = "Bob";
        String description = "eating burger";
        String beginTime = "03/17/1996 03:43";
        String endTime = "03/17/1997 03:44";

        TextDumper textDumper = new TextDumper();
        textDumper.SetFile(fileName);


        appointmentbook.setOwnerName(owner);
        appointment.setDescription(description);
        appointment.setBeginTimeString(beginTime);
        appointment.setEndTimeString(endTime);
        appointmentbook.addAppointment(appointment);

        textDumper.dump(appointmentbook);

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

    // Adding 2 entry to the dump text file
    @Test
    public void TestDumpingMultipleAppointment() {
        String fileName = "file";
        AppointmentBook appointmentbook = new AppointmentBook();
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        String owner = "Bob";
        String description1 = "eating burger";
        String beginTime1 = "03/17/1996 03:43";
        String endTime1 = "03/17/1997 03:44";

        String description2 = "taking nap";
        String beginTime2 = "01/6/1946 23:43";
        String endTime2 = "05/13/4447 03:48";

        TextDumper textDumper = new TextDumper();
        textDumper.SetFile(fileName);


        appointmentbook.setOwnerName(owner);
        appointment1.setDescription(description1);
        appointment1.setBeginTimeString(beginTime1);
        appointment1.setEndTimeString(endTime1);
        appointmentbook.addAppointment(appointment1);
        appointment2.setDescription(description2);
        appointment2.setBeginTimeString(beginTime2);
        appointment2.setEndTimeString(endTime2);
        appointmentbook.addAppointment(appointment2);

        textDumper.dump(appointmentbook);

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

    // Dumping to existing overwrites
    @Test
    public void TestOverWrittingWithNewAppointment() {
        this.TestDumpingMultipleAppointment();
        this.TestDumpingSingleAppointment();

    }


    /*
    // Test File Delete Method
    @Test
    public void TestDeleteFile() {
        String fileName = "file";
        assertThat(new File(fileName).isFile(), equalTo(true));
        DeleteFile(fileName);
        assertThat(new File(fileName).isFile(), equalTo(false));
    }
    */



}
