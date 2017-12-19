package com.agnt45.earthquakealert;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by an160 on 17-12-2017.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeData>> {
    private static final String LOG_TAG = EarthquakeLoader.class.getName();
    private String mUrl;
    public EarthquakeLoader(Context context,String url) {
        super(context);
        mUrl = url;

    }
    protected void onStartLoading() {
        Log.d("Loader","onStartLoading()");
        forceLoad();
    }
    @Override
    public List<EarthquakeData> loadInBackground() {
        Log.d("Loader","loadInBackground()");
        if(mUrl == null){
            return null;
        }
        List<EarthquakeData> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }
}
