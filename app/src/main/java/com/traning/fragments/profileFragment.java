package com.traning.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.traning.R;

public class profileFragment extends Fragment {

    private View profileView;
    TextView fullName , firstName , lastName, email,phone;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileView = inflater.inflate(R.layout.profile_frgment,container,false);

        String firstNameStr  , lastNameStr , emailStr ,phoneStr ;

        fullName = profileView.findViewById(R.id.full_name);
        firstName = profileView.findViewById(R.id.name);
        lastName = profileView.findViewById(R.id.last_name);
        email = profileView.findViewById(R.id.email);
        phone = profileView.findViewById(R.id.phone);

        firstNameStr = getArguments().getString(getString(R.string.fName));
        lastNameStr = getArguments().getString(getString(R.string.lName));
        emailStr = getArguments().getString(getString(R.string.email));
        phoneStr = getArguments().getString(getString(R.string.phone));
        
        fullName.setText(" "+firstNameStr+" "+lastNameStr);
        firstName.setText(" "+firstNameStr);
        lastName.setText(" "+lastNameStr);
        email.setText(" "+emailStr);
        phone.setText(" "+phoneStr);



        return profileView;
    }
}
