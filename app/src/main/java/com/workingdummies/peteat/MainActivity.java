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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private CallbackManager mCallbackManager;

    TextInputLayout text_input_email, text_input_password;
    private FirebaseAuth mAuth;
    EditText edit_text_email, edit_text_password;
    Button button_sign_up, button_login, button_recover_password;
    LoginButton button_login_facebook;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme((R.style.AppTheme));

        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mCallbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);

        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // BOTONES
        button_login_facebook = findViewById(R.id.button_login_facebook);
        button_login_facebook.setReadPermissions("email", "public_profile");

        button_sign_up = findViewById(R.id.button_signup);
        button_login = findViewById(R.id.button_login);
        button_recover_password = findViewById(R.id.button_recover_password);
        Typeface Quicksand_Bold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_sign_up.setTypeface(Quicksand_Bold);
        button_login.setTypeface(Quicksand_Bold);
        button_recover_password.setTypeface(Quicksand_Bold);

        // EDIT TEXT & TEXT INPUT
        edit_text_email = findViewById(R.id.edit_text_email);
        text_input_email = findViewById(R.id.text_input_email);
        edit_text_password = findViewById(R.id.edit_text_password);
        text_input_password = findViewById(R.id.text_input_password);
        Typeface Raleway_Regular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        edit_text_email.setTypeface(Raleway_Regular);
        text_input_email.setTypeface(Raleway_Regular);
        edit_text_password.setTypeface(Raleway_Regular);
        text_input_password.setTypeface(Raleway_Regular);

        FailedSignup();

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

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                errorNull();
                button_login.setEnabled(false);

                if (edit_text_email.getText().toString().isEmpty()) {
                    text_input_email.setError(getText(R.string.error_email_required));
                    button_login.setEnabled(true);
                    return;
                }

                if (!isEmailValid(edit_text_email.getText().toString())){
                    text_input_email.setError(getText(R.string.error_email_format));
                    button_login.setEnabled(true);
                    return;
                }

                if (edit_text_password.getText().toString().isEmpty()) {
                    text_input_password.setError(getText(R.string.error_password_required));
                    button_login.setEnabled(true);
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
                                    if(NewUser) {
                                        FailedSignup();
                                        text_input_email.setError(getText(R.string.error_email_non_exist));
                                    }
                                } catch (Exception e) {
                                    //error
                                }
                            }
                        });

                progressBar.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(edit_text_email.getText().toString(), edit_text_password.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    if (edit_text_password.length() < 6) {
                                        text_input_password.setError(getString(R.string.error_min_password));
                                        FailedSignup();
                                    } else {
                                        FailedSignup();
                                        Toast.makeText(MainActivity.this, getString(R.string.error_auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                    button_login.setEnabled(true);
                                } else {
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    edit_text_email.setText("");
                                    edit_text_password.setText("");
                                    FailedSignup();
                                }
                            }
                        });
            }
        });

        button_login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Verification();
                errorNull();
            }
        });

        button_login_facebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                FailedSignup();
            }

            @Override
            public void onError(FacebookException error) {
                FailedSignup();
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
        button_login.setEnabled(true);
        button_login_facebook.setEnabled(true);
        button_login_facebook.setVisibility(View.VISIBLE);
    }

    public void Verification() {
        edit_text_email.setEnabled(false);
        edit_text_password.setEnabled(false);
        button_login.setEnabled(false);
        button_login_facebook.setEnabled(false);
        button_login_facebook.setVisibility(View.INVISIBLE);
    }

    public void errorNull() {
        text_input_email.setError(null);
        text_input_password.setError(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                            edit_text_email.setText("");
                            edit_text_password.setText("");
                            FailedSignup();
                        } else {
                            Toast.makeText(MainActivity.this, getString(R.string.error_auth_failed_),
                                    Toast.LENGTH_LONG).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
}
