package edu.pdx.cs410J.prathik;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment extends AbstractAppointment {
  private String BeginTimeString = null;
  private String EndTimeString = null;
  private String Description = null;

  public Appointment(){
    super();
  }

  @Override
  public String getEndTimeString() {
    return this.EndTimeString;

  }

  @Override
  public String getBeginTimeString() {
    return this.BeginTimeString;
  }

  @Override
  public String getDescription() {
    return this.Description;
  }

  public void setBeginTimeString(String beginTime) {
    boolean validDateTimeFormat = false;
    if (checkDateFormat(beginTime, "mm/dd/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(beginTime, "m/dd/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(beginTime, "mm/d/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(beginTime, "m/d/yyyy HH:mm")) validDateTimeFormat = true;

    // Make sure year is 4 digits
    try {
      String year = beginTime.split(" ")[0].split("/")[2];
      if (year.length() != 4) validDateTimeFormat = false;
    }catch (ArrayIndexOutOfBoundsException e) {
      validDateTimeFormat = false;
    }

    if(!validDateTimeFormat)
      throw new WrongDateTimeFormat("Please enter begin time date in correct format of mm/dd/yyyy hh:mm!");

    this.BeginTimeString = beginTime;
  }

  public void setEndTimeString(String endTime) {
    boolean validDateTimeFormat = false;
    if (checkDateFormat(endTime, "mm/dd/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(endTime, "m/dd/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(endTime, "mm/d/yyyy HH:mm")) validDateTimeFormat = true;
    if (checkDateFormat(endTime, "m/d/yyyy HH:mm")) validDateTimeFormat = true;

    // Make sure year is 4 digits
    try {
      String year = endTime.split(" ")[0].split("/")[2];
      if (year.length() != 4) validDateTimeFormat = false;
    }catch (ArrayIndexOutOfBoundsException e) {
      validDateTimeFormat = false;
    }


    if(!validDateTimeFormat)
      throw new WrongDateTimeFormat("Please enter end time date in correct format of mm/dd/yyyy hh:mm");


    this.EndTimeString = endTime;
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

  public void setDescription(String description) {
    if ((description == null) || (description == ""))
      throw new WrongDescription("Please enter a description");

    this.Description = description;
  }

  private Boolean checkDateOrder(String date1, String date2, String format){
    return _checkDateOrder(date1, date2, "mm/dd/yyyy HH:mm");
  }


  private Boolean _checkDateOrder(String date1, String date2, String format){
    try {
      SimpleDateFormat formatter = new SimpleDateFormat(format);
      Date parsedDate1 = formatter.parse(date1);
      Date parsedDate2 = formatter.parse(date2);
      if(parsedDate2.after(parsedDate1))
        return true;

      return false;
    }catch (ParseException e){
      throw new UnsupportedOperationException("Please enter dates in correct format of mm/dd/yyyy hh:mm");
    }
  }
}
