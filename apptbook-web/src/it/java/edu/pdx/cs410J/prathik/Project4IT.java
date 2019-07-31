package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * An integration test for {@link Project4} that invokes its main method with
 * various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    public void test0RemoveAllMappings() throws IOException {
        AppointmentBookRestClient client = new AppointmentBookRestClient(HOSTNAME, Integer.parseInt(PORT));
        client.removeAllAppointmentBooks();
    }

    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project4.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
    }


    @Test
    public void test4AddAppointment() {
        try {
            test0RemoveAllMappings();
        } catch(Exception e) {

        }
        String owner = "TEST WORD";
        String description = "TEST DEFINITION";
        String beginTime = "01/01/2019 01:00 AM";
        String endTime = "01/01/2019 02:00 AM";

        MainMethodResult result = invokeMain( Project4.class, "-print", "-host", HOSTNAME, "-port", PORT, owner, description, "01/01/2019", "01:00", "AM", "01/01/2019", "02:00", "AM" );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(description));
        assertThat(out, out, containsString(beginTime));
        assertThat(out, out, containsString(endTime));
    }

    @Test
    public void getAppointments() {
        try {
            test0RemoveAllMappings();
        } catch(Exception e) {

        }
        String owner = "TEST WORD";
        String description = "getAppointments";
        String beginTime = "01/01/2019 01:00 AM";
        String endTime = "01/01/2019 02:00 AM";

        MainMethodResult result = invokeMain( Project4.class, "-print", "-host", HOSTNAME, "-port", PORT, owner, description, "01/01/2019", "01:00", "AM", "01/01/2019", "02:00", "AM" );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        assertThat(result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(description));
        assertThat(out, out, containsString(beginTime));
        assertThat(out, out, containsString(endTime));

        MainMethodResult result2 = invokeMain( Project4.class, "-search", "-host", HOSTNAME, "-port", PORT, owner, "01/01/2019", "01:00", "AM", "01/01/2019", "02:01", "AM" );
        assertThat(result.getExitCode(), equalTo(0));
        assertThat(result2.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out2 = result2.getTextWrittenToStandardOut();
        assertThat(out2, out2, containsString(description));
        assertThat(out2, out2, containsString(beginTime));
        assertThat(out2, out2, containsString(endTime));



    }

    @Test
    public void getAppointmentsMultipleAppointments() {
        try {
            test0RemoveAllMappings();
        } catch(Exception e) {

        }
        String owner = "TEST WORD1";
        String description = "getAppointmentsMultipleAppointments1";
        String beginTime = "01/01/2019 01:00 AM";
        String endTime = "01/01/2019 02:00 AM";

        MainMethodResult result = invokeMain( Project4.class, "-print", "-host", HOSTNAME, "-port", PORT, owner, description, "01/01/2019", "01:00", "AM", "01/01/2019", "02:00", "AM" );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        assertThat(result.getExitCode(), equalTo(0));

        String description2 = "getAppointmentsMultipleAppointments2";
        String beginTime2 = "01/01/2018 11:00 AM";
        String endTime2 = "01/01/2018 02:00 PM";

        MainMethodResult result2 = invokeMain( Project4.class, "-print", "-host", HOSTNAME, "-port", PORT, owner, description2, "01/01/2018", "11:00", "AM", "01/01/2018", "02:00", "PM" );
        assertThat(result2.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        assertThat(result2.getExitCode(), equalTo(0));

        MainMethodResult result3 = invokeMain( Project4.class, "-search", "-host", HOSTNAME, "-port", PORT, owner, "01/01/2018", "01:00", "AM", "01/01/2019", "02:01", "PM" );
        assertThat(result3.getExitCode(), equalTo(0));
        assertThat(result3.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out3 = result3.getTextWrittenToStandardOut();
        assertThat(out3, out3, containsString(description));
        assertThat(out3, out3, containsString(beginTime));
        assertThat(out3, out3, containsString(endTime));
        assertThat(out3, out3, containsString(description2));
        assertThat(out3, out3, containsString(beginTime2));
        assertThat(out3, out3, containsString(endTime2));



    }

    @Test
    public void getAppointmentsMultipleOwners() {
        try {
            test0RemoveAllMappings();
        } catch(Exception e) {

        }
        String owner = "TEST WORD1";
        String description = "getAppointmentsMultipleOwners1";
        String beginTime = "01/01/2019 01:00 AM";
        String endTime = "01/01/2019 02:00 AM";

        MainMethodResult result = invokeMain( Project4.class, "-print", "-host", HOSTNAME, "-port", PORT, owner, description, "01/01/2019", "01:00", "AM", "01/01/2019", "02:00", "AM" );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        assertThat(result.getExitCode(), equalTo(0));

        String owner2 = "TEST WORD2";
        String description2 = "getAppointmentsMultipleOwners2";
        String beginTime2 = "01/01/2018 11:00 AM";
        String endTime2 = "01/01/2018 02:00 PM";

        MainMethodResult result2 = invokeMain( Project4.class, "-print", "-host", HOSTNAME, "-port", PORT, owner2, description2, "01/01/2018", "11:00", "AM", "01/01/2018", "02:00", "PM" );
        assertThat(result2.getTextWrittenToStandardError(), result2.getExitCode(), equalTo(0));
        assertThat(result2.getExitCode(), equalTo(0));

        MainMethodResult result3 = invokeMain( Project4.class, "-search", "-host", HOSTNAME, "-port", PORT, owner, "01/01/2018", "01:00", "AM", "01/01/2019", "02:01", "PM" );
        assertThat(result3.getExitCode(), equalTo(0));
        assertThat(result3.getTextWrittenToStandardError(), result3.getExitCode(), equalTo(0));
        String out3 = result3.getTextWrittenToStandardOut();
        assertThat(out3, out3, containsString(description));
        assertThat(out3, out3, containsString(beginTime));
        assertThat(out3, out3, containsString(endTime));

        MainMethodResult result4 = invokeMain( Project4.class, "-search", "-host", HOSTNAME, "-port", PORT, owner2, "01/01/2018", "01:00", "AM", "01/01/2019", "02:01", "PM" );
        assertThat(result4.getExitCode(), equalTo(0));
        assertThat(result4.getTextWrittenToStandardError(), result4.getExitCode(), equalTo(0));
        String out4 = result4.getTextWrittenToStandardOut();
        assertThat(out4, out4, containsString(description2 ));
        assertThat(out4, out4, containsString(beginTime2));
        assertThat(out4, out4, containsString(endTime2));
    }

    @Test
    public void hostWithoutPort() {
        MainMethodResult result = invokeMain( Project4.class, "-search", "-host", HOSTNAME,  "Bob", "01/01/2018", "01:00", "AM", "01/01/2019", "02:01", "PM" );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Host name needs a port number"));
    }


}