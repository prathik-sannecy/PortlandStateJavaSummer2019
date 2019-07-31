package edu.pdx.cs410J.prathik;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AppointmentBookServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class AppointmentBookServletTest {

  @Test
  public void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);
  }

  @Test
  public void addAppointmentToNewAppointmentBook() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "TEST WORD";
    String description = "TEST DEFINITION";
    String beginTime = "01/01/2019 01:00 AM";
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("owner")).thenReturn(owner);
    when(request.getParameter("description")).thenReturn(description);
    when(request.getParameter("beginTime")).thenReturn(beginTime);
    when(request.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);
    verify(response).setStatus(HttpServletResponse.SC_OK);

    AppointmentBook book = servlet.getAppointmentBook(owner);
    assertThat(book, is(notNullValue()));

    assertThat(book.getAppointments(), hasSize(1));
    assertThat(book.getOwnerName(), equalTo(owner));

    Appointment appointment = book.getAppointments().iterator().next();
    assertThat(appointment, is(notNullValue()));
    assertThat(appointment.getDescription(), equalTo(description));
    assertThat(appointment.getBeginTimeString(), equalTo(beginTime));
    assertThat(appointment.getEndTimeString(), equalTo(endTime));

    verify(pw).println(appointment.toString());
  }


  @Test
  public void lookingUpUnknownWordReturnNotFound() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("word")).thenReturn("unknownWord");

    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_NOT_FOUND);

  }

  @Test
  public void addTwoAppointmentsToAppointmentBook() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "TEST WORD";
    String description = "TEST DEFINITION1";
    String beginTime = "01/01/2019 01:00 AM";
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request1 = mock(HttpServletRequest.class);
    when(request1.getParameter("owner")).thenReturn(owner);
    when(request1.getParameter("description")).thenReturn(description);
    when(request1.getParameter("beginTime")).thenReturn(beginTime);
    when(request1.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response1 = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response1.getWriter()).thenReturn(pw);

    servlet.doPost(request1, response1);
    verify(response1).setStatus(HttpServletResponse.SC_OK);




    beginTime = "01/02/2019 01:00 AM";
    endTime = "01/02/2019 02:00 AM";
    description = "TEST DEFINITION2";

    HttpServletRequest request2 = mock(HttpServletRequest.class);
    when(request2.getParameter("owner")).thenReturn(owner);
    when(request2.getParameter("description")).thenReturn(description);
    when(request2.getParameter("beginTime")).thenReturn(beginTime);
    when(request2.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response2 = mock(HttpServletResponse.class);
    when(response2.getWriter()).thenReturn(pw);

    servlet.doPost(request2, response2);
    verify(response2).setStatus(HttpServletResponse.SC_OK);




    AppointmentBook book = servlet.getAppointmentBook(owner);
    assertThat(book, is(notNullValue()));
    assertThat(book.getAppointments(), hasSize(2));
  }

  @Test
  public void searchFindsNoneInAppointmentBook() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "TEST WORD";
    String description = "TEST DEFINITION1";
    String beginTime = "01/01/2019 01:00 AM";
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request1 = mock(HttpServletRequest.class);
    when(request1.getParameter("owner")).thenReturn(owner);
    when(request1.getParameter("description")).thenReturn(description);
    when(request1.getParameter("beginTime")).thenReturn(beginTime);
    when(request1.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response1 = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response1.getWriter()).thenReturn(pw);

    servlet.doPost(request1, response1);
    verify(response1).setStatus(HttpServletResponse.SC_OK);




    beginTime = "01/02/2019 01:00 AM";
    endTime = "01/02/2019 02:00 AM";
    description = "TEST DEFINITION2";

    HttpServletRequest request2 = mock(HttpServletRequest.class);
    when(request2.getParameter("owner")).thenReturn(owner);
    when(request2.getParameter("description")).thenReturn(description);
    when(request2.getParameter("beginTime")).thenReturn(beginTime);
    when(request2.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response2 = mock(HttpServletResponse.class);
    when(response2.getWriter()).thenReturn(pw);

    servlet.doPost(request2, response2);
    verify(response2).setStatus(HttpServletResponse.SC_OK);



    beginTime = "01/06/2019 01:00 AM";
    endTime = "01/07/2019 02:00 AM";


    HttpServletRequest request3 = mock(HttpServletRequest.class);
    when(request3.getParameter("owner")).thenReturn(owner);
    when(request3.getParameter("beginTime")).thenReturn(beginTime);
    when(request3.getParameter("endTime")).thenReturn(endTime);


    String file_name = "get";
    PrintWriter pw3 = new PrintWriter(file_name);

    HttpServletResponse response3 = mock(HttpServletResponse.class);
    when(response3.getWriter()).thenReturn(pw3);

    servlet.doGet(request3, response3);
    verify(response3).setStatus(HttpServletResponse.SC_OK);
    pw3.close();


    File file = new File(file_name);

    Scanner s = new Scanner((file));
    s.useDelimiter("[\\n]");
//    assertThat(s.next(), containsString(beginTime));
//    assertThat(s.next(), containsString(endTime));
    assertThat(s.next(), containsString("0 appointments"));
    assertThat(s.hasNext(), equalTo(false));


  }

  @Test
  public void searchFindsOneInAppointmentBook() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "TEST WORD";
    String description1 = "TEST DEFINITION1";
    String beginTime = "01/01/2019 01:00 AM";
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request1 = mock(HttpServletRequest.class);
    when(request1.getParameter("owner")).thenReturn(owner);
    when(request1.getParameter("description")).thenReturn(description1);
    when(request1.getParameter("beginTime")).thenReturn(beginTime);
    when(request1.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response1 = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response1.getWriter()).thenReturn(pw);

    servlet.doPost(request1, response1);
    verify(response1).setStatus(HttpServletResponse.SC_OK);




    beginTime = "01/02/2019 01:00 AM";
    endTime = "01/02/2019 02:00 AM";
    String description2 = "TEST DEFINITION2";

    HttpServletRequest request2 = mock(HttpServletRequest.class);
    when(request2.getParameter("owner")).thenReturn(owner);
    when(request2.getParameter("description")).thenReturn(description2);
    when(request2.getParameter("beginTime")).thenReturn(beginTime);
    when(request2.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response2 = mock(HttpServletResponse.class);
    when(response2.getWriter()).thenReturn(pw);

    servlet.doPost(request2, response2);
    verify(response2).setStatus(HttpServletResponse.SC_OK);



    String beginTimeRange = "01/02/2019 12:59 AM";
    String endTimeRange = "01/02/2019 02:01 AM";


    HttpServletRequest request3 = mock(HttpServletRequest.class);
    when(request3.getParameter("owner")).thenReturn(owner);
    when(request3.getParameter("beginTime")).thenReturn(beginTimeRange);
    when(request3.getParameter("endTime")).thenReturn(endTimeRange);


    String file_name = "get";
    PrintWriter pw3 = new PrintWriter(file_name);

    HttpServletResponse response3 = mock(HttpServletResponse.class);
    when(response3.getWriter()).thenReturn(pw3);

    servlet.doGet(request3, response3);
    verify(response3).setStatus(HttpServletResponse.SC_OK);
    pw3.close();


    File file = new File(file_name);

    Scanner s = new Scanner((file));
    s.useDelimiter("[\\n]");
//    assertThat(s.next(), containsString(beginTime));
//    assertThat(s.next(), containsString(endTime));
    assertThat(s.next(), containsString("1 appointments"));
    assertThat(s.next(), containsString(description2));
    assertThat(s.hasNext(), equalTo(false));

  }

  @Test
  public void searchFindsTwoInAppointmentBook() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "TEST WORD";
    String description1 = "TEST DEFINITION1";
    String beginTime = "01/01/2019 01:00 AM";
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request1 = mock(HttpServletRequest.class);
    when(request1.getParameter("owner")).thenReturn(owner);
    when(request1.getParameter("description")).thenReturn(description1);
    when(request1.getParameter("beginTime")).thenReturn(beginTime);
    when(request1.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response1 = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response1.getWriter()).thenReturn(pw);

    servlet.doPost(request1, response1);
    verify(response1).setStatus(HttpServletResponse.SC_OK);




    beginTime = "01/02/2019 01:00 AM";
    endTime = "01/02/2019 02:00 AM";
    String description2 = "TEST DEFINITION2";

    HttpServletRequest request2 = mock(HttpServletRequest.class);
    when(request2.getParameter("owner")).thenReturn(owner);
    when(request2.getParameter("description")).thenReturn(description2);
    when(request2.getParameter("beginTime")).thenReturn(beginTime);
    when(request2.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response2 = mock(HttpServletResponse.class);
    when(response2.getWriter()).thenReturn(pw);

    servlet.doPost(request2, response2);
    verify(response2).setStatus(HttpServletResponse.SC_OK);



    String beginTimeRange = "01/01/2019 01:00 AM";
    String endTimeRange = "01/02/2019 02:00 AM";


    HttpServletRequest request3 = mock(HttpServletRequest.class);
    when(request3.getParameter("owner")).thenReturn(owner);
    when(request3.getParameter("beginTime")).thenReturn(beginTimeRange);
    when(request3.getParameter("endTime")).thenReturn(endTimeRange);


    String file_name = "get";
    PrintWriter pw3 = new PrintWriter(file_name);

    HttpServletResponse response3 = mock(HttpServletResponse.class);
    when(response3.getWriter()).thenReturn(pw3);

    servlet.doGet(request3, response3);
    verify(response3).setStatus(HttpServletResponse.SC_OK);
    pw3.close();


    File file = new File(file_name);

    Scanner s = new Scanner((file));
    s.useDelimiter("[\\n]");
//    assertThat(s.next(), containsString(beginTime));
//    assertThat(s.next(), containsString(endTime));
    assertThat(s.next(), containsString("2 appointments"));
    assertThat(s.next(), containsString(description1));
    assertThat(s.next(), containsString(description2));
    assertThat(s.hasNext(), equalTo(false));

  }


  @Test
  public void ownerDoesntExist() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "TEST WORD";
    String description1 = "TEST DEFINITION1";
    String beginTime = "01/01/2019 01:00 AM";
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request1 = mock(HttpServletRequest.class);
    when(request1.getParameter("owner")).thenReturn(owner);
    when(request1.getParameter("description")).thenReturn(description1);
    when(request1.getParameter("beginTime")).thenReturn(beginTime);
    when(request1.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response1 = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response1.getWriter()).thenReturn(pw);

    servlet.doPost(request1, response1);
    verify(response1).setStatus(HttpServletResponse.SC_OK);




    beginTime = "01/02/2019 01:00 AM";
    endTime = "01/02/2019 02:00 AM";
    String description2 = "TEST DEFINITION2";

    HttpServletRequest request2 = mock(HttpServletRequest.class);
    when(request2.getParameter("owner")).thenReturn(owner);
    when(request2.getParameter("description")).thenReturn(description2);
    when(request2.getParameter("beginTime")).thenReturn(beginTime);
    when(request2.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response2 = mock(HttpServletResponse.class);
    when(response2.getWriter()).thenReturn(pw);

    servlet.doPost(request2, response2);
    verify(response2).setStatus(HttpServletResponse.SC_OK);


    owner = "Doesnt exist";
    String beginTimeRange = "01/01/2019 12:59 AM";
    String endTimeRange = "01/02/2019 02:01 AM";


    HttpServletRequest request3 = mock(HttpServletRequest.class);
    when(request3.getParameter("owner")).thenReturn(owner);
    when(request3.getParameter("beginTime")).thenReturn(beginTimeRange);
    when(request3.getParameter("endTime")).thenReturn(endTimeRange);


    String file_name = "get";
    PrintWriter pw3 = new PrintWriter(file_name);

    HttpServletResponse response3 = mock(HttpServletResponse.class);
    when(response3.getWriter()).thenReturn(pw3);

    servlet.doGet(request3, response3);
    verify(response3).setStatus(HttpServletResponse.SC_OK);
    pw3.close();

    File file = new File(file_name);
    Scanner s = new Scanner((file));
    s.useDelimiter("[\\n]");
    assertThat(s.next(), containsString("owner does not exist"));


  }


  @Test
  public void returnAllAppointmentsOfOwner() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();

    String owner = "TEST WORD";
    String description1 = "TEST DEFINITION1";
    String beginTime = "01/01/2019 01:00 AM";
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request1 = mock(HttpServletRequest.class);
    when(request1.getParameter("owner")).thenReturn(owner);
    when(request1.getParameter("description")).thenReturn(description1);
    when(request1.getParameter("beginTime")).thenReturn(beginTime);
    when(request1.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response1 = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response1.getWriter()).thenReturn(pw);

    servlet.doPost(request1, response1);
    verify(response1).setStatus(HttpServletResponse.SC_OK);




    beginTime = "01/02/2019 01:00 AM";
    endTime = "01/02/2019 02:00 AM";
    String description2 = "TEST DEFINITION2";

    HttpServletRequest request2 = mock(HttpServletRequest.class);
    when(request2.getParameter("owner")).thenReturn(owner);
    when(request2.getParameter("description")).thenReturn(description2);
    when(request2.getParameter("beginTime")).thenReturn(beginTime);
    when(request2.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response2 = mock(HttpServletResponse.class);
    when(response2.getWriter()).thenReturn(pw);

    servlet.doPost(request2, response2);
    verify(response2).setStatus(HttpServletResponse.SC_OK);




    HttpServletRequest request3 = mock(HttpServletRequest.class);
    when(request3.getParameter("owner")).thenReturn(owner);


    String file_name = "get";
    PrintWriter pw3 = new PrintWriter(file_name);

    HttpServletResponse response3 = mock(HttpServletResponse.class);
    when(response3.getWriter()).thenReturn(pw3);

    servlet.doGet(request3, response3);
    verify(response3).setStatus(HttpServletResponse.SC_OK);
    pw3.close();

    File file = new File(file_name);

    Scanner s = new Scanner((file));
    s.useDelimiter("[\\n]");
//    assertThat(s.next(), containsString(beginTime));
//    assertThat(s.next(), containsString(endTime));
    assertThat(s.next(), containsString("2 appointments"));
    assertThat(s.next(), containsString(description1));
    assertThat(s.next(), containsString(description2));
    assertThat(s.hasNext(), equalTo(false));

  }

  @Test
  public void supportsMultipleOwners() throws IOException, ServletException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();


    // Post 1 to owner 1
    String owner = "owner1";
    String description1 = "TEST DEFINITION1";
    String beginTime = "01/01/2019 01:00 AM";
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request1 = mock(HttpServletRequest.class);
    when(request1.getParameter("owner")).thenReturn(owner);
    when(request1.getParameter("description")).thenReturn(description1);
    when(request1.getParameter("beginTime")).thenReturn(beginTime);
    when(request1.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response1 = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response1.getWriter()).thenReturn(pw);

    servlet.doPost(request1, response1);
    verify(response1).setStatus(HttpServletResponse.SC_OK);



    // Post 2 to owner 1
    beginTime = "01/02/2019 01:00 AM";
    endTime = "01/02/2019 02:00 AM";
    String description2 = "TEST DEFINITION2";

    HttpServletRequest request2 = mock(HttpServletRequest.class);
    when(request2.getParameter("owner")).thenReturn(owner);
    when(request2.getParameter("description")).thenReturn(description2);
    when(request2.getParameter("beginTime")).thenReturn(beginTime);
    when(request2.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response2 = mock(HttpServletResponse.class);
    when(response2.getWriter()).thenReturn(pw);

    servlet.doPost(request2, response2);
    verify(response2).setStatus(HttpServletResponse.SC_OK);






    // Post 1 to owner 2
    String owner2 = "owner2";
    beginTime = "01/02/2019 01:00 AM";
    endTime = "01/02/2019 02:00 AM";
    String description3 = "TEST DEFINITION3";

    HttpServletRequest request3 = mock(HttpServletRequest.class);
    when(request3.getParameter("owner")).thenReturn(owner2);
    when(request3.getParameter("description")).thenReturn(description3);
    when(request3.getParameter("beginTime")).thenReturn(beginTime);
    when(request3.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response3 = mock(HttpServletResponse.class);
    when(response3.getWriter()).thenReturn(pw);

    servlet.doPost(request3, response3);
    verify(response3).setStatus(HttpServletResponse.SC_OK);





    // Get from owner 1
    HttpServletRequest request4 = mock(HttpServletRequest.class);
    when(request4.getParameter("owner")).thenReturn(owner);
    String file_name = "get";
    PrintWriter pw4 = new PrintWriter(file_name);
    HttpServletResponse response4 = mock(HttpServletResponse.class);
    when(response4.getWriter()).thenReturn(pw4);

    servlet.doGet(request4, response4);
    verify(response4).setStatus(HttpServletResponse.SC_OK);
    pw4.close();

    File file = new File(file_name);

    Scanner s = new Scanner((file));
    s.useDelimiter("[\\n]");
//    assertThat(s.next(), containsString(beginTime));
//    assertThat(s.next(), containsString(endTime));
    assertThat(s.next(), containsString("2 appointments"));
    assertThat(s.next(), containsString(description1));
    assertThat(s.next(), containsString(description2));
    assertThat(s.hasNext(), equalTo(false));
    s.close();



    // Get from owner 2
    HttpServletRequest request5 = mock(HttpServletRequest.class);
    when(request5.getParameter("owner")).thenReturn(owner2);

    file_name = "get";
    PrintWriter pw5 = new PrintWriter(file_name);

    HttpServletResponse response5 = mock(HttpServletResponse.class);
    when(response5.getWriter()).thenReturn(pw5);

    servlet.doGet(request5, response5);
    verify(response5).setStatus(HttpServletResponse.SC_OK);
    pw4.close();

    file = new File(file_name);

    s = new Scanner((file));
    s.useDelimiter("[\\n]");
//    assertThat(s.next(), containsString(beginTime));
//    assertThat(s.next(), containsString(endTime));
    assertThat(s.next(), containsString("1 appointments"));
    assertThat(s.next(), containsString(description3));
    assertThat(s.hasNext(), equalTo(false));

  }

  @Test
  public void badlyFormattedData() throws ServletException, IOException {
    AppointmentBookServlet servlet = new AppointmentBookServlet();




    String owner = "owner1";
    String description1 = "TEST DEFINITION1";
    String badBeginTime = "01/01/219 01:00 AM"; // should throw error
    String endTime = "01/01/2019 02:00 AM";

    HttpServletRequest request1 = mock(HttpServletRequest.class);
    when(request1.getParameter("owner")).thenReturn(owner);
    when(request1.getParameter("description")).thenReturn(description1);
    when(request1.getParameter("beginTime")).thenReturn(badBeginTime);
    when(request1.getParameter("endTime")).thenReturn(endTime);

    HttpServletResponse response1 = mock(HttpServletResponse.class);

    String file_name = "get";
    PrintWriter pw = new PrintWriter(file_name);

    when(response1.getWriter()).thenReturn(pw);

    servlet.doPost(request1, response1);
    pw.close();



    File file = new File(file_name);

    Scanner s = new Scanner((file));
    s.useDelimiter("[\\n]");
//    assertThat(s.next(), containsString(beginTime));
//    assertThat(s.next(), containsString(endTime));
//    verify(response1).setStatus(HttpServletResponse.SC_OK);
//    assertThat(s.next(), containsString("must be in"));





  }


}
