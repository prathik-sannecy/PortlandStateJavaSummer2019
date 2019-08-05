package edu.pdx.cs410J.prathik.ApptBookAndroid;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client
 */
public class AppointmentBookRestClient extends HttpRequestHelper {
    private static final String WEB_APP = "apptbook";
    private static final String SERVLET = "appointments";

    private final String url;


    /**
     * Creates a client to the appointment book REST service running on the given host and port
     *
     * @param hostName The name of the host
     * @param port     The port
     */
    public AppointmentBookRestClient(String hostName, int port) {
        this.url = String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET);
    }

    /**
     * Returns appointments between the begin / end time for the owner
     *
     * @param beginTime The begin time
     * @param endTime     The end time
     * @param owner     The owner
     */
    public String getAppointments(String owner, String beginTime, String endTime) throws IOException {
        Response response = get(this.url,
                Map.of(
                        "owner", owner,
                        "beginTime", beginTime,
                        "endTime", endTime));
        throwExceptionIfNotOkayHttpStatus(response);
        return response.getContent();
    }

    /**
     * Adds an appointment to an owner's appointment book
     *
     * @param owner The appointment book's owner
     * @param description     The appointment's description
     * @param endTime     The appointment's end time
     * @param beginTime     The appointment's begin time
     */
    public String addAppointment(String owner, String description, String beginTime, String endTime) throws IOException {
        Map<String, String> params =
                Map.of(
                        "owner", owner,
                        "description", description,
                        "beginTime", beginTime,
                        "endTime", endTime
                );
        Response response = postToMyURL(params);
        throwExceptionIfNotOkayHttpStatus(response);
        return response.getContent();
    }

    /**
     * Posts an appointment to a URL over HTTP
     */
    @VisibleForTesting
    Response postToMyURL(Map<String, String> appointmentEntries) throws IOException {
        return post(this.url, appointmentEntries);
    }

    /**
     * Clears the site, removes all appointment books
     */
    public void removeAllAppointmentBooks() throws IOException {
        Response response = delete(this.url, Map.of());
        throwExceptionIfNotOkayHttpStatus(response);
    }

    /**
     * Makes sure that the response from an HTTP request is ok
     *
     * @param response     The response from the server after an HTTP request
     */
    private Response throwExceptionIfNotOkayHttpStatus(Response response) {
        int code = response.getCode();
        String message = response.getContent();
        if (code != HTTP_OK) {
            throw new AppointmentBookRestException(code, message);
        }
        return response;
    }

    /**
     * Throws an exception for an invalid HTTP response
     */
    @VisibleForTesting
    class AppointmentBookRestException extends RuntimeException {
        private AppointmentBookRestException(int httpStatusCode, String message) {
            super("Got an HTTP Status Code of " + httpStatusCode + ": " + message);
        }
    }
}