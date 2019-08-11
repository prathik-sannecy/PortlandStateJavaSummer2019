package edu.pdx.cs410J.prathik.ApptBookAndroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class READMe extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readme);


        TextView displayText = findViewById(R.id.READMe);
        //displayText.setText(String.format("README"));

        displayText.setText("This program implements an appointment book. \n\n" +
                "The user provides the details of appointments, and this program can add them to the corresponding owner's appointment book.\n" +
                "The user can search an appointment book for appointments within a date/time range, or simply retrieve all appointments of an appointment book");
    }


    public void goBackHelp(View view) {
        this.finish();
    }

}
