package edu.pdx.cs410J.prathik.ApptBookAndroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private static final int DOUBLE_NUMBER = 1;
    private static final int GET_TIME = 2;
    private static final int GET_DATE = 3;

    private int count;
    private String messageToDisplayAfterResume;
    private Integer doubled = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_appt_book_main_menu);


    }


    private void setMessage(CharSequence message) {
        TextView text = findViewById(R.id.text);
        text.setText(message);
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

    public void displayCurrentTimeInEditText(View view) {
        TextClock clock = (TextClock) view;
        setMessage(clock.getText());
    }

    public void doubleNumber(View view) {
        EditText numberToDouble = findViewById(R.id.number);
        String numberAsString = numberToDouble.getText().toString();
        try {
            int number = Integer.parseInt(numberAsString);
            Uri uri = Uri.fromParts("number", String.valueOf(number), null);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri, this, DoubleNumberActivity.class);
            intent.putExtra("number", number);
            startActivityForResult(intent, DOUBLE_NUMBER);

        } catch (NumberFormatException ex) {
            toast("Bad number: " + numberAsString);
        }
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == GET_TIME) {
            if (resultCode == RESULT_OK) {
                String hour;
                String min;
                String am_pm;
                hour = data.getStringExtra("hour");
                min = data.getStringExtra("min");
                am_pm = data.getStringExtra("am_pm");
                System.out.println(hour);
                System.out.println(min);
                System.out.println(am_pm);
                // Changing the state of the widgets here will have no effect because the
                // activity is "paused"
            }
        }
        else if (requestCode == GET_DATE) {
            if (resultCode == RESULT_OK) {
                String day;
                String month;
                String year;
                day = data.getStringExtra("day");
                month = data.getStringExtra("month");
                year = data.getStringExtra("year");
                System.out.println(day);
                System.out.println(month);
                System.out.println(year);
                // Changing the state of the widgets here will have no effect because the
                // activity is "paused"
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void READMe(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, READMe.class);
        startActivity(intent);
    }

    public void GetTime(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, GetTime.class);
        startActivityForResult(intent, GET_TIME);
    }

    public void GetDate(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, GetDate.class);
        startActivityForResult(intent, GET_DATE);
    }

    public void errorDialog(View view) {
        toast("Error Message");
    }
}