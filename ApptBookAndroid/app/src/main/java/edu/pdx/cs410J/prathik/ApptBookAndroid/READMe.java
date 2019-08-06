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
        displayText.setText(String.format("README"));
    }


    public void goBackHelp(View view) {
//        Uri uri = Uri.fromParts("number", "number", null);
//        Intent result = new Intent("Doubled Number", uri);
//
//        setResult(RESULT_OK, result);
        this.finish();
    }

}
