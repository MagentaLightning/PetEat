package com.workingdummies.peteat;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
                Intent intent = new Intent(getActivity(), ChartsActivity.class);
                startActivity(intent);
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

        return RootView;
    }

    //Métodos

    public void checkFoodValue(){

        String iduser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(iduser).child("pet").child("1").child("foodwatervalidations");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String foodvalidationstring= Objects.requireNonNull(dataSnapshot.child("food").getValue()).toString();
                foodvalidation = Integer.parseInt(foodvalidationstring);

                String watervalidationstring= Objects.requireNonNull(dataSnapshot.child("water").getValue()).toString();
                watervalidation = Integer.parseInt(watervalidationstring);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



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

        Map<String, Object> mapvalidationfood = new HashMap<>();
        mapvalidationfood.put("food", String.valueOf(foodvalidation) );
        mapvalidationfood.put("water", String.valueOf(watervalidation) );

        DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child(iduser);

        updateData.child("pet").child(idpet).child("foodwatervalidations").setValue(mapvalidationfood);
    }



    public void checkWaterValue(){

        String iduser = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(iduser).child("pet").child("1").child("foodwatervalidations");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String watervalidationstring= Objects.requireNonNull(dataSnapshot.child("water").getValue()).toString();
                watervalidation = Integer.parseInt(watervalidationstring);

                String foodvalidationstring= Objects.requireNonNull(dataSnapshot.child("food").getValue()).toString();
                foodvalidation = Integer.parseInt(foodvalidationstring);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

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

        Map<String, Object> mapvalidationfood = new HashMap<>();
        mapvalidationfood.put("water", String.valueOf(watervalidation) );
        mapvalidationfood.put("food", String.valueOf(foodvalidation) );

        DatabaseReference updateData = FirebaseDatabase.getInstance().getReference().child(iduser);

        updateData.child("pet").child(idpet).child("foodwatervalidations").setValue(mapvalidationfood);
    }


}
