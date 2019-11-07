package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
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

public class EditPetActivity extends AppCompatActivity {

    TextInputLayout text_input_name;
    EditText edit_text_name, edit_text_discharge,
            edit_text_quantity;
    Button button_update, button_delete;
    RadioGroup radio_group_kind;
    RadioButton radio_button_cat, radio_button_dog;
    TextView text_view_kind, text_view_food, text_view_discharge,
            text_view_per_day, text_view_quantity, text_view_per_discharge;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pet);

        radio_group_kind = findViewById(R.id.radio_group_kind);
        radio_button_cat = findViewById(R.id.radio_button_cat);
        radio_button_dog = findViewById(R.id.radio_button_dog);

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
                                Toast.makeText(EditPetActivity.this, R.string.snackbar_profile_deleted, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .show();
            }
        });
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(EditPetActivity.this)
                        .setMessage(R.string.dialog_confirm_changes)
                        .setNegativeButton(getString(R.string.button_cancel), null)
                        .setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(EditPetActivity.this, R.string.snackbar_profile_updated, Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .show();
            }
        });
    }
}