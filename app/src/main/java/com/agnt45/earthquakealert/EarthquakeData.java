package com.agnt45.earthquakealert;

/**
 * Created by Agnt45 on 11-11-2017.
 */

public class EarthquakeData {
    public String getMagnitude() {
        return Magnitude;
    }

    public void setMagnitude(String magnitude) {
        Magnitude = magnitude;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public EarthquakeData() {
    }

    String Magnitude;
    String Location;
    String Date;
    String Time;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    String Url;


    public EarthquakeData(String magnitude, String location, String date, String time,String url) {
        Magnitude = magnitude;
        Location = location;
        Date = date;
        Time = time;
        Url = url;
    }
}
