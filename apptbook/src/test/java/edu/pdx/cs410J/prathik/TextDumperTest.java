package edu.pdx.cs410J.prathik;

import org.junit.Test;

import java.io.File;

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
