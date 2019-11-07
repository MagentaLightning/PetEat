package com.workingdummies.peteat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.Chart;

public class HomeFragment extends Fragment {

    TextView text_view_food_gr, text_view_remain_food_gr, text_view_water_ml, text_view_remain_water,
            text_view_info, text_view_name, text_view_consumed_food, text_view_remain_food,
            text_view_consumed_water, text_view_remain, text_view_state;
    ImageButton button_record, button_edit;

    private Context mContext;

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

        return RootView;
    }
}
