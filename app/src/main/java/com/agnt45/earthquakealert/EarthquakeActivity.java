package com.agnt45.earthquakealert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        ListView listView = findViewById(R.id.list);
        ArrayList<EarthquakeData> earthquakes = QueryUtils.extractEarthquakes();
        EarthquakedataAdapter earthquakedataAdapter = new EarthquakedataAdapter(this,earthquakes);
        listView.setAdapter(earthquakedataAdapter);

    }
}
