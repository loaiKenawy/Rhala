package com.traning.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.traning.R;
import com.traning.fragments.bookFragment;
import com.traning.fragments.homeFragment;
import com.traning.fragments.profileFragment;
import com.traning.fragments.ticketsFragment;

public class homeActivity extends AppCompatActivity {


    private static final int homeId = R.id.home;
    private static final int bookId = R.id.book;
    private static final int profileId = R.id.profile;
    private static final int myTrips = R.id.my_trips;
    private static  String fName;
    private static  String lName;
    private static  String phone;
    private static  String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fName = getIntent().getExtras().getString(getString(R.string.fName));
        lName = getIntent().getExtras().getString(getString(R.string.lName));
        phone = getIntent().getExtras().getString(getString(R.string.phone));
        email = getIntent().getExtras().getString(getString(R.string.email));

        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.fName),fName);

        BottomNavigationView bottomNavigationView;
        homeFragment homeFragment = new homeFragment();
        homeFragment.setArguments(bundle);
        bottomNavigationView = findViewById(R.id.homeNavMenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    homeFragment homeFragment = new homeFragment();
                    bookFragment bookFragment = new bookFragment();
                    profileFragment profileFragment = new profileFragment();
                    ticketsFragment ticketsFragment = new ticketsFragment();

                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case homeId: {
                            Bundle bundle = new Bundle();
                            bundle.putString(getString(R.string.fName),fName);
                            selectedFragment = homeFragment;
                            selectedFragment.setArguments(bundle);
                            break;
                        }
                        case bookId: {
                            Bundle bundle = new Bundle();
                            bundle.putString(getString(R.string.phone),phone);
                            selectedFragment = bookFragment;
                            selectedFragment.setArguments(bundle);
                            break;
                        }
                        case profileId: {
                            Bundle bundle = new Bundle();
                            bundle.putString(getString(R.string.fName),fName);
                            bundle.putString(getString(R.string.lName),lName);
                            bundle.putString(getString(R.string.email),email);
                            bundle.putString(getString(R.string.phone),phone);
                            selectedFragment = profileFragment;
                            selectedFragment.setArguments(bundle);
                            break;
                        }
                        case myTrips: {
                            Bundle bundle = new Bundle();
                            bundle.putString(getString(R.string.myTrips),"true");
                            bundle.putString(getString(R.string.phone),getIntent().getExtras().getString(getString(R.string.phone)));
                            selectedFragment = ticketsFragment;
                            selectedFragment.setArguments(bundle);
                            break;
                        }

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

}