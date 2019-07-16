package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;

/**
 * Integration tests for the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project1} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain( Project1.class, args );
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



}