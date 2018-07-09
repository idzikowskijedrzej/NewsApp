package com.example.jedrzej.newspart1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

public class MainActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<News>> {
    private NewsAdapter newsAdapter;
    TextView noConnectionTextView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noConnectionTextView = findViewById(R.id.noConnection_TextView);
        noConnectionTextView.setText(R.string.noNews);

        listView = findViewById(R.id.list_news);
        listView.setEmptyView(noConnectionTextView);

        newsAdapter = new NewsAdapter(this);
        listView.setAdapter(newsAdapter);

        // Check internet connection
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            getSupportLoaderManager().initLoader(0, null, this);
        } else {
            noConnectionTextView.setVisibility(View.VISIBLE);
            noConnectionTextView.setText(R.string.noConnection);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = newsAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getUrl());
                Intent goWebsite = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(goWebsite);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        if (data != null) {
            noConnectionTextView.setVisibility(View.GONE);
            newsAdapter.setNotifyOnChange(false);
            newsAdapter.clear();
            newsAdapter.setNotifyOnChange(true);
            newsAdapter.addAll(data);
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {
            noConnectionTextView.setVisibility(View.VISIBLE);
            noConnectionTextView.setText(R.string.noConnection);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
    }

    @Override
    // This method initialize the contents of the Activity's options menu.
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu we specified in XML
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
