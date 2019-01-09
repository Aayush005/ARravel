package com.mohak.android.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mohak.android.travelapp.models.SeasonPlace;

import java.util.Objects;

public class SeasonDetailActivity extends AppCompatActivity {

    private Intent intent;
    private String seasonPlaceName;
    private int seasonPlaceImage;
    private TextView descriptionTv;
    private SeasonPlace seasonPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        seasonPlace = gson.fromJson(strObj, SeasonPlace.class);

        seasonPlaceImage = seasonPlace.getCountryImage();
        seasonPlaceName = seasonPlace.getCountryName();
        Log.e("SeasonDetailActivity", "season_place_name : " + seasonPlaceName);

        Button button = findViewById(R.id.button);


        descriptionTv = findViewById(R.id.place_description);

        if (seasonPlaceName.equals("Ladhaakh, India")) {
            descriptionTv.setText(getResources().getString(R.string.ladaakh_desc));
        } else if (seasonPlaceName.equals("Goa, India")) {
            descriptionTv.setText(getResources().getString(R.string.goa_desc));
        } else if (seasonPlaceName.equals("Iceland")) {
            descriptionTv.setText(getResources().getString(R.string.iceland_desc));
        } else if (seasonPlaceName.equals("Koh Samui, Thailand")) {
            descriptionTv.setText(getResources().getString(R.string.koh_samui_desc));
        }

        if (seasonPlaceName.equals("Manali, India")) {
            descriptionTv.setText(getResources().getString(R.string.manali));
        } else if (seasonPlaceName.equals("Whitecity, USA")) {
            descriptionTv.setText(getResources().getString(R.string.whitecity));
        } else if (seasonPlaceName.equals("HarbinCity, China")) {
            descriptionTv.setText(getResources().getString(R.string.harbin));
        } else if (seasonPlaceName.equals("Abisko, Sweden")) {
            descriptionTv.setText(getResources().getString(R.string.abisko));
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle(seasonPlaceName);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setBackground(getResources().getDrawable(seasonPlaceImage));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Intent intent = new Intent(SeasonDetailActivity.this, FullImageActivity.class);
                intent.putExtra("obj", gson.toJson(seasonPlace));
                startActivity(intent);
            }
        });

    }
}
