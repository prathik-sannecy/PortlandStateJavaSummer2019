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

public class AllAppointments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_appointments);
    }


    public void goBackHelp(View view) {
        this.finish();
    }




    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void getAppointments(View view) {

        EditText owner = findViewById(R.id.ownerName);
        String ownerAsString = owner.getText().toString();



        String textFile = ownerAsString + ".txt";

        File f = new File(this.getApplicationContext().getFilesDir(), textFile);

        if (f.isFile()) {
            try {
                TextParser textParser = new TextParser(textFile);

                AppointmentBook appointmentBook = textParser.parse(this);


                TextView ownerAppointmentsText = findViewById(R.id.ownerAppointments);
                ownerAppointmentsText.setText(appointmentBook.toString());


                TextView appointmentsText = findViewById(R.id.ownerAppointmentBook);
                PrettyPrinter prettyPrinter = new PrettyPrinter(appointmentsText);
                prettyPrinter.dump(appointmentBook);

            }catch (WrongDateTimeFormat w){
                toast(w.getMessage());
            } catch (UnsupportedOperationException u){
                toast(u.getMessage());
            } catch (CorruptedFile c){
                toast(c.getMessage());
            }



        } else {
            toast("owner does not exist!");
        }

    }
}
