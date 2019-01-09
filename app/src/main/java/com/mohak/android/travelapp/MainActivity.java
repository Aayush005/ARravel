package com.mohak.android.travelapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mohak.android.travelapp.adapters.SeasonsAdapter;
import com.mohak.android.travelapp.adapters.SuggestedAdapter;
import com.mohak.android.travelapp.models.Season;
import com.mohak.android.travelapp.models.Suggested;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private RecyclerView seasonsRecyclerView;
    private RecyclerView suggestedRecyclerView;
    private SeasonsAdapter seasonsAdapter;
    private List<Season> seasonList = new ArrayList<>();
    ImageView autoDetectImageView;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    String address;
    private CardView cardView;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private static final int REQUEST_CHECK_SETTINGS = 100;

    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;
    private String mLastUpdateTime;
    TextView currentLocationTextView , detectionLocationTextView;
    ProgressBar progressBar;



    private SuggestedAdapter suggestedAdapter;
    private List<Suggested> suggestedList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        seasonList.add(new Season("Summer Season", R.drawable.tropicalseason));
        seasonList.add(new Season("Winter Season", R.drawable.winterseason));
        seasonList.add(new Season("All Seasons", R.drawable.allseasons));

        seasonsRecyclerView = findViewById(R.id.recyclerview);
        suggestedRecyclerView = findViewById(R.id.recyclerview2);

        cardView = findViewById(R.id.cardview_countries);
        autoDetectImageView = findViewById(R.id.autoDetectImageView);
       // ZoomCenterCardLayoutManager layoutManager = new ZoomCenterCardLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        seasonsAdapter = new SeasonsAdapter(this, 3, seasonList, new SeasonsAdapter.OnSeasonedItemClickListener() {
            @Override
            public void OnSeasonedItemClicked(int position) {
                Intent intent = new Intent(MainActivity.this, SeasonActivity.class);
                intent.putExtra("season_name", seasonList.get(position).getSeasonName());
                startActivity(intent);
            }
        });
        seasonsRecyclerView.setLayoutManager(layoutManager);
        seasonsRecyclerView.setAdapter(seasonsAdapter);

        suggestedList.add(new Suggested("Saint Basil's Cathedral", R.drawable.saintcathedral));
        suggestedList.add(new Suggested("The Great Buddha", R.drawable.budhha));
        suggestedList.add(new Suggested("Statue Of Liberty", R.drawable.statueofliberty));

       // ZoomCenterCardLayoutManager layoutManager2 = new ZoomCenterCardLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
       RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        suggestedAdapter = new SuggestedAdapter(this, 3, suggestedList, new SuggestedAdapter.OnSuggestedItemClickListener() {
            @Override
            public void OnSuggestedItemClicked(int position) {

                Gson gson = new Gson();
                Intent intent = new Intent(MainActivity.this, FullImageActivity.class);
                intent.putExtra("suggested_obj", gson.toJson(suggestedList.get(position)));
                startActivity(intent);


            }
        });
        suggestedRecyclerView.setLayoutManager(layoutManager2);
        suggestedRecyclerView.setAdapter(suggestedAdapter);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CountriesActivity.class);
                startActivity(i);


            }
        });



        autoDetectImageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                takePicture();

            }
        });

    }

















    private void takePicture(){ //you can call this every 5 seconds using a timer or whenever you want
        Intent cameraIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bitmap picture = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            picture.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byte[] byteArray = bStream.toByteArray();
//            imageView.setImageBitmap(picture);
//            firebase_code(picture);
            Intent intent = new Intent(MainActivity.this , LandmarkDetectionActivity.class);
            intent.putExtra("image" , byteArray);
            startActivity(intent);
        }
    }
}
