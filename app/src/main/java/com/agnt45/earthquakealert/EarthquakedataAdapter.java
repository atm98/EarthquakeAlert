package com.agnt45.earthquakealert;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agnt45 on 11-11-2017.
 */

public class EarthquakedataAdapter extends ArrayAdapter<EarthquakeData> {
    public EarthquakedataAdapter(Activity context, ArrayList<EarthquakeData> earthquakeData) {

        super(context, 0, earthquakeData);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listitem, parent, false);
        }
        EarthquakeData earthquakeData = getItem(position);
        TextView magnitude =  listItemView.findViewById(R.id.magnitude);
        TextView location1  = listItemView.findViewById(R.id.location1);
        TextView location2  = listItemView.findViewById(R.id.location2);
        TextView date = listItemView.findViewById(R.id.date);
        TextView time = listItemView.findViewById(R.id.time);
        Double mag = Double.parseDouble(earthquakeData.getMagnitude());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String  Magnitude = decimalFormat.format(mag);

        String place = earthquakeData.getLocation();
        if (place.contains(" of ")) {
            String[] parts = place.split("(?<=of)");
            String locationOffset = parts[0] + " of ";
            String primaryLocation = parts[1];
            location1.setText(locationOffset);
            location2.setText(primaryLocation);
        } else {
            String locationOffset = "Near the";
            String primaryLocation = place;
            location1.setText(locationOffset);
            location2.setText(primaryLocation);
        }
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(mag);
        magnitudeCircle.setColor(magnitudeColor);
        date.setText(earthquakeData.getDate());
        time.setText(earthquakeData.getTime());
        magnitude.setText(Magnitude);
        return  listItemView;

    }

    private int getMagnitudeColor(Double mag) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(mag);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;



        }

        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

}
