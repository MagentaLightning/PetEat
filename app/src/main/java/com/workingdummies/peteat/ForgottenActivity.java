package com.workingdummies.peteat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgottenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextInputLayout text_input_email;
    EditText edit_text_email;
    Button button_recover_password, button_signin;
    TextView text_view_recover_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_forgotten);

        button_recover_password = findViewById(R.id.button_recover_password);
        button_signin = findViewById(R.id.button_signin);
        Typeface Quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_recover_password.setTypeface(Quicksand);
        button_signin.setTypeface(Quicksand);

        edit_text_email = findViewById(R.id.edit_text_email);
        text_input_email = findViewById(R.id.text_input_email);
        Typeface RalewayRegular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        edit_text_email.setTypeface(RalewayRegular);
        text_input_email.setTypeface(RalewayRegular);

        text_view_recover_info = findViewById(R.id.text_view_recover_info);
        Typeface Raleway_Medium = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Medium.ttf");
        text_view_recover_info.setTypeface(Raleway_Medium);

        edit_text_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_input_email.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String result = editable.toString().replaceAll(" ", "");
                if (!editable.toString().equals(result)) {
                    edit_text_email.setText(result);
                    edit_text_email.setSelection(result.length());
                }
            }
        });

        button_recover_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                button_recover_password.setEnabled(false);

                if (edit_text_email.getText().toString().isEmpty()) {
                    text_input_email.setError(getText(R.string.error_email_required));
                    button_recover_password.setEnabled(true);
                    return;
                }

                if (!isEmailValid(edit_text_email.getText().toString())){
                    text_input_email.setError(getText(R.string.error_email_format));
                    button_recover_password.setEnabled(true);
                    return;
                }

                mAuth.sendPasswordResetEmail(edit_text_email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Snackbar.make(view, R.string.snackbar_recover_email, Snackbar.LENGTH_SHORT)
                                            .addCallback(new Snackbar.Callback() {
                                                @Override
                                                public void onDismissed(Snackbar snackbar, int event) {
                                                    super.onDismissed(snackbar, event);

                                                    finish();
                                                }
                                            }).show();
                                } else {
                                    Toast.makeText(ForgottenActivity.this, R.string.error_auth_reset, Toast.LENGTH_SHORT).show();
                                    button_recover_password.setEnabled(true);
                                }
                            }
                        });
            }
        });

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onBackPressed() {
    }
}
