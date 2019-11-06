package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputLayout text_input_email, text_input_new_password, text_input_new_repeat_password, text_input_password, text_input_repeat_password;
    Button button_accept;
    EditText edit_text_email, edit_text_new_password, edit_text_new_repeat_password, edit_text_password, edit_text_repeat_password;
    TextView text_view_change_notification, text_view_new_data;
    ProgressBar progressBar;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        progressBar = findViewById(R.id.progress_bar);

        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // BOTONES
        button_accept = findViewById(R.id.button_accept);
        Typeface QuicksandBold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_accept.setTypeface(QuicksandBold);

        // EDIT TEXT & TEXT INPUT
        text_input_email = findViewById(R.id.text_input_email);
        edit_text_email = findViewById(R.id.edit_text_email);
        text_input_password = findViewById(R.id.text_input_password);
        edit_text_password = findViewById(R.id.edit_text_password);
        text_input_repeat_password = findViewById(R.id.text_input_repeat_password);
        edit_text_repeat_password = findViewById(R.id.edit_text_repeat_password);
        text_input_new_password = findViewById(R.id.text_input_new_password);
        edit_text_new_password = findViewById(R.id.edit_text_new_password);
        text_input_new_repeat_password = findViewById(R.id.text_input_new_repeat_password);
        edit_text_new_repeat_password = findViewById(R.id.edit_text_new_repeat_password);
        Typeface RalewayRegular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        text_input_email.setTypeface(RalewayRegular);
        edit_text_email.setTypeface(RalewayRegular);
        text_input_password.setTypeface(RalewayRegular);
        edit_text_password.setTypeface(RalewayRegular);
        text_input_repeat_password.setTypeface(RalewayRegular);
        edit_text_repeat_password.setTypeface(RalewayRegular);
        text_input_new_password.setTypeface(RalewayRegular);
        edit_text_new_password.setTypeface(RalewayRegular);
        text_input_new_repeat_password.setTypeface(RalewayRegular);
        edit_text_new_repeat_password.setTypeface(RalewayRegular);

        // TEXTVIEW
        text_view_change_notification = findViewById(R.id.text_view_change_notification);
        text_view_new_data = findViewById(R.id.text_view_new_data);
        Typeface RalewayMedium = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf");
        text_view_change_notification.setTypeface(RalewayMedium);
        text_view_new_data.setTypeface(RalewayMedium);

        button_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(ChangePasswordActivity.this)
                        .setMessage(R.string.dialog_confirm_changes)
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ChangePasswordActivity.this, R.string.snackbar_profile_updated, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .show();
            }
        });
    }
}
