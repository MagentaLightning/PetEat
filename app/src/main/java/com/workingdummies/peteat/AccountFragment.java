package com.workingdummies.peteat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class AccountFragment extends Fragment {

    TextInputLayout text_input_email, text_input_password;
    Button button_change_password, button_change_email;
    EditText edit_text_email, edit_text_password;

    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext=context;
    }
    //

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.action_button_add);
        if(item!=null)
            item.setVisible(false);
    }
    //

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View RootView = inflater.inflate(R.layout.fragment_account, container, false);

        text_input_email = RootView.findViewById(R.id.text_input_email);
        edit_text_email = RootView.findViewById(R.id.edit_text_email);
        text_input_password = RootView.findViewById(R.id.text_input_password);
        edit_text_password = RootView.findViewById(R.id.edit_text_password);
        Typeface RalewayRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/Raleway-Regular.ttf");
        text_input_email.setTypeface(RalewayRegular);
        edit_text_email.setTypeface(RalewayRegular);
        text_input_password.setTypeface(RalewayRegular);
        edit_text_password.setTypeface(RalewayRegular);

        button_change_password = RootView.findViewById(R.id.button_change_password);
        button_change_password.setTypeface(RalewayRegular);
        button_change_email = RootView.findViewById(R.id.button_change_email);
        button_change_email.setTypeface(RalewayRegular);

        button_change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        button_change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeEmailActivity.class);
                startActivity(intent);
            }
        });

        return RootView;
    }
}
