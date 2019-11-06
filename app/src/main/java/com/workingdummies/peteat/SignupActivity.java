package com.workingdummies.peteat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextInputLayout text_input_email, text_input_password, text_input_repeat_password;
    EditText edit_text_email, edit_text_password, edit_text_repeat_password;
    Button button_sign_up, button_signin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_signup);

        progressBar = findViewById(R.id.progress_bar);

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

        edit_text_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_input_password.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String result = editable.toString().replaceAll(" ", "");
                if (!editable.toString().equals(result)) {
                    edit_text_password.setText(result);
                    edit_text_password.setSelection(result.length());
                }
            }
        });

        edit_text_repeat_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                text_input_repeat_password.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String result = editable.toString().replaceAll(" ", "");
                if (!editable.toString().equals(result)) {
                    edit_text_repeat_password.setText(result);
                    edit_text_repeat_password.setSelection(result.length());
                }
            }
        });

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                text_input_email.setError(null);
                text_input_password.setError(null);
                text_input_repeat_password.setError(null);
                button_sign_up.setEnabled(false);

                if (edit_text_email.getText().toString().isEmpty()) {
                    text_input_email.setError(getText(R.string.error_email_required));
                    button_sign_up.setEnabled(true);
                    return;
                }

                if (!isEmailValid(edit_text_email.getText().toString())){
                    text_input_email.setError(getText(R.string.error_email_format));
                    button_sign_up.setEnabled(true);
                    return;
                }

                if (edit_text_password.getText().toString().isEmpty()) {
                    text_input_password.setError(getText(R.string.error_password_required));
                    button_sign_up.setEnabled(true);
                    return;
                }

                if (edit_text_password.length() < 6) {
                    text_input_password.setError(getString(R.string.error_min_password));
                    button_sign_up.setEnabled(true);
                    return;
                }

                if (edit_text_repeat_password.getText().toString().isEmpty()) {
                    text_input_repeat_password.setError(getText(R.string.error_password_required));
                    button_sign_up.setEnabled(true);
                    return;
                }

                if (edit_text_repeat_password.length() < 6) {
                    text_input_repeat_password.setError(getString(R.string.error_min_password));
                    button_sign_up.setEnabled(true);
                    return;
                }

                if (!edit_text_password.getText().toString().equals(edit_text_repeat_password.getText().toString())) {
                    text_input_password.setError(getString(R.string.error_repeat_password));
                    text_input_repeat_password.setError(getString(R.string.error_repeat_password));
                    button_sign_up.setEnabled(true);
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                Verification();
                mAuth.fetchSignInMethodsForEmail(edit_text_email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                                boolean NewUser;
                                try {
                                    NewUser = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getSignInMethods()).isEmpty();
                                    if(!NewUser) {
                                        FailedSignup();
                                        text_input_email.setError(getText(R.string.error_email_exist));
                                    }
                                } catch (Exception e) {
                                    // Error
                                }
                            }
                        });

                mAuth.createUserWithEmailAndPassword(edit_text_email.getText().toString(), edit_text_password.getText().toString())
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, R.string.error_auth_failed_signup,
                                            Toast.LENGTH_SHORT).show();
                                    FailedSignup();
                                } else {
                                    Snackbar.make(view, R.string.error_auth_successful_signup, Snackbar.LENGTH_SHORT)
                                            .addCallback(new Snackbar.Callback() {
                                                @Override
                                                public void onDismissed(Snackbar snackbar, int event) {
                                                    super.onDismissed(snackbar, event);

                                                    finish();
                                                }
                                            }).show();
                                }
                            }
                        });

            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onBackPressed() {
    }

    public void FailedSignup() {
        progressBar.setVisibility(View.GONE);
        edit_text_email.setEnabled(true);
        edit_text_password.setEnabled(true);
        edit_text_repeat_password.setEnabled(true);
        button_sign_up.setEnabled(true);
    }

    public void Verification() {
        edit_text_email.setEnabled(false);
        edit_text_password.setEnabled(false);
        edit_text_repeat_password.setEnabled(false);
    }
}
