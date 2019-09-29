package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    TextView text_view_about, text_view_credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // TEXTVIEW
        text_view_about = findViewById(R.id.text_view_about);
        text_view_credits = findViewById(R.id.text_view_credits);
        Typeface RalewayRegular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf");
        text_view_about.setTypeface(RalewayRegular);
        text_view_credits.setTypeface(RalewayRegular);
    }
}