package com.traning.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.traning.R;

import java.util.ArrayList;

public class suggestedAdapter extends RecyclerView.Adapter<suggestedAdapter.suggestedViewHolder> {

    private ArrayList<String> images = new ArrayList<>();

    public suggestedAdapter(ArrayList<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public suggestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_ads, parent, false);
        suggestedAdapter.suggestedViewHolder viewFinder = new suggestedViewHolder(itemView);
        return viewFinder;
    }

    @Override
    public void onBindViewHolder(@NonNull suggestedViewHolder holder, int position) {
        Picasso.get().load(images.get(position)).fit().into(holder.adsImage);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class suggestedViewHolder extends RecyclerView.ViewHolder {
        ImageView adsImage;

        public suggestedViewHolder(@NonNull View itemView) {
            super(itemView);
            adsImage = itemView.findViewById(R.id.places_ads_image_view);
        }
    }
}
