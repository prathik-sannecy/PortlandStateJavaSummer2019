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

        displayText.setText("This program implements an appointment book. \n" +
                "The user provides the details of an appointment, and this program can add it to the corresponding appointment book on the server.\n" +
                "Also allows to search an appointment book for appointments within a date/time range");
    }


    public void goBackHelp(View view) {
        this.finish();
    }

}
