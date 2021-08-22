package com.traning.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.traning.R;

import java.util.Calendar;

public class bookFragment extends Fragment {

    private View bookView;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private DatePickerDialog.OnDateSetListener returnDateSetListener;
    private TextView departureDate , returnDate, fromSpinner , toSpinner;
    private Button searchButton;
    private com.traning.fragments.ticketsFragment ticketsFragment;
    private Bundle ticketDetails;
    private String[] Airports;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bookView = inflater.inflate(R.layout.book_fragment_view, container, false);


        Airports = getResources().getStringArray(R.array.airports);

        toSpinner = bookView.findViewById(R.id.to_spinner);
        fromSpinner = bookView.findViewById(R.id.from_spinner);

        departureDate = bookView.findViewById(R.id.departure_date_text);
        returnDate = bookView.findViewById(R.id.return_date);

        searchButton = bookView.findViewById(R.id.search_tickets_button);

        checkButton();
        spinnerHandler(fromSpinner,Airports);
        spinnerHandler(toSpinner,Airports);

        getDepartureDate();
        getReturnDate();

        search();

        return bookView;
    }

    private void spinnerHandler(TextView spinner , String[] Airports){

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog ;
                dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_airport_dialog);
                dialog.getWindow().setLayout(650,850);
                dialog.show();

                EditText airportName = dialog.findViewById(R.id.airportName);
                ListView airportList = dialog.findViewById(R.id.airports_list);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity()
                        , R.layout.list_item_view, Airports);
                airportList.setAdapter(adapter);

                airportName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                airportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        spinner.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });

            }
        });
    }


    private void checkButton(){
        RadioGroup radioGroup;
        radioGroup = bookView.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.if_return){
                    returnDate.setEnabled(true);
                }else{
                    returnDate.setText("Return Date");
                    returnDate.setEnabled(false);
                }
            }
        });

    }

    private void getDepartureDate() {

        departureDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                int day = calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(), R.style.Theme_cal,
                        dateSetListener, year, month, day);

                dialog.getWindow().setLayout(650,850);
                dialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = dayOfMonth + "/" + month + "/" + year;
                departureDate.setText(date);

            }
        };
    }
    private void getReturnDate() {

        returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                int day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), R.style.Theme_cal,
                        returnDateSetListener, year, month, day);
                dialog.getWindow().setLayout(650,850);
                dialog.show();

            }
        });

        returnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                returnDate.setText(date);

            }
        };
    }

    private void search(){

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ticketsFragment = new ticketsFragment();
                ticketDetails =new Bundle();

                ticketDetails.putString(getString(R.string.fromCode),getCode(fromSpinner.getText().toString()));
                ticketDetails.putString(getString(R.string.toCode),getCode(toSpinner.getText().toString()));
                ticketDetails.putString(getString(R.string.departureDate),departureDate.getText().toString());
                ticketDetails.putString(getString(R.string.returnDate),returnDate.getText().toString());
                ticketDetails.putString(getString(R.string.phone),getArguments().getString(getString(R.string.phone)));

                ticketsFragment.setArguments(ticketDetails);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ticketsFragment).commit();
            }
        });

    }

    private String getCode(String name){
        String code;
        switch (name){
            case "Alexandria International Airport,ALY": {
                code ="ALY";
                break;
            }
            case "Assiut Airport,ATZ": {
                code = "ATZ";
                break;
            }
            case "Abu Rudeis Airport,AUE": {
                code = "AUE";
                break;
            }
            case "Abu Simbel Airport,ABS": {
                code = "ABS";
                break;
            }
            case "Aswan International Airport,ASW": {
                code = "ASW";
                break;
            }
            case "Borg El Arab Airport,HBE": {
                code = "HBE";
                break;
            }
            case "Cairo International Airport,CAI": {
                code = "CAI";
                break;
            }
            case "Dakhla Oasis Airport,DAK": {
                code = "DAK";
                break;
            }
            case "El Arish International Airport,AAC": {
                code = "AAC";
                break;
            }
            case " El Alamain International Airport,DBB": {
                code = "DBB";
                break;
            }
            case "El Gora Airport,EGH": {
                code = "EGH";
                break;
            }
            case "El Kharga Airport,UVL": {
                code = "UVL";
                break;
            }
            case "El Tor Airport,ELT": {
                code = "ELT";
                break;
            }
            case "Hurghada International Airport,HRG": {
                code = "HRG";
                break;
            }
            case "Luxor International Airport,LXR": {
                code = "LXR";
                break;
            }
            case "Marsa Matruh Airport,MUH": {
                code = "MUH";
                break;
            }
            case "Marsa Alam International Airport,RMF": {
                code = "RMF";
                break;
            }
            case "Sphinx International Airport,SPX": {
                code = "SPX";
                break;
            }

            case "St. Catherine International Airport,SKV": {
                code = "SKV";
                break;
            }
            case "Sohag International Airport,HMB": {
                code = "HMB";
                break;
            }
            case "Sharm El Sheikh International Airport,SSH": {
                code = "SSH";
                break;
            }  case "Sharq El Owainat Airport,GSQ": {
                code = "GSQ";
                break;
            }
            case "Taba International Airport,TCP": {
                code = "TCP";
                break;
            } default:{
                code = "LLL";
                break;
            }
        }
        return code;
    }

}