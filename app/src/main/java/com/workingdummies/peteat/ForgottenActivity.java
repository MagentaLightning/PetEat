package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class ForgottenActivity extends AppCompatActivity {

    TextInputLayout text_input_email;
    EditText edit_text_email;
    Button button_recover_password, button_signin;
    TextView text_view_recover_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten);

        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // BOTONES
        button_recover_password = findViewById(R.id.button_recover_password);
        button_signin = findViewById(R.id.button_signin);
        Typeface Quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_recover_password.setTypeface(Quicksand);
        button_signin.setTypeface(Quicksand);

        // EDIT TEXT & TEXT INPUT
        edit_text_email = findViewById(R.id.edit_text_email);
        text_input_email = findViewById(R.id.text_input_email);
        Typeface RalewayRegular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        edit_text_email.setTypeface(RalewayRegular);
        text_input_email.setTypeface(RalewayRegular);

        // TEXT VIEW
        text_view_recover_info = findViewById(R.id.text_view_recover_info);
        Typeface Raleway_Medium = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf");
        text_view_recover_info.setTypeface(Raleway_Medium);

        button_recover_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // La actividad terminará hasta que termine el tiempo del Snackbar
                Snackbar.make(view, R.string.snackbar_recover_email, Snackbar.LENGTH_SHORT)
                        .addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);

                                finish();
                            }
                        }).show();
            }
        });

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
