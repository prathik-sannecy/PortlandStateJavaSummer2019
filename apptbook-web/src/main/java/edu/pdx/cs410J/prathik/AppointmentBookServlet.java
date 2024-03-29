package edu.pdx.cs410J.prathik;

import com.google.common.annotations.VisibleForTesting;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>AppointmentBook</code>.
 */
public class AppointmentBookServlet extends HttpServlet {
    static final String OWNER_PARAMETER = "owner";
    static final String DESCRIPTION_PARAMETER = "description";
    static final String BEGIN_TIME_PARAMETER = "beginTime";
    static final String END_TIME_PARAMETER = "endTime";

    private final Map<String, AppointmentBook> appointmentBooks = new HashMap<>();

    /**
     * Handles an HTTP GET request from a client by returning the contents of the owner's appointment book.
     * If the begin time and end time are specified, then only return appointments within that range
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        // owner is required, begin time and end time are not
        String parameter = OWNER_PARAMETER;
        String owner = getRequiredParameter(request, response, parameter);
        if (owner == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        String beginTime = getParameter(BEGIN_TIME_PARAMETER, request);
        String endTime = getParameter(END_TIME_PARAMETER, request);

        PrettyPrinter prettyPrinter = new PrettyPrinter("-", response.getWriter());

        if (this.appointmentBooks.containsKey(owner)) {
            if (beginTime == null && endTime == null) {
                prettyPrinter.dump(this.appointmentBooks.get(owner));
            } else {
                try {
                    Appointment appt = new Appointment("Dummy", beginTime, endTime);

                    AppointmentBook shortenedAppointmentBook = new AppointmentBook(owner);
                    for (Appointment appointment : this.appointmentBooks.get(owner).getAppointments()) {
                        if (appointment.getBeginTime().compareTo(appt.getBeginTime()) >= 0 && appt.getEndTime().compareTo(appointment.getEndTime()) >= 0) {
                            shortenedAppointmentBook.addAppointment(appointment);
                        }
                    }
                    prettyPrinter.dump(shortenedAppointmentBook);
                } catch (WrongDateTimeFormat e) {
                    response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "begin time and end time must be in mm/dd/yyyy hh:mm format");
                    return;
                }
            }
        } else {
            response.getWriter().println("owner does not exist!");
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Handles an HTTP POST request by storing the appointment into the appropriate owner's appointment book
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        String parameter = OWNER_PARAMETER;
        String owner = getRequiredParameter(request, response, parameter);
        if (owner == null) return;

        String description = getRequiredParameter(request, response, DESCRIPTION_PARAMETER);
        if (description == null) return;

        String beginTime = getRequiredParameter(request, response, BEGIN_TIME_PARAMETER);
        if (beginTime == null) return;

        String endTime = getRequiredParameter(request, response, END_TIME_PARAMETER);
        if (endTime == null) return;

        // If owner exist, put appointment into existing appointment book. Else, create a new appointment book
        AppointmentBook book;
        if (!this.appointmentBooks.containsKey(owner)) {
            book = new AppointmentBook(owner);
            this.appointmentBooks.put(owner, book);
        } else {
            book = this.appointmentBooks.get(owner);
        }

        try {
            Appointment appt = new Appointment(description, beginTime, endTime);
            book.addAppointment(appt);

            response.getWriter().println(appt.toString());
        } catch (WrongDateTimeFormat e) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, "begin time and end time must be in mm/dd/yyyy hh:mm format");
            return;
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Throws an error if a parameter is missing
     */
    private String getRequiredParameter(HttpServletRequest request, HttpServletResponse response, String parameter) throws IOException {
        String value = getParameter(parameter, request);
        if (value == null) {
            missingRequiredParameter(response, parameter);
            return null;
        }
        return value;
    }

    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        this.appointmentBooks.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    }

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     * <p>
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter(HttpServletResponse response, String parameterName)
            throws IOException {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     * <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
        String value = request.getParameter(name);
        if (value == null || "".equals(value)) {
            return null;

        } else {
            return value;
        }
    }

    @VisibleForTesting
    AppointmentBook getAppointmentBook(String owner) {
        return this.appointmentBooks.get(owner);
    }
}
