package com.mohak.android.travelapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mFlower;
    TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = findViewById(R.id.toolbar);
        mFlower = findViewById(R.id.ivImage);
        mDescription = findViewById(R.id.tvDescription);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            mToolbar.setTitle(mBundle.getString("Title"));
            mFlower.setImageResource(mBundle.getInt("Image"));
            mDescription.setText(mBundle.getString("Description"));
        }
    }
}
