package edu.pdx.cs410J.prathik.ApptBookAndroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GetTime extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);



    }


    public void cancel(View view) {
        this.finish();
    }

    public void setTime(View view) {

        this.finish();
    }
}
