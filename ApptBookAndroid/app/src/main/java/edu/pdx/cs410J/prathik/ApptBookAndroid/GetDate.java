package edu.pdx.cs410J.prathik.ApptBookAndroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class GetDate extends AppCompatActivity {

    int day;
    int month;
    int year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_date);



    }


    public void cancel(View view) {
        this.finish();
    }

    public void setDate(View view) {
        DatePicker datePicker=(DatePicker) findViewById(R.id.datePicker);

        this.day = datePicker.getDayOfMonth();
        this.month = datePicker.getMonth();
        this.year = datePicker.getYear();



        Intent result = new Intent("Get Date", null);
        result.putExtra("day", Integer.toString(this.day));
        result.putExtra("month", Integer.toString(this.month + 1)); // Add offset 1 (1 = January)
        result.putExtra("year", Integer.toString(this.year));
        setResult(RESULT_OK, result);


        this.finish();
    }
}
