package com.mohak.android.travelapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mohak.android.travelapp.adapters.CountryDetailsAdapter;
import com.mohak.android.travelapp.models.Country;
import com.mohak.android.travelapp.models.SeasonPlace;

import java.util.ArrayList;
import java.util.List;

public class CountryDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CountryDetailsAdapter countryDetailsAdapter;
    private List<Country> countryList = new ArrayList<>();
    private Country country;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(CountryDetailActivity.this, 2);
        recyclerView.setLayoutManager(mGridLayoutManager);

        textView = findViewById(R.id.text);

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("obj");
        country = gson.fromJson(strObj, Country.class);

        if (country.getCountryName().equals("India")) {
            countryList.add(new Country(R.drawable.tajmahal, "Taj Mahal"));
            countryList.add(new Country(R.drawable.lotustemple, "Lotus Temple"));
            countryList.add(new Country(R.drawable.sanchistupa, "Sanchi Stupa"));
            textView.setText("India");
        } else if (country.getCountryName().equals("New York City")) {
            countryList.add(new Country(R.drawable.statueofliberty, "Statue of Liberty"));
            textView.setText("New York City");
        } else if (country.getCountryName().equals("Italy")) {
            countryList.add(new Country(R.drawable.pisa, "Leaning tower of pisa"));
            textView.setText("Italy");
        } else if (country.getCountryName().equals("Dubai")) {
            countryList.add(new Country(R.drawable.burjkhalifa, "Burj Khalifa"));
            countryList.add(new Country(R.drawable.burjalarab, "Burj-Al-Arab"));
            textView.setText("Dubai");
        } else if (country.getCountryName().equals("Rome")) {
            countryList.add(new Country(R.drawable.colosseum, "Colosseum"));
            textView.setText("Rome");
        } else if (country.getCountryName().equals("Brazil")) {
            countryList.add(new Country(R.drawable.riodejaneiro, "Rio de Janeiro"));
            textView.setText("Brazil");
        } else if (country.getCountryName().equals("London")) {
            countryList.add(new Country(R.drawable.bigben, "Big Ben"));
            textView.setText("London");
        } else if (country.getCountryName().equals("Thailand")) {
            countryList.add(new Country(R.drawable.budhha, "The Great Buddha of Thailand"));
            textView.setText("Thailand");
        } else if (country.getCountryName().equals("Russia")) {
            countryList.add(new Country(R.drawable.saintcathedral, "Saint Basil's Cathedral"));
            textView.setText("Russia");
        } else if (country.getCountryName().equals("France")) {
            countryList.add(new Country(R.drawable.eiffeltower, "Eiffel Tower"));
            textView.setText("France");
        }

        countryDetailsAdapter = new CountryDetailsAdapter(this, countryList, new CountryDetailsAdapter.OnCountryDetailItemClickListener() {
            @Override
            public void OnCountryDetailItemClicked(int position) {
                Gson gson = new Gson();
                Intent intent = new Intent(CountryDetailActivity.this, CountryPlaceDetailActivity.class);
                intent.putExtra("obj", gson.toJson(countryList.get(position)));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(countryDetailsAdapter);

    }
}
