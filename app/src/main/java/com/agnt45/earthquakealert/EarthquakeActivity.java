package com.agnt45.earthquakealert;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        final EarthquakedataAdapter earthquakedataAdapter = new EarthquakedataAdapter(this,earthquakes);
        listView.setAdapter(earthquakedataAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String URL = earthquakedataAdapter.getItem(position).getUrl();
                Intent ViewData = new Intent(Intent.ACTION_VIEW);
                ViewData.setData(Uri.parse(URL));
                startActivity(ViewData);
            }
        });
    }

}
