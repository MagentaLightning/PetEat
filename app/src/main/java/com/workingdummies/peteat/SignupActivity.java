package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    TextInputLayout text_input_email, text_input_password, text_input_repeat_password;
    EditText edit_text_email, edit_text_password, edit_text_repeat_password;
    Button button_sign_up, button_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // BOTONES
        button_sign_up = findViewById(R.id.button_signup);
        button_signin = findViewById(R.id.button_signin);
        Typeface Quicksand_Bold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_sign_up.setTypeface(Quicksand_Bold);
        button_signin.setTypeface(Quicksand_Bold);

        // EDIT TEXT & TEXT INPUT
        edit_text_email = findViewById(R.id.edit_text_email);
        text_input_email = findViewById(R.id.text_input_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        text_input_password = findViewById(R.id.text_input_password);
        edit_text_repeat_password = findViewById(R.id.edit_text_repeat_password);
        text_input_repeat_password = findViewById(R.id.text_input_repeat_password);
        Typeface Raleway_Regular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        edit_text_email.setTypeface(Raleway_Regular);
        text_input_email.setTypeface(Raleway_Regular);
        edit_text_password.setTypeface(Raleway_Regular);
        text_input_password.setTypeface(Raleway_Regular);
        edit_text_repeat_password.setTypeface(Raleway_Regular);
        text_input_repeat_password.setTypeface(Raleway_Regular);

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, HomeActivity.class));
            }
        });
    }
}
