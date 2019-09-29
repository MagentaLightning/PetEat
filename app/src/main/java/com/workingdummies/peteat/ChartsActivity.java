package com.workingdummies.peteat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ChartsActivity extends AppCompatActivity {

    LineChart mpLineChart, mpLineChart2;
    TextView text_view_pet_name;
    ImageButton button_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charts);

        button_location = findViewById(R.id.button_location);

        text_view_pet_name = findViewById(R.id.text_view_pet_name);
        Typeface QuicksandBold = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Bold.otf");
        text_view_pet_name.setTypeface(QuicksandBold);

        // POR AHORA ESTO ES PARA LOS CHARTS DE PRUEBA, NADA RELEVANTE POR AHORA
        mpLineChart = findViewById(R.id.line_chart);
        mpLineChart2 = findViewById(R.id.line_chart2);
        LineDataSet lineDataSet1 = new LineDataSet(dataValues(), "Data Set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart2.setData(data);
        mpLineChart.invalidate();
        mpLineChart2.invalidate();
        //

        button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChartsActivity.this, "Abrir Google Maps", Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<Entry> dataValues(){
        ArrayList<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0,20));
        dataVals.add(new Entry(1,24));
        dataVals.add(new Entry(2,2));
        dataVals.add(new Entry(3,10));
        dataVals.add(new Entry(4,28));
        return dataVals;
    }
}
