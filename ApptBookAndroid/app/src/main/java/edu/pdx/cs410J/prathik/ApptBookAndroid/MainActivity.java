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
    private static final int PRINT_README = 2;

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
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void READMe(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, null, this, READMe.class);
        startActivity(intent);
    }
}