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
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PreferencesFragment extends Fragment {

    TextInputLayout text_input_messages;
    Switch switch_notifications;
    CheckBox checkbox_sound, checkbox_vibration;
    Button button_accept;
    TextView text_view_messages, text_view_notifications;
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

        View RootView = inflater.inflate(R.layout.fragment_preferences, container, false);

        // Indicamos a qué elementos corresponden las variables
        // Llamamos al tipo de fuente
        // Aplicamos la fuente en los elementos

        // BOTONES
        button_accept = RootView.findViewById(R.id.button_accept);
        Typeface QuicksandBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand_Bold.otf");
        button_accept.setTypeface(QuicksandBold);

        // EDIT TEXT & TEXT INPUT
        text_input_messages = RootView.findViewById(R.id.text_input_messages);
        Typeface RalewayRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/Raleway-Regular.ttf");
        text_input_messages.setTypeface(RalewayRegular);

        //  TEXT VIEW
        text_view_messages = RootView.findViewById(R.id.text_view_messages);
        text_view_messages.setTypeface(RalewayRegular);
        text_view_notifications = RootView.findViewById(R.id.text_view_notifications);
        text_view_notifications.setTypeface(RalewayRegular);

        // CHECKBOX
        checkbox_sound = RootView.findViewById(R.id.checkbox_sound);
        checkbox_sound.setTypeface(RalewayRegular);
        checkbox_vibration = RootView.findViewById(R.id.checkbox_vibration);
        checkbox_vibration.setTypeface(RalewayRegular);

        // AUTO COMPLETE TEXT VIEW
        filled_exposed_dropdown = RootView.findViewById(R.id.filled_exposed_dropdown);
        filled_exposed_dropdown.setTypeface(RalewayRegular);

        String[] TIME = new String[] {"1 Hora", "5 Horas", "12 Horas", "1 Día"};

        ArrayAdapter<String> myListAdapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()),R.layout.item_layout,R.id.suggestions, TIME);

        @SuppressLint("CutPasteId") AutoCompleteTextView editTextFilledExposedDropdown = RootView.findViewById(R.id.filled_exposed_dropdown);
        editTextFilledExposedDropdown.setAdapter(myListAdapter);

        button_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // La actividad terminará hasta que termine el tiempo del Snackbar
                Snackbar.make(view, R.string.snackbar_preferences_updated, Snackbar.LENGTH_SHORT)
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
