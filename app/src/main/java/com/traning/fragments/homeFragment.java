package com.traning.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.traning.R;
import com.traning.adapters.suggestedAdapter;
import com.traning.adapters.ticketsAdapter;

import java.util.ArrayList;

public class homeFragment extends Fragment {

    private View homeView;
    TextView userName;
    private RecyclerView suggestedRecyclerView;
    private suggestedAdapter adapter;
    private ViewFlipper viewFlipper ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        userName = homeView.findViewById(R.id.user_name);
        String first_name;
        first_name = getArguments().getString("fName");
        userName.setText("Hello, "+first_name);

        suggestedRecyclerView = homeView.findViewById(R.id.suggested_recycler_view);
        viewFlipper = homeView.findViewById(R.id.ads_recyclerview);
        flipImages();
        initRecyclerView();
        Handler handler  = new Handler();
        handler.postDelayed(this::Notify,1000);
        return homeView;
    }

    public void flipImages(){

        for(int i = 0;i < setAdsImages().size() ; i++) {
            ImageView imageView = new ImageView(getContext());
            Picasso.get().load(setAdsImages().get(i)).fit().into(imageView);
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(getContext(),"Place Name",Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
            viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(5000);
            viewFlipper.setAutoStart(true);
            viewFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);


        }

    }

    private void initRecyclerView() {
        adapter = new suggestedAdapter(setAdsImages());
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setOrientation(RecyclerView.HORIZONTAL);
        suggestedRecyclerView.setLayoutManager(linearLayout);
        suggestedRecyclerView.setAdapter(adapter);


    }

    private ArrayList<String> setAdsImages(){
        ArrayList<String> imagesList = new ArrayList<>();

        imagesList.add("https://www.planetware.com/photos-large/EGY/egypt-cairo-pyramids-of-giza.jpg");
        imagesList.add("https://cdn.discordapp.com/attachments/856090353329504267/877722634618556476/rixos-sharm-el-sheikh.jpg");
        imagesList.add("https://cdn.discordapp.com/attachments/856090353329504267/877722646681387038/STELLA_DI_MARE_BEACH_RESORT__SPA_-_Hurghada_00.jpg");
        imagesList.add("https://cdn.discordapp.com/attachments/856090353329504267/877722649051140096/2000x2000-0-70-41cac53a919fc16dc02b47ba7ceb1f2d.jpg");
        imagesList.add("https://cdn.discordapp.com/attachments/856090353329504267/877724429277343764/egypt-cairo.jpg");
        imagesList.add("https://cdn.discordapp.com/attachments/856090353329504267/877726947264831498/200611101955-01-egypt-dahab-full-169.jpg");
        imagesList.add("https://cdn.discordapp.com/attachments/856090353329504267/877724432431472700/shutterstockRF_1037036482.jpg");
        imagesList.add("https://cdn.discordapp.com/attachments/856090353329504267/877726441494700062/Agiba_Beach_-_Marsa_Matrouh_Egypt_10.jpg");
        return imagesList;
    }

    private void Notify(){
        adapter.notifyDataSetChanged();
    }
}
