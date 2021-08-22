package com.traning.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.traning.R;
import com.traning.database.TicketDatabase;
import com.traning.models.ticket;

import java.util.List;


public class ticketsAdapter extends RecyclerView.Adapter<ticketsAdapter.ticketsViewHolder> {

    private List<ticket> ticketList;
    private String userID;
    private Context context;
    private boolean booked;

    public ticketsAdapter(List<ticket> ticketList, String id, Context context, boolean booked) {
        this.ticketList = ticketList;
        this.userID = id;
        this.context = context;
        this.booked = booked;
    }

    @NonNull
    @Override
    public ticketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_ticket_card, parent, false);
        ticketsViewHolder viewFinder = new ticketsViewHolder(itemView);
        return viewFinder;
    }

    @Override
    public void onBindViewHolder(@NonNull ticketsViewHolder holder, int position) {
        //set the data at the view

        holder.departureFromTime.setText(ticketList.get(position).getOutboundDepartureTime());
        holder.departureFromCode.setText(ticketList.get(position).getFromAirportCode());
        holder.departureToCode.setText(ticketList.get(position).getToAirportCode());
        holder.departureToTime.setText(ticketList.get(position).getDepartureLandingTime());

        holder.returnFromCode.setText(ticketList.get(position).getToAirportCode());
        holder.returnFromTime.setText(ticketList.get(position).getOutboundReturnTime());
        holder.returnToCode.setText(ticketList.get(position).getFromAirportCode());
        holder.returnToTime.setText(ticketList.get(position).getReturnLandingTime());

        holder.price.setText(ticketList.get(position).getPrice() + "$");

        holder.airlineCompany.setText(ticketList.get(position).getFlightCompany());

        //add to my trips
        if (!booked) {
            holder.bookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TicketDatabase database = new TicketDatabase(context);
                    if (database.bookTicket(userID, ticketList.get(position))) {
                        Toast.makeText(context, "Ticket Booked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.bookButton.setText("Booked");
            holder.bookButton.setEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public class ticketsViewHolder extends RecyclerView.ViewHolder {

        //initialize the view
        TextView departureFromCode, departureToCode, departureFromTime, departureToTime;
        TextView returnFromCode, returnToCode, returnFromTime, returnToTime;
        TextView price, airlineCompany;
        Button bookButton;

        public ticketsViewHolder(@NonNull View itemView) {
            super(itemView);

            departureFromCode = itemView.findViewById(R.id.departure_from_code);
            departureFromTime = itemView.findViewById(R.id.departure_from_time);
            departureToCode = itemView.findViewById(R.id.departure_landing_code);
            departureToTime = itemView.findViewById(R.id.departure_landing_time);

            returnFromCode = itemView.findViewById(R.id.return_from_code);
            returnFromTime = itemView.findViewById(R.id.return_from_time);
            returnToCode = itemView.findViewById(R.id.return_landing_code);
            returnToTime = itemView.findViewById(R.id.return_landing_time);

            price = itemView.findViewById(R.id.price);
            airlineCompany = itemView.findViewById(R.id.flight_company);

            bookButton = itemView.findViewById(R.id.book_button);


        }
    }
}
