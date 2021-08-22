package com.traning.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.traning.R;
import com.traning.adapters.ticketsAdapter;
import com.traning.database.TicketDatabase;
import com.traning.models.ticket;

import java.util.ArrayList;

public class ticketsFragment extends Fragment {

    private View ticketsView;
    private RecyclerView ticketsRecyclerView;
    private ticketsAdapter adapter;
    private ticket tickets = new ticket();
    private ArrayList<ticket> list = new ArrayList<>();
    private TextView notFoundText;
    private static final String TAG = "ticket";
    private String userID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ticketsView = inflater.inflate(R.layout.tickets_fragment_layout, container, false);
        ticketsRecyclerView = ticketsView.findViewById(R.id.search_tickets);
        notFoundText = ticketsView.findViewById(R.id.text_not_found);
        userID = getArguments().getString(getString(R.string.phone));

        setList();

        if (getArguments().getString(getString(R.string.myTrips)) == null) {
            filterTickets(getArguments().getString(getString(R.string.fromCode)), getArguments().getString(getString(R.string.toCode)),
                    getArguments().getString(getString(R.string.departureDate)), getArguments().getString(getString(R.string.returnDate)));
        } else {
            //Get database
            getBookedTickets();
            Log.d(TAG,"else !!");

        }
        return ticketsView;
    }

    private void initRecyclerView() {
        adapter = new ticketsAdapter(tickets.getList(), userID, getActivity().getApplicationContext(),false);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        ticketsRecyclerView.setLayoutManager(linearLayout);
        ticketsRecyclerView.setAdapter(adapter);

    }

    private void filterTickets(String fromCode, String toCode, String departureDate, String returnDate) {

        ArrayList<ticket> searchList = new ArrayList<>();

        for (ticket item : list) {
            if (item.getFromAirportCode().equals(fromCode) && item.getToAirportCode().equals(toCode) &&
                    item.getDepartureDate().equals(departureDate) && item.getReturnDate().equals(returnDate)) {

                searchList.add(item);
            }
        }
        if (searchList.isEmpty()) {
            notFoundText.setVisibility(View.VISIBLE);
            ticketsRecyclerView.setVisibility(View.GONE);
            Log.d(TAG, "Empty List");
        } else {
            tickets.setList(searchList);
            notFoundText.setVisibility(View.GONE);
            ticketsRecyclerView.setVisibility(View.VISIBLE);
            Log.d(TAG, "NOT Empty");
            initRecyclerView();

        }
    }

    private void getBookedTickets() {
        notFoundText.setVisibility(View.GONE);
        ticketsRecyclerView.setVisibility(View.VISIBLE);
        TicketDatabase database = new TicketDatabase(getActivity().getApplicationContext());
        Cursor data = database.getAll();
        ArrayList<ticket> bookedList = new ArrayList<>();
        if (data != null) {
            while (data.moveToNext()) {
                if(userID.equals(data.getString(data.getColumnIndexOrThrow(database.userIdColumn)))) {
                    bookedList.add(new
                            ticket(data.getString(data.getColumnIndexOrThrow(database.FromCodeColumn))
                            , data.getString(data.getColumnIndexOrThrow(database.ToCodeColumn))
                            , data.getString(data.getColumnIndexOrThrow(database.FlightCompanyColumn))
                            , data.getString(data.getColumnIndexOrThrow(database.PriceColumn))
                            , data.getString(data.getColumnIndexOrThrow(database.DepartureTakeoffColumn))
                            , data.getString(data.getColumnIndexOrThrow(database.DepartureLandingColumn))
                            , data.getString(data.getColumnIndexOrThrow(database.ReturnTakeoffColumn))
                            , data.getString(data.getColumnIndexOrThrow(database.ReturnLandingColumn))));
                    Log.d(TAG + "list >>> ", data.getString(data.getColumnIndexOrThrow(database.ToCodeColumn)));
                }
            }
            if (bookedList.isEmpty()){
                Log.d(TAG, "Empty List");
            }
            adapter = new ticketsAdapter(bookedList, userID, getActivity().getApplicationContext() , true);
            LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
            ticketsRecyclerView.setLayoutManager(linearLayout);
            ticketsRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else {
            notFoundText.setVisibility(View.VISIBLE);
            notFoundText.setText(getString(R.string.not_Found_for_user));
            ticketsRecyclerView.setVisibility(View.GONE);
        }

    }


    public void setList() {

        list.add(new ticket("SPX", "ALY", "Egypt air", "200", "20/9/2021", "30/9/2021", "12:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("SPX", "ALY", "Egypt air", "150", "20/9/2021", "30/9/2021", "01:30", "03:00", "06:00", "9:30"));
        list.add(new ticket("SPX", "ALY", "Egypt air", "90", "20/9/2021", "30/9/2021", "12:30", "02:00", "01:30", "03:00"));
        list.add(new ticket("SPX", "ALY", "Egypt air", "210", "20/9/2021", "30/9/2021", "12:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("ALY", "SPX", "Egypt air", "150", "25/9/2021", "29/9/2021", "08:30", "10:00", "07:00", "10:30"));
        list.add(new ticket("Atz", "ABS", "Egypt air", "200", "1/9/2021", "8/9/2021", "08:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("AUE", "ASW", "Egypt air", "150", "18/9/2021", "29/9/2021", "11:30", "02:00", "05:00", "10:30"));
        list.add(new ticket("ABS", "LXR", "Egypt air", "150", "20/9/2021", "22/9/2021", "07:30", "09:00", "10:00", "12:30"));
        list.add(new ticket("ASW", "DBB", "Egypt air", "300", "25/9/2021", "30/9/2021", "12:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("HBE", "DBB", "Egypt air", "200", "26/9/2021", "29/9/2021", "05:30", "10:00", "09:00", "11:30"));
        list.add(new ticket("SPX", "DBB", "Egypt air", "250", "18/8/2021", "19/9/2021", "11:30", "02:00", "05:00", "8:30"));
        list.add(new ticket("LXR", "ASW", "Egypt air", "150", "22/8/2021", "30/9/2021", "10:30", "02:00", "07:00", "11:30"));
        list.add(new ticket("TCP", "DBB", "Egypt air", "250", "30/8/2021", "15/9/2021", "03:30", "07:00", "09:00", "12:30"));
        list.add(new ticket("HRG", "DBB", "Egypt air", "150", "22/8/2021", "30/9/2021", "04:30", "06:00", "07:00", "10:30"));
        list.add(new ticket("UVL", "ASW", "Egypt air", "200", "19/8/2021", "25/8/2021", "12:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("SPX", "DBB", "Egypt air", "250", "20/8/2021", "30/8/2021", "12:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("ALY", "ASW", "Egypt air", "300", "25/8/2021", "29/8/2021", "08:30", "10:00", "07:00", "10:30"));
        list.add(new ticket("Atz", "AUE", "Egypt air", "150", "1/9/2021", "8/9/2021", "08:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("AUE", "DBB", "Egypt air", "250", "18/8/2021", "29/8/2021", "11:30", "02:00", "05:00", "10:30"));
        list.add(new ticket("ABS", "DBB", "Egypt air", "150", "20/8/2021", "22/8/2021", "07:30", "09:00", "10:00", "12:30"));
        list.add(new ticket("SPX", "DBB", "Egypt air", "300", "20/8/2021", "30/8/2021", "12:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("ALY", "DBB", "Egypt air", "300", "25/8/2021", "29/8/2021", "08:30", "10:00", "07:00", "10:30"));
        list.add(new ticket("Atz", "UVL", "Egypt air", "200", "1/9/2021", "8/9/2021", "08:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("AUE", "DBB", "Egypt air", "250", "18/8/2021", "29/8/2021", "11:30", "02:00", "05:00", "10:30"));
        list.add(new ticket("ABS", "AUE", "Egypt air", "150", "20/8/2021", "22/8/2021", "07:30", "09:00", "10:00", "12:30"));
        list.add(new ticket("ASW", "DBB", "Egypt air", "250", "25/8/2021", "30/8/2021", "12:30", "02:00", "09:00", "12:30"));
        list.add(new ticket("HBE", "DBB", "Egypt air", "200", "26/8/2021", "29/8/2021", "05:30", "10:00", "09:00", "11:30"));
        list.add(new ticket("SPX", "AUE", "Egypt air", "150", "18/8/2021", "19/8/2021", "11:30", "02:00", "5:00", "8:30"));
        list.add(new ticket("LXR", "UVL", "Egypt air", "250", "22/8/2021", "30/8/2021", "10:30", "02:00", "07:00", "11:30"));
        list.add(new ticket("TCP", "DBB", "Egypt air", "150", "30/8/2021", "15/9/2021", "03:30", "07:00", "09:00", "12:30"));
        list.add(new ticket("HRG", "AUE", "Egypt air", "300", "22/8/2021", "30/8/2021", "04:30", "06:00", "07:00", "10:30"));
        list.add(new ticket("UVL", "DBB", "Egypt air", "300", "19/8/2021", "25/8/2021", "12:30", "02:00", "09:00", "12:30"));

    }


}
