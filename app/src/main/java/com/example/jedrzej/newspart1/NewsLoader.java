package com.example.jedrzej.newspart1;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        List<News> newsList = null;
        try {
            URL url = Query.createUrl();
            String jsonResponse = Query.makeHttpRequest(url);
            newsList = Query.parseJson(jsonResponse);
        } catch (IOException e) {
            Log.e("Query", "loadInBackground Error: ", e);
        }
        return newsList;
    }
}
