package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class AddPetActivity extends AppCompatActivity {

    TextInputLayout text_input_name, text_input_date, text_input_weight, text_input_height,
            text_input_size, text_input_discharge, text_input_quantity, text_input_identifier;
    EditText edit_text_name, edit_text_date, edit_text_weight, edit_text_height, edit_text_discharge,
            edit_text_quantity, edit_text_identifier;
    Button button_add;
    RadioGroup radio_group_kind;
    RadioButton radio_button_cat, radio_button_dog, radio_button_male, radio_button_female;
    TextView text_view_kind, text_view_sex, text_view_birth_date, text_view_weight, text_view_kg,
            text_view_height, text_view_cm, text_view_size, text_view_food, text_view_discharge,
            text_view_per_day, text_view_quantity, text_view_per_discharge, text_view_identifier;
    AutoCompleteTextView filled_exposed_dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        radio_group_kind = findViewById(R.id.radio_group_kind);
        radio_button_cat = findViewById(R.id.radio_button_cat);
        radio_button_dog = findViewById(R.id.radio_button_dog);

        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // BOTONES
        button_add = findViewById(R.id.button_add);
        Typeface QuicksandBold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_add.setTypeface(QuicksandBold);

        //  TEXT VIEW
        text_view_kind = findViewById(R.id.text_view_kind);
        text_view_sex = findViewById(R.id.text_view_sex);
        text_view_birth_date = findViewById(R.id.text_view_birth_date);
        text_view_weight = findViewById(R.id.text_view_weight);
        text_view_kg = findViewById(R.id.text_view_kg);
        text_view_height = findViewById(R.id.text_view_height);
        text_view_cm = findViewById(R.id.text_view_cm);
        text_view_size = findViewById(R.id.text_view_size);
        text_view_food = findViewById(R.id.text_view_food);
        text_view_discharge = findViewById(R.id.text_view_discharge);
        text_view_per_day = findViewById(R.id.text_view_per_day);
        text_view_quantity = findViewById(R.id.text_view_quantity);
        text_view_per_discharge = findViewById(R.id.text_view_per_discharge);
        text_view_identifier = findViewById(R.id.text_view_identifier);
        text_view_kind.setTypeface(QuicksandBold);
        text_view_sex.setTypeface(QuicksandBold);
        text_view_birth_date.setTypeface(QuicksandBold);
        text_view_weight.setTypeface(QuicksandBold);
        text_view_kg.setTypeface(QuicksandBold);
        text_view_height.setTypeface(QuicksandBold);
        text_view_cm.setTypeface(QuicksandBold);
        text_view_size.setTypeface(QuicksandBold);
        text_view_food.setTypeface(QuicksandBold);
        text_view_discharge.setTypeface(QuicksandBold);
        text_view_per_day.setTypeface(QuicksandBold);
        text_view_quantity.setTypeface(QuicksandBold);
        text_view_per_discharge.setTypeface(QuicksandBold);
        text_view_identifier.setTypeface(QuicksandBold);

        // EDIT TEXT & TEXT INPUT
        edit_text_name = findViewById(R.id.edit_text_name);
        text_input_name = findViewById(R.id.text_input_name);
        edit_text_date = findViewById(R.id.edit_text_date);
        text_input_date = findViewById(R.id.text_input_date);
        edit_text_weight = findViewById(R.id.edit_text_weight);
        text_input_weight = findViewById(R.id.text_input_weight);
        edit_text_height = findViewById(R.id.edit_text_height);
        text_input_height = findViewById(R.id.text_input_height);
        edit_text_discharge = findViewById(R.id.edit_text_discharge);
        text_input_discharge = findViewById(R.id.text_input_discharge);
        edit_text_quantity = findViewById(R.id.edit_text_quantity);
        text_input_quantity = findViewById(R.id.text_input_quantity);
        text_input_size = findViewById(R.id.text_input_size);
        edit_text_identifier = findViewById(R.id.edit_text_identifier);
        text_input_identifier = findViewById(R.id.text_input_identifier);
        Typeface RalewayRegular = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");
        edit_text_name.setTypeface(RalewayRegular);
        text_input_name.setTypeface(RalewayRegular);
        edit_text_date.setTypeface(RalewayRegular);
        text_input_date.setTypeface(RalewayRegular);
        edit_text_weight.setTypeface(RalewayRegular);
        text_input_weight.setTypeface(RalewayRegular);
        edit_text_height.setTypeface(RalewayRegular);
        text_input_height.setTypeface(RalewayRegular);
        edit_text_discharge.setTypeface(RalewayRegular);
        text_input_discharge.setTypeface(RalewayRegular);
        edit_text_quantity.setTypeface(RalewayRegular);
        text_input_quantity.setTypeface(RalewayRegular);
        text_input_size.setTypeface(RalewayRegular);
        edit_text_identifier.setTypeface(RalewayRegular);
        text_input_identifier.setTypeface(RalewayRegular);

        // RADIO BUTTON
        radio_button_male = findViewById(R.id.radio_button_male);
        radio_button_female = findViewById(R.id.radio_button_female);
        radio_button_male.setTypeface(RalewayRegular);
        radio_button_female.setTypeface(RalewayRegular);

        // AUTO COMPLETE TEXT VIEW
        filled_exposed_dropdown = findViewById(R.id.filled_exposed_dropdown);
        filled_exposed_dropdown.setTypeface(RalewayRegular);

        String[] SIZES = new String[] {"Miniatura", "Pequeño", "Mediano", "Grande"};

        ArrayAdapter myListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, SIZES);


        @SuppressLint("CutPasteId") AutoCompleteTextView editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(myListAdapter);

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

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(AddPetActivity.this)
                        .setMessage(R.string.dialog_confirm_add)
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AddPetActivity.this, R.string.snackbar_pet_added, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .show();
            }
        });
    }
}
