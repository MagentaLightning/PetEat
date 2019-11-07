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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddPetActivity extends AppCompatActivity {

    TextInputLayout text_input_name;
    EditText edit_text_name, edit_text_discharge,
            edit_text_quantity;
    Button button_add;
    RadioGroup radio_group_kind;
    RadioButton radio_button_cat, radio_button_dog;
    TextView text_view_kind, text_view_food, text_view_discharge,
            text_view_per_day, text_view_quantity, text_view_per_discharge;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        //Inicializamos el objeto Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        radio_group_kind = findViewById(R.id.radio_group_kind);
        radio_button_cat = findViewById(R.id.radio_button_cat);
        radio_button_dog = findViewById(R.id.radio_button_dog);

        progressBar = findViewById(R.id.progress_bar);

        button_add = findViewById(R.id.button_add);
        Typeface QuicksandBold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        button_add.setTypeface(QuicksandBold);

        text_view_kind = findViewById(R.id.text_view_kind);
        text_view_food = findViewById(R.id.text_view_food);
        text_view_discharge = findViewById(R.id.text_view_discharge);
        text_view_per_day = findViewById(R.id.text_view_per_day);
        text_view_quantity = findViewById(R.id.text_view_quantity);
        text_view_per_discharge = findViewById(R.id.text_view_per_discharge);
        text_view_kind.setTypeface(QuicksandBold);
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
        edit_text_name.setTypeface(RalewayRegular);
        text_input_name.setTypeface(RalewayRegular);
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

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(AddPetActivity.this)
                        .setMessage(R.string.dialog_confirm_add)
                        .setNegativeButton(getString(R.string.button_cancel), null)
                        .setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                registerpet();
                            }
                        })
                        .show();
            }
        });
    }

    //Metodos
    public void registerpet(){
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


        mDatabase.child(iduser).child("pet").child(idpet).child("name").setValue(nametext);
        mDatabase.child(iduser).child("pet").child(idpet).child("food").setValue(mapfood);
        mDatabase.child(iduser).child("pet").child(idpet).child("foodgiven").setValue(mapfoodgiven);
        mDatabase.child(iduser).child("pet").child(idpet).child("watergiven").setValue(mapwatergiven);

        Toast.makeText(AddPetActivity.this, R.string.snackbar_pet_added, Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.GONE);
        finish();
    }
}
