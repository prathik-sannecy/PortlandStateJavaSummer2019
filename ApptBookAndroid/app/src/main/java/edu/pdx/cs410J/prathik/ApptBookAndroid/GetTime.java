package edu.pdx.cs410J.prathik.ApptBookAndroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class GetTime extends AppCompatActivity {

    String hour;
    String min;
    String am_pm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_time);



    }


    public void cancel(View view) {
        this.finish();
    }

    public void setTime(View view) {
        TimePicker timePicker=(TimePicker)findViewById(R.id.timePicker);
        int hour;
        int min;

        hour = timePicker.getCurrentHour();
        min = timePicker.getCurrentMinute();

        if (hour > 12) {
            hour -= 12;
            this.am_pm = "PM";
        } else if (hour == 0) {
            hour += 12;
            this.am_pm = "AM";
        } else if (hour == 12){
            this.am_pm = "PM";
        }else{
            this.am_pm = "AM";
        }

        this.hour = Integer.toString(hour);
        this.min = Integer.toString(min);


        //Uri uri = Uri.fromParts("time", String.valueOf(this.am_pm), null);
        Intent result = new Intent("Get Time", null);
        result.putExtra("hour", this.hour);
        result.putExtra("min", this.min);
        result.putExtra("am_pm", this.am_pm);
        setResult(RESULT_OK, result);


        this.finish();
    }
}
