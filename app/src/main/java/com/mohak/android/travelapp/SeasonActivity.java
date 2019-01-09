package com.mohak.android.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mohak.android.travelapp.adapters.SeasonPlacesAdapter;
import com.mohak.android.travelapp.models.SeasonPlace;

import java.util.ArrayList;
import java.util.List;

public class SeasonActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SeasonPlacesAdapter seasonPlacesAdapter;
    private List<SeasonPlace> seasonPlacesList = new ArrayList<>();
    private String seasonName;
    private Intent intent;
    private TextView seasonNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();
        seasonName = intent.getStringExtra("season_name");

        seasonNameTextView = findViewById(R.id.seasonName);

        if (seasonName.equals("Summer Season")) {
            seasonNameTextView.setText("Summer Season");
            seasonPlacesList.add(new SeasonPlace(R.drawable.indiaflag, R.drawable.imagelad, "Ladhaakh, India"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.indiaflag, R.drawable.imagegoa, "Goa, India"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.icelandflag, R.drawable.imageice, "Iceland"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.thailandimage, R.drawable.imagethai, "Koh Samui, Thailand"));
        } else if (seasonName.equals("Winter Season")) {
            seasonNameTextView.setText("Winter Season");
            seasonPlacesList.add(new SeasonPlace(R.drawable.indiaflag, R.drawable.manali, "Manali, India"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.icelandflag, R.drawable.whitecity, "Whitecity, USA"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.thailandimage, R.drawable.china, "HarbinCity, China"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.icelandflag, R.drawable.sweden, "Abisko, Sweden"));
        } else if (seasonName.equals("All Seasons")) {
            seasonNameTextView.setText("All Seasons");
            seasonPlacesList.add(new SeasonPlace(R.drawable.indiaflag, R.drawable.imagelad, "Ladhaakh, India"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.thailandimage, R.drawable.imagethai, "Koh Samui, Thailand"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.indiaflag, R.drawable.manali, "Manali, India"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.indiaflag, R.drawable.imagegoa, "Goa, India"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.thailandimage, R.drawable.china, "HarbinCity, China"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.icelandflag, R.drawable.imageice, "Iceland"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.icelandflag, R.drawable.whitecity, "Whitecity, USA"));
            seasonPlacesList.add(new SeasonPlace(R.drawable.icelandflag, R.drawable.sweden, "Abisko, Sweden"));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        seasonPlacesAdapter = new SeasonPlacesAdapter(this, seasonPlacesList, new SeasonPlacesAdapter.OnSeasonedPlaceItemClickListener() {
            @Override
            public void OnSeasonedPlaceItemClicked(int position) {

                Gson gson = new Gson();
                Intent intent = new Intent(SeasonActivity.this, SeasonDetailActivity.class);
                intent.putExtra("obj", gson.toJson(seasonPlacesList.get(position)));
                startActivity(intent);


            }
        });
        recyclerView.setAdapter(seasonPlacesAdapter);



    }
}
