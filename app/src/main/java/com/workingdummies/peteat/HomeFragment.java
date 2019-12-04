package com.workingdummies.peteat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.Chart;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.facebook.FacebookSdk.getApplicationContext;

public class HomeFragment extends Fragment {

    TextView text_view_food_gr, text_view_remain_food_gr, text_view_water_ml, text_view_remain_water,
            text_view_info, text_view_name, text_view_consumed_food, text_view_remain_food,
            text_view_consumed_water, text_view_remain, text_view_state;
    ImageButton button_record, button_edit, button_food, button_water;
    ImageView ic_pet;

    private Context mContext;

    //Declaramos lo necesario para firebase
    DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    //Variables públicas
    public int foodvalidation;
    public int watervalidation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_home, container, false);

        button_record = RootView.findViewById(R.id.image_record);
        button_edit = RootView.findViewById(R.id.ic_edit);
        button_food = RootView.findViewById(R.id.image_food);
        button_water = RootView.findViewById(R.id.image_water);
        ic_pet = RootView.findViewById(R.id.ic_pet);

        text_view_food_gr = RootView.findViewById(R.id.text_view_food_gr);
        text_view_remain_food_gr = RootView.findViewById(R.id.text_view_remain_food_gr);
        text_view_water_ml = RootView.findViewById(R.id.text_view_water_ml);
        text_view_remain_water = RootView.findViewById(R.id.text_view_remain_water);
        text_view_info = RootView.findViewById(R.id.text_view_info);
        Typeface QuicksandBook = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand_Book.otf");
        text_view_food_gr.setTypeface(QuicksandBook);
        text_view_remain_food_gr.setTypeface(QuicksandBook);
        text_view_water_ml.setTypeface(QuicksandBook);
        text_view_remain_water.setTypeface(QuicksandBook);
        text_view_info.setTypeface(QuicksandBook);

        text_view_name = RootView.findViewById(R.id.text_view_name);
        text_view_consumed_food = RootView.findViewById(R.id.text_view_consumed_food);
        text_view_remain_food = RootView.findViewById(R.id.text_view_remain_food);
        text_view_consumed_water = RootView.findViewById(R.id.text_view_consumed_water);
        text_view_remain = RootView.findViewById(R.id.text_view_remain);
        text_view_state = RootView.findViewById(R.id.text_view_state);
        Typeface QuicksandBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand_Bold.otf");
        text_view_name.setTypeface(QuicksandBold);
        text_view_consumed_food.setTypeface(QuicksandBold);
        text_view_remain_food.setTypeface(QuicksandBold);
        text_view_consumed_water.setTypeface(QuicksandBold);
        text_view_remain.setTypeface(QuicksandBold);
        text_view_state.setTypeface(QuicksandBold);

        //Inicializamos el objeto Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditPetActivity.class);
                startActivity(intent);
            }
        });

        button_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Veterinarias");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        button_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkFoodValue();
                setFoodValue();
            }
        });

        button_water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkWaterValue();
                setWaterValue();
            }
        });

        setTextFoodValue();
        setTextWaterValue();
        setTextNameValue();
        setKindValue();

        return RootView;
    }

    //Métodos

    public void checkFoodValue(){

        String iduser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(iduser).child("pet").child("1").child("foodwatervalidations");


        try {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try{
                    String foodvalidationstring = dataSnapshot.child("food").getValue().toString();
                    foodvalidation = Integer.parseInt(foodvalidationstring);
                    }
                    catch(Exception e){
                        Toast.makeText(getApplicationContext(), R.string.error_food_values, Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }



    }

    public void setFoodValue(){

        if (foodvalidation == 0){
            Context ctx=this.getActivity();
            new MaterialAlertDialogBuilder(ctx)
                    .setMessage(R.string.dialog_open_food)
                    .setNegativeButton(getString(R.string.button_cancel), null)
                    .setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            foodvalidation = 1;
                            updateFoodValidation();
                        }
                    })
                    .show();


        }else{
            foodvalidation = 0;
            updateFoodValidation();
        }

    }

    public void updateFoodValidation(){
        String iduser = mAuth.getCurrentUser().getUid();
        String idpet = "1";

        DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child(iduser);

        updateData.child("pet").child(idpet).child("foodwatervalidations").child("food").setValue(foodvalidation);
    }



    public void checkWaterValue(){

        String iduser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(iduser).child("pet").child("1").child("foodwatervalidations");


        try {
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try{
                    String watervalidationstring = dataSnapshot.child("water").getValue().toString();
                    watervalidation = Integer.parseInt(watervalidationstring);

                    }
                    catch(Exception e){
                        Toast.makeText(getApplicationContext(), R.string.error_water_values, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void setWaterValue(){

        if (watervalidation == 0){
            Context ctx=this.getActivity();
            new MaterialAlertDialogBuilder(ctx)
                    .setMessage(R.string.dialog_open_water)
                    .setNegativeButton(getString(R.string.button_cancel), null)
                    .setPositiveButton(getString(R.string.button_accept), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which){
                            watervalidation = 1;
                            updateWaterValidation();
                        }
                    })
                    .show();


        }else{
            watervalidation = 0;
            updateWaterValidation();
        }

    }

    public void updateWaterValidation(){
        String iduser = mAuth.getCurrentUser().getUid();
        String idpet = "1";

        DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child(iduser);

        updateData.child("pet").child(idpet).child("foodwatervalidations").child("water").setValue(watervalidation);
    }

    public void setTextFoodValue(){

        try {
            String iduser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child(iduser).child("pet").child("1").child("foodgiven");
        }
        catch (Exception e){

        }


            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try{
                    String foodconsumed = dataSnapshot.child("consumed").getValue().toString();
                    foodconsumed = foodconsumed + " gr";
                    text_view_food_gr.setText(foodconsumed);
                    }
                    catch(Exception e){

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
    }

    public void setTextWaterValue(){

        try {
            String iduser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child(iduser).child("pet").child("1").child("watergiven");
        }
        catch (Exception e){

        }


            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try{
                    String waterconsumed = dataSnapshot.child("consumed").getValue().toString();
                    waterconsumed = waterconsumed + " ml";
                    text_view_water_ml.setText(waterconsumed);
                    }
                    catch(Exception e){

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    public void setTextNameValue(){

        try {
            String iduser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child(iduser).child("pet").child("1");
        }
        catch (Exception e){

        }


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    String namestring = dataSnapshot.child("name").getValue().toString();
                    text_view_name.setText(namestring);
                }
                catch(Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void setKindValue(){

        try {
            String iduser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child(iduser).child("pet").child("1");
        }
        catch (Exception e){
            ic_pet.setImageResource(R.drawable.ic_catcolor);
        }


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    int kind = Integer.parseInt(dataSnapshot.child("kind").getValue().toString());
                    if (kind == 0){
                        ic_pet.setImageResource(R.drawable.ic_catcolor);
                    }else{
                        ic_pet.setImageResource(R.drawable.ic_dogcolor);
                    }
                }
                catch(Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
