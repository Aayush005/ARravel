package com.mohak.android.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;
import com.mohak.android.travelapp.adapters.MyAdapter;
import com.mohak.android.travelapp.models.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    List<Country> countryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        mRecyclerView = findViewById(R.id.recyclerview);

        countryList.add(new Country(R.drawable.india, "India"));
        countryList.add(new Country(R.drawable.newyorkcity, "New York City"));
        countryList.add(new Country(R.drawable.italy, "Italy"));
        countryList.add(new Country(R.drawable.italy, "Dubai"));
        countryList.add(new Country(R.drawable.rome, "Rome"));
        countryList.add(new Country(R.drawable.brazil, "Brazil"));
        countryList.add(new Country(R.drawable.london, "London"));
        countryList.add(new Country(R.drawable.thailand, "Thailand"));
        countryList.add(new Country(R.drawable.russia, "Russia"));
        countryList.add(new Country(R.drawable.france, "France"));

        ZoomCenterCardLayoutManager layoutManager = new ZoomCenterCardLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        MyAdapter myAdapter = new MyAdapter(CountriesActivity.this, countryList, new MyAdapter.OnCountryItemSelectedListener() {
            @Override
            public void OnCountryItemClicked(int position) {
                Gson gson = new Gson();
                Intent intent = new Intent(CountriesActivity.this, CountryDetailActivity.class);
                intent.putExtra("obj", gson.toJson(countryList.get(position)));
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(myAdapter);
    }
}
