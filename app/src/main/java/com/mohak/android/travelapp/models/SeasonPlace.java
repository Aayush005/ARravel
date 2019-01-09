package com.mohak.android.travelapp.models;

public class SeasonPlace {

    private int countryFlag;
    private int countryImage;
    private String countryName;

    public SeasonPlace(int countryFlag, int countryImage, String countryName) {
        super();
        this.countryName = countryName;
        this.countryFlag = countryFlag;
        this.countryImage = countryImage;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(int countryFlag) {
        this.countryFlag = countryFlag;
    }

    public int getCountryImage() {
        return countryImage;
    }

    public void setCountryImage(int countryImage) {
        this.countryImage = countryImage;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
