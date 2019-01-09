package com.mohak.android.travelapp.models;

public class Season {

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public int getSeasonImage() {
        return seasonImage;
    }

    public void setSeasonImage(int seasonImage) {
        this.seasonImage = seasonImage;
    }

    private String seasonName;
    private int seasonImage;


    public Season(String seasonName, int seasonImage) {
        super();
        this.seasonImage = seasonImage;
        this.seasonName = seasonName;
    }

}
