package com.mohak.android.travelapp.models;

public class Country {

    private int countryImage;
    private String countryName;


    public Country(int countryImage, String countryName) {
        super();
        this.countryImage = countryImage;
        this.countryName = countryName;
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
