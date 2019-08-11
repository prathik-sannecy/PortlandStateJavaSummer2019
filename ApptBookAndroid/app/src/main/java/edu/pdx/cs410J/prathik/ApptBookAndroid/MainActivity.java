package edu.pdx.cs410J.prathik.ApptBookAndroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CREATE = 1;
    private static final int SEARCH = 2;
    private static final int ALL_APPOINTMENTS = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appt_book_main_menu);


    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }



    @Override
    protected void onResume() {
        super.onResume();


    }


    public void READMe(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, READMe.class);
        startActivity(intent);
    }

    public void createNewAppointment(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, CreateAppointment.class);
        startActivityForResult(intent, CREATE);
    }

    public void searchAppointments(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, SearchAppointments.class);
        startActivityForResult(intent, SEARCH);
    }

    public void allAppointments(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, AllAppointments.class);
        startActivityForResult(intent, ALL_APPOINTMENTS);
    }
}