package com.agnt45.earthquakealert;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<EarthquakeData>>{
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";
    EarthquakedataAdapter earthquakedataAdapter;
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private ListView listView;
    private TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        emptyStateTextView =  findViewById(R.id.emptyMessage);

        listView = findViewById(R.id.list);
        listView.setEmptyView(emptyStateTextView);
        earthquakedataAdapter = new EarthquakedataAdapter(this,new ArrayList<EarthquakeData>());
        listView.setAdapter(earthquakedataAdapter);
        Context context = this;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork =  cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork !=null && activeNetwork.isConnectedOrConnecting();
        if(isConnected ){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(EARTHQUAKE_LOADER_ID,null,this);
            Log.d("Loader","initLoader()");
        }
        else {
            View loadingIndicator = findViewById(R.id.progress);
            loadingIndicator.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.nointernetConnectionMessageString);
        }

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

    @Override
    public Loader<List<EarthquakeData>> onCreateLoader(int i, Bundle bundle) {
        Log.d("Loader","onCreateLoader()");
        return new EarthquakeLoader(this,USGS_REQUEST_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeData>> loader, List<EarthquakeData> earthquakeData) {
        Log.d("Loader","onLoaderFinished()");
        View loadingIndicator = findViewById(R.id.progress);
        loadingIndicator.setVisibility(View.GONE);
        emptyStateTextView.setText(R.string.emptyMessageString);
        earthquakedataAdapter.clear();
        if(earthquakeData !=null && !earthquakeData.isEmpty()){
            earthquakedataAdapter.addAll(earthquakeData);

        }

    }

    @Override
    public void onLoaderReset(Loader<List<EarthquakeData>> loader) {
        Log.d("Loader","onLoaderReset()");
        earthquakedataAdapter.clear();
    }



}
