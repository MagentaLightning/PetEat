package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout text_input_email, text_input_password;
    EditText edit_text_email, edit_text_password;
    TextView text_view_connect;
    Button button_sign_up, button_login, button_recover_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // BOTONES
        button_sign_up = findViewById(R.id.button_signup);
        button_login = findViewById(R.id.button_login);
        button_recover_password = findViewById(R.id.button_recover_password);
        Typeface Quicksand_Bold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_sign_up.setTypeface(Quicksand_Bold);
        button_login.setTypeface(Quicksand_Bold);
        button_recover_password.setTypeface(Quicksand_Bold);

        // TEXTVIEW
        text_view_connect = findViewById(R.id.text_view_connect);
        Typeface Raleway_Regular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        text_view_connect.setTypeface(Raleway_Regular);

        // EDIT TEXT & TEXT INPUT
        edit_text_email = findViewById(R.id.edit_text_email);
        text_input_email = findViewById(R.id.text_input_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        text_input_password = findViewById(R.id.text_input_password);
        edit_text_email.setTypeface(Raleway_Regular);
        text_input_email.setTypeface(Raleway_Regular);
        edit_text_password.setTypeface(Raleway_Regular);
        text_input_password.setTypeface(Raleway_Regular);

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });

        button_recover_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgottenActivity.class));
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
}
