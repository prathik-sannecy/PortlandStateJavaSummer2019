package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment extends AbstractAppointment {
  private String BeginTimeString = null;
  @Override
  public String getBeginTimeString() {
    if(BeginTimeString != null)
      return BeginTimeString;
    throw new UnsupportedOperationException("Please Set date / time for appointment");
  }

  @Override
  public String getEndTimeString() {
    throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDescription() {
    return "This method is not implemented yet";
  }

  public void setBeginTimeString(String beginTime) {
    boolean validDateTimeFormat = false;
    if (checkDateFormat(beginTime, "mm/dd/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(beginTime, "m/dd/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(beginTime, "mm/d/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(beginTime, "m/d/yyyy HH:mm")) validDateTimeFormat = true;

    if(!validDateTimeFormat)
      throw new WrongDateTimeFormat("Does not follow date / time format!");

    BeginTimeString = beginTime;
  }

  private Boolean checkDateFormat(String date, String format){
    try {
      SimpleDateFormat formatter = new SimpleDateFormat(format);
      formatter.setLenient(false);
      Date parsedDate = formatter.parse(date);

    }catch (ParseException e){
      return false;
    }

    return true;
  }

}
