package com.mohak.android.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mohak.android.travelapp.models.Country;
import com.mohak.android.travelapp.models.SeasonPlace;

import java.util.Objects;

public class CountryPlaceDetailActivity extends AppCompatActivity {

    private Country country;
    private int countryImage;
    private String countryName;
    private TextView descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_place_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        country = gson.fromJson(strObj, Country.class);

        countryImage = country.getCountryImage();
        countryName = country.getCountryName();

        Button button = findViewById(R.id.button);
        descriptionTv = findViewById(R.id.place_description);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setTitle(countryName);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setBackground(getResources().getDrawable(countryImage));

        if (countryName.equals("Taj Mahal")) {
            descriptionTv.setText(getString(R.string.tajamahal));
        } else if (countryName.equals("Lotus Temple")) {
            descriptionTv.setText(getString(R.string.lotustemple));
        } else if (countryName.equals("Sanchi Stupa")) {
            descriptionTv.setText(getString(R.string.sanchistupa));
        } else if (countryName.equals("Statue of Liberty")) {
            descriptionTv.setText(getString(R.string.statueofliberty));
        } else if (countryName.equals("Leaning tower of pisa")) {
            descriptionTv.setText(getString(R.string.pisa));
        } else if (countryName.equals("Burj Khalifa")) {
            descriptionTv.setText(getString(R.string.burjkhalifa));
        } else if (countryName.equals("Burj-Al-Arab")) {
            descriptionTv.setText(getString(R.string.burjalarab));
        } else if (countryName.equals("Colosseum")) {
            descriptionTv.setText(getString(R.string.colosseum));
        } else if (countryName.equals("Rio de Janeiro")) {
            descriptionTv.setText(getString(R.string.riodejaneiro));
        } else if (countryName.equals("Big Ben")) {
            descriptionTv.setText(getString(R.string.bigben));
        } else if (countryName.equals("The Great Buddha of Thailand")) {
            descriptionTv.setText(getString(R.string.buddha));
        } else if (countryName.equals("Saint Basil's Cathedral")) {
            descriptionTv.setText(getString(R.string.cathedral));
        } else if (countryName.equals("Eiffel Tower")) {
            descriptionTv.setText(getString(R.string.eiffeltower));
        }



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
                Intent intent = new Intent(CountryPlaceDetailActivity.this, FullImageActivity.class);
                intent.putExtra("countryObj", gson.toJson(country));
                startActivity(intent);
            }
        });
    }
}
