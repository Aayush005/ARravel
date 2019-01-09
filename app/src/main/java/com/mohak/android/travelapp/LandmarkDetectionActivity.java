package com.mohak.android.travelapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmark;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmarkDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.google.firebase.ml.vision.common.FirebaseVisionLatLng;
import com.google.gson.Gson;
import com.mohak.android.travelapp.models.Country;

import java.util.ArrayList;
import java.util.List;

public class LandmarkDetectionActivity extends AppCompatActivity {
    Intent intent;
    Bitmap bmp;
    byte[] byteArray;
    ImageView imageView;
    ProgressBar progressBar;
    TextView textView;
    List<Country> monumentsList = new ArrayList<>();

    String landmarkName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmark_detection);
        monumentsList.add(new Country(R.drawable.tajmahal, "Taj Mahal"));
        monumentsList.add(new Country(R.drawable.lotustemple, "Lotus Temple"));
        monumentsList.add(new Country(R.drawable.sanchistupa, "Sanchi Stupa"));
        monumentsList.add(new Country(R.drawable.statueofliberty, "Statue of Liberty"));
        monumentsList.add(new Country(R.drawable.pisa, "Leaning tower of pisa"));
        monumentsList.add(new Country(R.drawable.burjkhalifa, "Burj Khalifa"));
        monumentsList.add(new Country(R.drawable.burjalarab, "Burj-Al-Arab"));
        monumentsList.add(new Country(R.drawable.colosseum, "Colosseum"));
        monumentsList.add(new Country(R.drawable.riodejaneiro, "Rio de Janeiro"));
        monumentsList.add(new Country(R.drawable.bigben, "Big Ben"));
        monumentsList.add(new Country(R.drawable.budhha, "The Great Buddha of Thailand"));
        monumentsList.add(new Country(R.drawable.saintcathedral, "Saint Basil's Cathedral"));
        monumentsList.add(new Country(R.drawable.eiffeltower, "Eiffel Tower"));




        intent = getIntent();

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);


        byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        imageView = findViewById(R.id.landmark_image);
        imageView.setImageBitmap(bmp);
        firebase_code(bmp);
    }

    public void firebase_code(Bitmap bitmap){
        Log.e("MainActivity" , "Inside firebase_code");
        FirebaseVisionCloudDetectorOptions options =
                new FirebaseVisionCloudDetectorOptions.Builder()
                        .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                        .setMaxResults(5)
                        .build();

        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);



        FirebaseVisionImageMetadata metadata = new FirebaseVisionImageMetadata.Builder()
                .setWidth(480)   // 480x360 is typically sufficient for
                .setHeight(360)  // image recognition
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)

                .build();

        FirebaseVisionCloudLandmarkDetector detector = FirebaseVision.getInstance()
                .getVisionCloudLandmarkDetector();

        Task<List<FirebaseVisionCloudLandmark>> result = detector.detectInImage(image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionCloudLandmark>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionCloudLandmark> firebaseVisionCloudLandmarks) {
                        Log.e("Main" , "Inside success");
                        for (FirebaseVisionCloudLandmark landmark: firebaseVisionCloudLandmarks) {

                            progressBar.setVisibility(View.INVISIBLE);
                            textView.setVisibility(View.VISIBLE);

                            Rect bounds = landmark.getBoundingBox();
                             landmarkName = landmark.getLandmark();
                            String entityId = landmark.getEntityId();
                            float confidence = landmark.getConfidence();
                            for (int i = 0;i<monumentsList.size();i++){
                                if(monumentsList.get(i).getCountryName().equals(landmarkName)){
                                    Gson gson = new Gson();
                                    Intent intent = new Intent(LandmarkDetectionActivity.this, SeasonDetailActivity.class);


                                    intent.putExtra("obj", gson.toJson(monumentsList.get(i)));
                                    startActivity(intent);

                                }

                            }



                            Log.e("MainActivity" , "Landmark name : " + landmarkName);


                            // Multiple locations are possible, e.g., the location of the depicted
                            // landmark and the location the picture was taken.
                            for (FirebaseVisionLatLng loc: landmark.getLocations()) {
                                double latitude = loc.getLatitude();
                                double longitude = loc.getLongitude();
                            }
                        }
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Log.e("Main" , "Inside failure");

                        // Task failed with an exception
                        Log.e("Mian" , "error : " + e);
                        // ...
                    }
                });



    }
}
