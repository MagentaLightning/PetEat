package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditPetActivity extends AppCompatActivity {

    int cont = 0;
    int cont2 = 0;
    TextInputLayout text_input_name;
    EditText edit_text_name, edit_text_discharge,
            edit_text_quantity;
    Button button_update, button_delete;
    AppCompatImageButton button_minus_discharge, button_plus_discharge , button_minus_quantity, button_plus_quantity;
    RadioGroup radio_group_kind;
    RadioButton radio_button_cat, radio_button_dog;
    TextView text_view_kind, text_view_food, text_view_discharge,
            text_view_per_day, text_view_quantity, text_view_per_discharge;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);

        //Inicializamos el objeto Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        radio_group_kind = findViewById(R.id.radio_group_kind);
        radio_button_cat = findViewById(R.id.radio_button_cat);
        radio_button_dog = findViewById(R.id.radio_button_dog);
        button_minus_discharge = findViewById(R.id.button_minus_discharge);
        button_plus_discharge = findViewById(R.id.button_plus_discharge);
        button_minus_quantity = findViewById(R.id.button_minus_quantity);
        button_plus_quantity = findViewById(R.id.button_plus_quantity);

        progressBar = findViewById(R.id.progress_bar);

        button_update = findViewById(R.id.button_update);
        button_delete = findViewById(R.id.button_delete);
        Typeface QuicksandBold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_update.setTypeface(QuicksandBold);
        button_delete.setTypeface(QuicksandBold);

        text_view_kind = findViewById(R.id.text_view_kind);
        text_view_food = findViewById(R.id.text_view_food);
        text_view_discharge = findViewById(R.id.text_view_discharge);
        text_view_per_day = findViewById(R.id.text_view_per_day);
        text_view_quantity = findViewById(R.id.text_view_quantity);
        text_view_per_discharge = findViewById(R.id.text_view_per_discharge);
        text_view_food.setTypeface(QuicksandBold);
        text_view_discharge.setTypeface(QuicksandBold);
        text_view_per_day.setTypeface(QuicksandBold);
        text_view_quantity.setTypeface(QuicksandBold);
        text_view_per_discharge.setTypeface(QuicksandBold);

        edit_text_name = findViewById(R.id.edit_text_name);
        text_input_name = findViewById(R.id.text_input_name);
        edit_text_discharge = findViewById(R.id.edit_text_discharge);
        edit_text_quantity = findViewById(R.id.edit_text_quantity);
        Typeface RalewayRegular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        edit_text_discharge.setTypeface(RalewayRegular);
        edit_text_quantity.setTypeface(RalewayRegular);

        radio_group_kind.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(i)
                {
                    case R.id.radio_button_cat:
                        radio_button_cat.setBackgroundResource(R.drawable.ic_cat);
                        radio_button_dog.setBackgroundResource(R.drawable.ic_dog_unchecked);
                        break;
                    case R.id.radio_button_dog:
                        radio_button_dog.setBackgroundResource(R.drawable.ic_dog);
                        radio_button_cat.setBackgroundResource(R.drawable.ic_cat_unchecked);
                        break;
                }
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(EditPetActivity.this)
                        .setMessage(R.string.dialog_confirm_delete)
                        .setNegativeButton(getString(R.string.button_cancel), null)
                        .setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deletepet();
                            }
                        })
                        .show();
            }
        });

        button_plus_discharge.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cont = cont + 1;

                edit_text_discharge.setText(String.valueOf(cont));
                button_minus_discharge.setEnabled(true);

                return true;
            }
        });

        button_minus_discharge.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cont = cont - 1;


                if(cont<0){
                    cont=0;
                    edit_text_discharge.setText(String.valueOf(cont));
                    button_minus_discharge.setEnabled(false);

                }

                edit_text_discharge.setText(String.valueOf(cont));
                return true;
            }
        });

        button_plus_quantity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cont2 = cont2 + 1;

                edit_text_quantity.setText(String.valueOf(cont2));
                button_minus_quantity.setEnabled(true);

                return true;
            }
        });

        button_minus_quantity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                cont2 = cont2 - 1;


                if(cont2<0){
                    cont2=0;
                    edit_text_quantity.setText(String.valueOf(cont2));
                    button_minus_quantity.setEnabled(false);

                }

                edit_text_quantity.setText(String.valueOf(cont2));
                return true;
            }
        });

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                text_input_name.setError(null);
                edit_text_discharge.setError(null);
                edit_text_quantity.setError(null);
                button_update.setEnabled(false);

                if (edit_text_name.getText().toString().isEmpty()) {
                    text_input_name.setError(getText(R.string.error_name_required));
                    button_update.setEnabled(true);
                    return;
                }

                if (edit_text_discharge.getText().toString().isEmpty()) {
                    edit_text_discharge.setError(getText(R.string.error_discharge_no_valid));
                    button_update.setEnabled(true);
                    return;
                }

                if (Integer.parseInt(edit_text_discharge.getText().toString()) == 0) {
                    edit_text_discharge.setError(getString(R.string.error_discharge_no_valid));
                    button_update.setEnabled(true);
                    return;
                }

                if (edit_text_quantity.getText().toString().isEmpty()) {
                    edit_text_quantity.setError(getText(R.string.error_quantity_no_valid));
                    button_update.setEnabled(true);
                    return;
                }

                if (Integer.parseInt(edit_text_quantity.getText().toString()) == 0 ) {
                    edit_text_quantity.setError(getString(R.string.error_quantity_no_valid));
                    button_update.setEnabled(true);
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                Verification();
                new MaterialAlertDialogBuilder(EditPetActivity.this)
                        .setMessage(R.string.dialog_confirm_changes)
                        .setNegativeButton(getString(R.string.button_cancel), null)
                        .setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                updatepet();
                            }
                        })
                        .show();
            }
        });


    }
    public void Verification() {
        edit_text_name.setEnabled(false);
        edit_text_discharge.setEnabled(false);
        edit_text_quantity.setEnabled(false);
    }


    //Metodos
    public void updatepet(){
        progressBar.setVisibility(View.VISIBLE);

        final String nametext = edit_text_name.getText().toString().trim();
        final String quantitytext = edit_text_quantity.getText().toString().trim();
        final String dischargetext = edit_text_discharge.getText().toString().trim();

        String iduser = mAuth.getCurrentUser().getUid();

        String idpet = "1";

        Map<String, Object> mapfood = new HashMap<>();
        mapfood.put("discharges", dischargetext );
        mapfood.put("quantity", quantitytext);

        Map<String, Object> mapfoodgiven = new HashMap<>();
        mapfoodgiven.put("date", "06/11/2019");
        mapfoodgiven.put("time", "11:32");
        mapfoodgiven.put("consumed", "15");

        Map<String, Object> mapwatergiven = new HashMap<>();
        mapwatergiven.put("date", "06/11/2019");
        mapwatergiven.put("time", "12:32");

        //Adquiriendo id de animal seleccionado
        int radioButtonID = radio_group_kind.getCheckedRadioButtonId();
        View radioButton = radio_group_kind.findViewById(radioButtonID);
        int kindid = radio_group_kind.indexOfChild(radioButton);
        //Adquiriendo id de animal seleccionado

        DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child(iduser);

        updateData.child("pet").child(idpet).child("name").setValue(nametext);
        updateData.child("pet").child(idpet).child("kind").setValue(kindid);
        updateData.child("pet").child(idpet).child("food").setValue(mapfood);
        updateData.child("pet").child(idpet).child("foodgiven").setValue(mapfoodgiven);
        updateData.child("pet").child(idpet).child("watergiven").setValue(mapwatergiven);

        Toast.makeText(EditPetActivity.this, R.string.snackbar_profile_updated, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
        finish();
    }

    public void deletepet(){
        progressBar.setVisibility(View.VISIBLE);
        String iduser = mAuth.getCurrentUser().getUid();

        mDatabase.child(iduser).child("pet").child("1").removeValue();

        Toast.makeText(EditPetActivity.this, R.string.snackbar_profile_deleted, Toast.LENGTH_LONG).show();

        progressBar.setVisibility(View.GONE);
        finish();
    }

}