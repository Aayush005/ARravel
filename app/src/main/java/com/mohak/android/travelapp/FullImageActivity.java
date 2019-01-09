package com.mohak.android.travelapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mohak.android.travelapp.models.Country;
import com.mohak.android.travelapp.models.SeasonPlace;
import com.mohak.android.travelapp.models.Suggested;

public class FullImageActivity extends AppCompatActivity {

    private SeasonPlace seasonPlace;
    private Country country;
    private Suggested suggested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        ImageView imageView = findViewById(R.id.image);

        Gson gson = new Gson();
        if (getIntent().hasExtra("obj")) {
            String strObj = getIntent().getStringExtra("obj");
            seasonPlace = gson.fromJson(strObj, SeasonPlace.class);
            imageView.setImageDrawable(getResources().getDrawable(seasonPlace.getCountryImage()));
        } else if (getIntent().hasExtra("countryObj")) {
            String strObj = getIntent().getStringExtra("countryObj");
            country = gson.fromJson(strObj, Country.class);
            imageView.setImageDrawable(getResources().getDrawable(country.getCountryImage()));
        } else if (getIntent().hasExtra("suggested_obj")) {
            String strObj = getIntent().getStringExtra("suggested_obj");
            suggested = gson.fromJson(strObj, Suggested.class);
            imageView.setImageDrawable(getResources().getDrawable(suggested.getSeasonImage()));
        }




    }
}
