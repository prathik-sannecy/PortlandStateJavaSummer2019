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

public class CreateAppointment extends AppCompatActivity {
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
        setContentView(R.layout.activity_create_appointment);
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

    public void createAppointment(View view) {
        EditText owner = findViewById(R.id.ownerName);
        String ownerAsString = owner.getText().toString();
        EditText description = findViewById(R.id.description);
        String descriptionAsString = description.getText().toString();

        String begin = this.beginMonth + "/" + this.beginDay + "/" + this.beginYear + " " + this.beginHour + ":" + this.beginMin + " " + this.beginAm_pm;
        String end = this.endMonth + "/" + this.endDay + "/" + this.endYear + " " + this.endHour + ":" + this.endMin + " " + this.endAm_pm;


        Appointment appointment;

        try{
            appointment = new Appointment(descriptionAsString, begin, end);



            textFile(this, ownerAsString + ".txt", ownerAsString, appointment);

            Intent result = new Intent("Added Appointment", null);
            setResult(RESULT_OK, result);

            this.finish();

        } catch (WrongDateTimeFormat w){
            toast(w.getMessage());
        } catch (UnsupportedOperationException u){
            toast(u.getMessage());
        } catch (CorruptedFile c){
            toast(c.getMessage());
        }


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

    /**
     * Loads a pre-existing (if it exists) <code>Appointmentbook</code> from <code>textFile</code>,
     * adds the new <code>Appointment</code> to it, and saves it back.
     *
     * Returns back the new appointment book with all the appointments
     *
     * @param textFile The file to load the appointmentbook, where to resave it
     * @param owner The owner of the appointmentbook
     * @param appointment The new appointment to add to the appointmentbook
     */
    private  AppointmentBook textFile(Context context, String textFile, String owner, Appointment appointment){
        AppointmentBook appointmentbook;

        TextParser textParser = new TextParser(textFile);
        appointmentbook = textParser.parse(context);

        // No owner means that the appointbook file was nonexisting - must create new one
        if(appointmentbook == null) {
            appointmentbook = new AppointmentBook(owner);
        }
        else if(!appointmentbook.getOwnerName().equals(owner)){
            System.err.println("Owner mismatch between new appointment and existing appointment book!");
            System.exit(1);
        }
        appointmentbook.addAppointment(appointment);

        TextDumper textDumper = new TextDumper(textFile);
        textDumper.dump(context, appointmentbook);

        return appointmentbook;
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
