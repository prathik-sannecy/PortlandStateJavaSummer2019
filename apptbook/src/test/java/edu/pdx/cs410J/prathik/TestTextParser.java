package edu.pdx.cs410J.prathik;

import org.junit.Test;

import java.io.File;
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
    public void TestParseReturnsAppointmentBook() {
        TextParser textParser = new TextParser();
        AppointmentBook appointmentBook = textParser.parse();
    }


    // Can't set empty String
    @Test(expected = InvalidFileName.class)
    public void TestSettingNullFileName() {
        String fileName = "";
        TextDumper textDumper = new TextDumper();
        textDumper.SetFile(fileName);
    }

//    @Test(expected = InvalidFileName.class)
//    public void TestSettingNonexistingFileName() {
//        TextParser textParser = new TextParser();
//        tex
//        AppointmentBook appointmentBook = textParser.parse();
//    }


}

