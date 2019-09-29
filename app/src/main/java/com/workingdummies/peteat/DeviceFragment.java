package com.workingdummies.peteat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class DeviceFragment extends Fragment {

    TextInputLayout text_input_identifier, text_input_wifi, text_input_password;
    EditText edit_text_identifier, edit_text_password;
    Button button_connect, action_button_add;
    TextView text_view_wifi_name;
    AutoCompleteTextView filled_exposed_dropdown;

    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext=context;
    }

    @SuppressLint("CutPasteId")

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.action_button_add);
        if(item!=null)
            item.setVisible(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View RootView = inflater.inflate(R.layout.fragment_device, container, false);


        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // BOTONES
        button_connect = RootView.findViewById(R.id.button_connect);
        Typeface QuicksandBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand_Bold.otf");
        button_connect.setTypeface(QuicksandBold);

        // EDIT TEXT & TEXT INPUT
        edit_text_identifier = RootView.findViewById(R.id.edit_text_identifier);
        text_input_identifier = RootView.findViewById(R.id.text_input_identifier);
        edit_text_password = RootView.findViewById(R.id.edit_text_password);
        text_input_password = RootView.findViewById(R.id.text_input_password);
        text_input_wifi = RootView.findViewById(R.id.text_input_wifi);
        Typeface RalewayRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/Raleway-Regular.ttf");
        edit_text_identifier.setTypeface(RalewayRegular);
        text_input_identifier.setTypeface(RalewayRegular);
        edit_text_password.setTypeface(RalewayRegular);
        text_input_password.setTypeface(RalewayRegular);
        text_input_wifi.setTypeface(RalewayRegular);

        //  TEXT VIEW
        text_view_wifi_name = RootView.findViewById(R.id.text_view_wifi_name);
        text_view_wifi_name.setTypeface(RalewayRegular);

        // AUTO COMPLETE TEXT VIEW
        filled_exposed_dropdown = RootView.findViewById(R.id.filled_exposed_dropdown);
        filled_exposed_dropdown.setTypeface(RalewayRegular);

        String[] WIFI = new String[]{"INFINITUM", "INFINITUM", "INFINITUM", "INFINITUM"};

        ArrayAdapter<String> myListAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_expandable_list_item_1, WIFI);

        @SuppressLint("CutPasteId") AutoCompleteTextView editTextFilledExposedDropdown = RootView.findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(myListAdapter);

        button_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // La actividad terminará hasta que termine el tiempo del Snackbar
                Snackbar.make(view, R.string.snackbar_successful_connection, Snackbar.LENGTH_SHORT)
                        .addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                            }
                        }).show();
            }
        });

        return RootView;
    }
}
