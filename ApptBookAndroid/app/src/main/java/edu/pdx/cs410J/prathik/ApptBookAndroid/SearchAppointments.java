package edu.pdx.cs410J.prathik.ApptBookAndroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class SearchAppointments extends AppCompatActivity {
    private static final int GET_BEGIN_TIME = 1;
    private static final int GET_END_TIME = 2;
    private static final int GET_BEGIN_DATE = 3;
    private static final int GET_END_DATE = 4;
    private String beginHour;
    private String beginMin;
    private String beginAm_pm;
    private String beginYear;
    private String beginDay;
    private String beginMonth;
    private String endHour;
    private String endMin;
    private String endAm_pm;
    private String endDay;
    private String endMonth;
    private String endYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_appointments);
    }


    public void goBackHelp(View view) {
        this.finish();
    }

    public void getBeginTime(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, GetTime.class);
        startActivityForResult(intent, GET_BEGIN_TIME);
    }

    public void getEndTime(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, GetTime.class);
        startActivityForResult(intent, GET_END_TIME);

    }


    public void getBeginDate(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, GetDate.class);
        startActivityForResult(intent, GET_BEGIN_DATE);

    }

    public void getEndDate(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, GetDate.class);
        startActivityForResult(intent, GET_END_DATE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GET_BEGIN_TIME) {
            if (resultCode == RESULT_OK) {
                try {
                    this.beginHour = data.getStringExtra("hour");
                    this.beginMin = data.getStringExtra("min");
                    this.beginAm_pm = data.getStringExtra("am_pm");
                } catch (NullPointerException e) {

                }
            }
        } else if (requestCode == GET_BEGIN_DATE) {
            if (resultCode == RESULT_OK) {
                try {
                    this.beginDay = data.getStringExtra("day");
                    this.beginMonth = data.getStringExtra("month");
                    this.beginYear = data.getStringExtra("year");
                } catch (NullPointerException e) {

                }
            }
        } else if (requestCode == GET_END_TIME) {
            if (resultCode == RESULT_OK) {
                try {
                    this.endHour = data.getStringExtra("hour");
                    this.endMin = data.getStringExtra("min");
                    this.endAm_pm = data.getStringExtra("am_pm");
                } catch (NullPointerException e) {

                }
            }
        } else if (requestCode == GET_END_DATE) {
            if (resultCode == RESULT_OK) {
                try {
                    this.endDay = data.getStringExtra("day");
                    this.endMonth = data.getStringExtra("month");
                    this.endYear = data.getStringExtra("year");
                } catch (NullPointerException e) {

                }
            }
        }
    }


    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void searchAppointments(View view) {

        EditText owner = findViewById(R.id.ownerName);
        String ownerAsString = owner.getText().toString();

        String begin = this.beginMonth + "/" + this.beginDay + "/" + this.beginYear + " " + this.beginHour + ":" + this.beginMin + " " + this.beginAm_pm;
        String end = this.endMonth + "/" + this.endDay + "/" + this.endYear + " " + this.endHour + ":" + this.endMin + " " + this.endAm_pm;


        String textFile = ownerAsString + ".txt";

        File f = new File(this.getApplicationContext().getFilesDir(), textFile);

        if (f.isFile()) {
            try {
                TextParser textParser = new TextParser(textFile);
                Appointment appt = new Appointment("Dummy", begin, end);

                AppointmentBook appointmentBook = textParser.parse(this);

                AppointmentBook shortenedAppointmentBook = new AppointmentBook(ownerAsString);


                for (Appointment appointment : appointmentBook.getAppointments()) {
                    if (appointment.getBeginTime().compareTo(appt.getBeginTime()) >= 0 && appt.getEndTime().compareTo(appointment.getEndTime()) >= 0) {
                        shortenedAppointmentBook.addAppointment(appointment);
                    }
                }

                TextView ownerAppointmentsText = findViewById(R.id.ownerAppointments);
                ownerAppointmentsText.setText(shortenedAppointmentBook.toString());


                TextView appointmentsText = findViewById(R.id.ownerAppointmentBook);
                PrettyPrinter prettyPrinter = new PrettyPrinter(appointmentsText);
                prettyPrinter.dump(shortenedAppointmentBook);

            } catch (WrongDateTimeFormat w) {
                toast(w.getMessage());
            } catch (UnsupportedOperationException u) {
                toast(u.getMessage());
            } catch (CorruptedFile c) {
                toast(c.getMessage());
            }


        } else {
            toast("owner does not exist!");
        }

    }
}
