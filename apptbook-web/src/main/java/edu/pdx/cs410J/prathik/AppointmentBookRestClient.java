package edu.pdx.cs410J.prathik;

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
   * Returns all dictionary entries from the server
   */
  public Map<String, String> getAllDictionaryEntries() throws IOException {
    Response response = get(this.url, Map.of());
    return Messages.parseDictionary(response.getContent());
  }

  /**
   * Returns the definition for the given word
   */
  public String getDefinition(String word) throws IOException {
    Response response = get(this.url, Map.of("word", word));
    throwExceptionIfNotOkayHttpStatus(response);
    String content = response.getContent();
    return Messages.parseDictionaryEntry(content).getValue();
  }

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

  @VisibleForTesting
  Response postToMyURL(Map<String, String> dictionaryEntries) throws IOException {
    return post(this.url, dictionaryEntries);
  }

  public void removeAllAppointmentBooks() throws IOException {
    Response response = delete(this.url, Map.of());
    throwExceptionIfNotOkayHttpStatus(response);
  }

  private Response throwExceptionIfNotOkayHttpStatus(Response response) {
    int code = response.getCode();
    String message = response.getContent();
    if (code != HTTP_OK) {
      throw new AppointmentBookRestException(code, message);
    }
    return response;
  }

  @VisibleForTesting
  class AppointmentBookRestException extends RuntimeException {
    private AppointmentBookRestException(int httpStatusCode, String message) {
      super("Got an HTTP Status Code of " + httpStatusCode + ": " + message);
    }
  }
}